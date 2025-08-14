package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.OrderDaoImpl;
import lk.ijse.florist_pos.final_project.dto.OrderDetailsDto;
import lk.ijse.florist_pos.final_project.dto.Tm.OrderStats;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {

    boolean placeOrder(List<OrderDetailsDto> orderDetailsList) throws SQLException ;

    double getYesterdayTotalSaleForDashBoard() throws SQLException ;

    double getTodayTotalSaleForDashBoard() throws SQLException ;

    String getNextOrderId() throws SQLException ;

    List<OrderStats> getOrderCountsByCustomer() throws SQLException ;



}
