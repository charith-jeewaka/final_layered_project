package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.Supplier;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDao extends CrudDao<Supplier> {
    List<String> getAllSupplierEmails() throws SQLException ;

}
