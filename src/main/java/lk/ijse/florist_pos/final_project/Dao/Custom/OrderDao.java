package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.OrderDetails;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrderDetails>{

    double getYesterdayTotalSaleForDashBoard() throws SQLException ;

    double getTodayTotalSaleForDashBoard() throws SQLException ;



}
