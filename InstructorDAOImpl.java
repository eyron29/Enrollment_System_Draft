package enrollmentDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorDAOImpl implements InstructorDAO {

    //implementation of addInstructor method
    public void addInstructor(Instructor instructor) {
        String query = "INSERT INTO instructor (instructorId, lName, fName, contactNo, email, hiredDate, department) VALUES (?, ?, ? ,?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, instructor.getId());
            stmt.setString(2, instructor.getLastName());
            stmt.setString(3, instructor.getFirstName());
            stmt.setString(4, instructor.getContactNo());
            stmt.setString(5, instructor.getEmail());
            stmt.setString(6, instructor.getHiredDate());
            stmt.setString(7, instructor.getDeptCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//add Instructor

    //implementation of getAllInstructors method
    public ArrayList<Instructor> getAllInstructors() {
        ArrayList<Instructor> instructor = new ArrayList<>();
        String query = "SELECT * FROM instructor";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                String deptCode = rs.getString("department");
                DepartmentDAOImpl temp = new DepartmentDAOImpl();
                Department department = temp.getByDeptCode(deptCode);

                instructor.add(new Instructor(
                        rs.getInt("instructorId"),
                        rs.getString("lName"),
                        rs.getString("fName"),
                        rs.getString("contactNo"),
                        rs.getString("email"),
                        rs.getString("hiredDate"),
                        department
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructor;
    }

    //implementation of getInstructorById method
    public Instructor getInstructorById(int insId) {
        Instructor instructor = null;
        String query = "SELECT * FROM instructor WHERE instructorId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, insId); // Set the ID parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    String departmentCode = rs.getString("department");
                    DepartmentDAOImpl temp = new DepartmentDAOImpl();
                    Department department = temp.getByDeptCode(departmentCode);

                    instructor = new Instructor(
                            rs.getInt("instructorId"),
                            rs.getString("lName"),
                            rs.getString("fName"),
                            rs.getString("contactNo"),
                            rs.getString("email"),
                            rs.getString("hiredDate"),
                            department
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructor; // Returns the instructor object or null if not found
    }

}//class InstructorDAOImpl
