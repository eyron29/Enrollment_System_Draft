package enrollmentDemo;

import java.util.ArrayList;

public interface CourseDAO {

    //abstract methods
    void addCourse(Course course);

    ArrayList<Course> getOfferedCourses();

    Course getCourseByCode(String courseCode);
}
