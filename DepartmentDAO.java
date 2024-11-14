package enrollmentDemo;

import java.util.ArrayList;

public interface DepartmentDAO {

    //abtract methods
    void addDepartment(Department department);

    ArrayList<Department> getDepartments();

    Department getByDeptCode(String deptCode);

}//interface DepartmentDAO
