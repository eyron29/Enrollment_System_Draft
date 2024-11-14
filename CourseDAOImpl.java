package enrollmentDemo;

import java.sql.*;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {

    //implementation fo addCourse method
    public void addCourse(Course course) {
        String query = "INSERT INTO course (courseCode, courseName, credits, faculty) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCredits());

            if (course.getInstructor() != null) {
                stmt.setInt(4, course.getInstructorId());
            } else {
                stmt.setNull(4, Types.INTEGER);  // Set null if there's no instructor assigned
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //implementation fo getOfferedCourses method
    public ArrayList<Course> getOfferedCourses() {
        ArrayList<Course> offeredCourses = new ArrayList<>();
        String query = "SELECT * FROM course";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                int instructorId = rs.getInt("faculty");
                InstructorDAOImpl instructorDao = new InstructorDAOImpl();
                Instructor instructor = instructorDao.getInstructorById(instructorId);

                offeredCourses.add(new Course(
                        rs.getString("courseCode"),
                        rs.getString("courseName"),
                        rs.getInt("credits"),
                        instructor
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offeredCourses;
    }

    //find the Course, then enroll to student
    //implementation fo getCourseByCode method
    public Course getCourseByCode(String courseCode) {
        Course course = null;
        String query = "SELECT courseCode, courseName, credits FROM course WHERE courseCode = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, courseCode);

            // Execute the query and process the result set
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    String code = rs.getString("courseCode");
                    String name = rs.getString("courseName");
                    int credits = rs.getInt("credits");

                    // Create a Course object with the retrieved data
                    course = new Course(code, name, credits);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

}//class CourseDAO
