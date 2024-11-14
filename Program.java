package enrollmentDemo;

public class Program{

    //private instance variables
    private String programCode;
    private String programName;

    //parameterized constructor
    public Program(String programCode, String programName) {
        this.programCode = programCode;
        this.programName = programName;
    }

    //setters
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    //getters
    public String getProgramCode() {
        return programCode;
    }

    public String getProgramName() {
        return programName;
    }

    //toString method
    public String toString() {
        return programCode + " | " + programName;
    }

}//class Program