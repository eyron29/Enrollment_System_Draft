package enrollmentDemo;

import java.util.ArrayList;

//extends abstract Person class
public class Instructor extends Person{

    //private instance variables
    private String hiredDate;
    private Department department;
    private ArrayList<Course> assignedCourses;

    //parameterized constructor
    public Instructor(int personId, String lName, String fName, String contactNo, String email, String hiredDate, Department department) {
        //call to superclass constructor
        super(personId, lName, fName, contactNo, email);
        //unique attributes
        this.hiredDate = hiredDate;
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }

    //non parameterized constructor
    public Instructor() {
        // Call the superclass constructor with default values
        super(0, "", "", "", "");
        // Initialize unique attributes with default values
        this.hiredDate = "";
        this.department = null;
    }

    //setters
    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }

    public void setDepartment(Department d) {
        this.department = d;
    }

    //getters
    public String getHiredDate() {
        return hiredDate;
    }

    public String getDeptCode(){
        return department.getDeptCode();
    }

    //@Override
    //implementatio of abstract method
    public String toString(){
        return getId() + " " + getLastName().toUpperCase() + ", " + getFirstName();
    }

    //@Override
    //implementatio of abstract method
    public void printDetails(){
        System.out.println("Instructor ID: " + getId());
        System.out.println("Name: " + getLastName().toUpperCase() + " " + getFirstName());
    }

}//class Instructor