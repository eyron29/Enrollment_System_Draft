package enrollmentDemo;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {

    //implementation of addStudent method
    public void addStudent(Student student){
        String query = "INSERT INTO student (studentId, lName, fName, contactNo, email, dateOfBirth, yearLevel, major) VALUES (?, ?, ? ,?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getFirstName());
            stmt.setString(4, student.getContactNo());
            stmt.setString(5, student.getEmail());
            stmt.setString(6, student.getDateOfBirth());
            stmt.setString(7, student.getYearLevel());
            stmt.setString(8, student.getProgramCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//addStudent

    //implementation of getAllStudents method
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> student = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                String programCode = rs.getString("major");
                ProgramDAOImpl temp = new ProgramDAOImpl();
                Program program = temp.getProgramByCode(programCode);

                student.add(new Student(
                        rs.getInt("studentId"),
                        rs.getString("lName"),
                        rs.getString("fName"),
                        rs.getString("contactNo"),
                        rs.getString("email"),
                        rs.getString("dateOfBirth"),
                        rs.getString("yearLevel"),
                        program
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    //implementation of getStudentById method
    public Student getStudentById(int studentId) {
        Student student = null;
        String query = "SELECT * FROM student WHERE studentId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId); // Set the ID parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    String programCode = rs.getString("major");
                    ProgramDAOImpl temp = new ProgramDAOImpl();
                    Program program = temp.getProgramByCode(programCode);

                    student = new Student(
                            rs.getInt("studentId"),
                            rs.getString("lName"),
                            rs.getString("fName"),
                            rs.getString("contactNo"),
                            rs.getString("email"),
                            rs.getString("dateOfBirth"),
                            rs.getString("yearLevel"),
                            program
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student; // Returns the student object or null if not found
    }

    //implementation of updateStudent method
    public void updateStudent(Student student) {
        String query = "UPDATE student SET lName=?, fName=?, contactNo=?, email=?, dateOfBirth=? WHERE studentId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getLastName());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getContactNo());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getDateOfBirth());
            //stmt.setString(6, student.getYearLevel());
            //stmt.setString(7, student.getProgramCode());
            stmt.setInt(6, student.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}//class StudentDAOImpl