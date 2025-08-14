package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.Customer;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer> {

    String getCustomerName(String number) throws SQLException ;


}
