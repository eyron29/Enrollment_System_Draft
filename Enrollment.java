package enrollmentDemo;

//for ArrayList of Student(enrolledCourses)
public class Enrollment {

    //private instance variables
    private Student student;
    private Course course;

    //parameterized constructor
    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    //enroll method: add courses to Student list
    public void enroll() {
        student.getEnrolledCourses().add(course);
    }

}//class Enrollment
