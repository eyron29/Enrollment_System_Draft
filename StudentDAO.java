package enrollmentDemo;

import java.util.ArrayList;

public interface StudentDAO {

    //abstract methods
    void addStudent(Student student);

    ArrayList<Student> getAllStudents();

    Student getStudentById(int studentId);

    void updateStudent(Student student);
}
