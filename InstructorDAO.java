package enrollmentDemo;

import java.util.ArrayList;

public interface InstructorDAO {

    //abstract methods
    void addInstructor(Instructor instructor);

    ArrayList<Instructor> getAllInstructors();

    Instructor getInstructorById(int insId);

}//instructorDAO interface
