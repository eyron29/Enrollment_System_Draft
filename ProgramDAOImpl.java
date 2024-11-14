package enrollmentDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramDAOImpl implements ProgramDAO {

    //implementation of addProgram method
    public void addProgram(Program program) {
        String query = "INSERT INTO program (programCode, programName) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, program.getProgramCode());
            stmt.setString(2, program.getProgramName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//addProgram

    //implementation of getProgramByCode
    public Program getProgramByCode(String programCode) {
        Program program = null;
        String query = "SELECT programCode, programName FROM program WHERE programCode = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, programCode);

            // Execute the query and process the result set
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String code = rs.getString("programCode");
                    String name = rs.getString("programName");

                    // Create a Program object with the retrieved data
                    program = new Program(code, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return program;
    }//getProgram

    //implementation of getPrograms method
    public ArrayList<Program> getPrograms() {
        ArrayList<Program> programs = new ArrayList<>();
        String query = "SELECT * FROM program";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                programs.add(new Program(
                        rs.getString("programCode"),
                        rs.getString("programName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }//get programs

}//class ProgramDAOImpl