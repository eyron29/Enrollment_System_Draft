package enrollmentDemo;

import java.util.ArrayList;

public interface ProgramDAO {

    //abstract methods
    void addProgram(Program program);

    Program getProgramByCode(String programCode);

    ArrayList<Program> getPrograms();

}//interface ProgramDAO
