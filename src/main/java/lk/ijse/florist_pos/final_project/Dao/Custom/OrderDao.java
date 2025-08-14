package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.OrderDetails;
import lk.ijse.florist_pos.final_project.dto.Tm.OrderStats;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDao extends CrudDao<OrderDetails>{

    double getYesterdayTotalSaleForDashBoard() throws SQLException ;

    double getTodayTotalSaleForDashBoard() throws SQLException ;

    public List<OrderStats> getOrderCountsByCustomer() throws SQLException;



}
