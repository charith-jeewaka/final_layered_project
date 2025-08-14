package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.EmployeeDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StaffBO extends SuperBO {

    int getTotalEmployees() throws SQLException ;

    ArrayList<EmployeeDto> getAllEmployees() throws SQLException;
}
