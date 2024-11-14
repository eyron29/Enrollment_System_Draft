package enrollmentDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentDAOImpl implements DepartmentDAO {

    //implementation of addDepartment method
    public void addDepartment(Department department) {
        String query = "INSERT INTO department (departmentCode, departmentName) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, department.getDeptCode());
            stmt.setString(2, department.getDeptName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//addDepartment

    //implementation of getDepartments method
    public ArrayList<Department> getDepartments() {
        ArrayList<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                departments.add(new Department(
                        rs.getString("departmentCode"),
                        rs.getString("departmentName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }//get departments

    //implementation of getByDeptCode
    public Department getByDeptCode(String deptCode) {
        Department department = null;
        String query = "SELECT departmentCode, departmentName FROM department WHERE departmentCode = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, deptCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String code = rs.getString("departmentCode");
                    String name = rs.getString("departmentName");

                    // Create a Department object with the retrieved data
                    department = new Department(code, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return department;
    }
}//class DepartmentDAOImpl