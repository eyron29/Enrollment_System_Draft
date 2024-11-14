package enrollmentDemo;

public class Course{

    //private instance variables
    private String courseCode;
    private String courseName;
    private int credits;
    private Instructor instructor;

    //parameterized constructor w/o an Instructor
    public Course(String courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
    }

    //parameterized constructor with an Instructor
    public Course(String courseCode, String courseName, int credits, Instructor instructor) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;
    }

    //setters
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    //getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public int getInstructorId(){
        return instructor.getId();
    }

    public String getInstructorName(){
        if(instructor != null) {
            return instructor.getLastName().toUpperCase() + ", " + instructor.getFirstName();
        } else {
            return "No Instructor Assigned";
        }
    }

    //to String method
    public String toString(){
        return courseCode + "   |   \t" + courseName;
    }

}//class Course
