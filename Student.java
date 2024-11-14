package enrollmentDemo;

//ArrayList is used to store enrolledCourses by a Student
import java.util.ArrayList;

public class Student extends Person {

    //private instance variables
    private String yearLevel;
    private String dateOfBirth;
    private Program program;
    private static ArrayList<Course> enrolledCourses;

    //parameterized constructor
    public Student(int personId,String lName, String fName, String contactNo, String email, String dateOfBirth, String yearLevel, Program program) {
        //call to superclass constructor
        super(personId, lName, fName, contactNo, email);
        //unique attributes
        this.dateOfBirth = dateOfBirth;
        this.yearLevel = yearLevel;
        this.program = program;
        this.enrolledCourses = new ArrayList<>();
    }

    //non-parameterized constructor
    public Student() {
        // Call the superclass constructor with default values
        super(0, "", "", "", "");
        this.dateOfBirth = "";
        this.yearLevel = "";
        this.program = null;
        this.enrolledCourses = new ArrayList<>();
    }

    //setters
    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    //getters
    public String getYearLevel() {
        return yearLevel;
    }   
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProgramCode(){
        return program.getProgramCode();
    }

    public String toString() {
        return String.format("%-10s | %-10s, %-15s |  %-10s |  %-25s | %-15s",
                getId(),
                getLastName().toUpperCase(),
                getFirstName(),
                getProgramCode(),
                getEmail(),
                getContactNo());
    }

    //abstract method implementation
    public void printDetails() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\tSTUDENT INFORMATION");
        System.out.println("STUDENT ID   |     LAST NAME     |       FIRST NAME      |      CONTACT NO      |           EMAIL               |         YEAR LEVEL      |     MAJOR");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20d%-25s%-25s%-25s%-25s%-25s%-25s%n", getId(), getLastName(), getFirstName(), getContactNo(), getEmail(), getYearLevel(), getProgramCode());
    }

    //TO EDIT PA
    public void printDetails1() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("STUDENT INFORMATION");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-10s: %s%n", "Student No", getId());
        System.out.printf("%-10s: %s, %s%n", "Name", getLastName().toUpperCase(), getFirstName());

    }

    //ARRAYLIST OPERATIONS
    //for Enrollment class
    public ArrayList<Course> getEnrolledCourses(){
        return enrolledCourses;
    }

    //display list of enrolled courses
    public void printList() {
        if(enrolledCourses.isEmpty()){
            System.out.println("List is empty...");
        }else{
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.printf("%-15s | %-25s | %-6s | %-20s%n", "COURSE CODE", "COURSE TITLE", "UNITS", "FACULTY");
            System.out.println("------------------------------------------------------------------------------------------");

            for (Course course : enrolledCourses) {
                System.out.printf("%-15s | %-25s | %-6d | %-20s%n",
                        course.getCourseCode(),
                        course.getCourseName(),
                        course.getCredits(),
                        course.getInstructorName());
            }
            System.out.println("------------------------------------------------------------------------------------------");
        }
    }//printList

}//class Student