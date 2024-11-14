package enrollmentDemo;

public class Department {

    //private instance variables
    private String deptCode;
    private String deptName;

    //parameterized constructor
    public Department(String deptCode, String deptName) {
        this.deptCode = deptCode;
        this.deptName = deptName;
    }

    //setters
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    //getters
    public String getDeptCode() {
        return deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    //toString method
    public String toString(){
        return deptCode + " -  " + deptName;
    }

}//class Department
