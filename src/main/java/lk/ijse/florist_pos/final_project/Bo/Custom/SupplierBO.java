package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    List<String> getAllSupplierEmails() throws SQLException;
}
