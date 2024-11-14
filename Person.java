package enrollmentDemo;

//abstract class, use for common states and behaviors of both Student & Instructor
//cannot be instantiated
public abstract class Person {

    private int personId;
    private String lName;
    private String fName;
    private String contactNo;
    private String email;

    //superclass constructor
    public Person(int personId, String lName, String fName, String contactNo, String email){
        this.personId = personId;
        this.lName = lName;
        this.fName = fName;
        this.contactNo = contactNo;
        this.email = email;
    }

    //setters
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setLastName(String lName){
        this.lName = lName;
    }

    public void setFirstName(String fName){
        this.fName = fName;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //getters
    public int getId() {
        return personId ;
    }

    public String getLastName() {
        return lName;
    }

    public String getFirstName() {
        return fName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    //abstract methods: implemented in subclasses

    public abstract String toString();

    public abstract void printDetails();


}//abstract class Person
