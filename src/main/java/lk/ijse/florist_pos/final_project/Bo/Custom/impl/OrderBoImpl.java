package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.OrderBO;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.Dao.Custom.FlowerDao;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.OrderDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.OrderDao;
import lk.ijse.florist_pos.final_project.Dao.Custom.PlantDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.OrderDetails;
import lk.ijse.florist_pos.final_project.dto.OrderDetailsDto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl implements OrderBO {

      OrderDao orderDao =
            (OrderDao) DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.ORDER);
      PlantDao plantDao =
            (PlantDao) DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.PLANT);
      FlowerDao flowerDao =
            (FlowerDao) DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.FLOWER);

    @Override
    public boolean placeOrder(List<OrderDetailsDto> orderDetailsList) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean isSuccess = false;

        try {
            connection.setAutoCommit(false);

            for (OrderDetailsDto dto : orderDetailsList) {

                OrderDetails orderDetails = new OrderDetails(
                        dto.getOrderId(),
                        dto.getCustomerName(),
                        dto.getItemName(),
                        dto.getItemId(),
                        dto.getPaymentType(),
                        dto.getItemQty(),
                        dto.getTotalAmount(),
                        dto.getHandleBy(),
                        dto.getTotalBill()
                );

                if (!orderDao.save(orderDetails)) {
                    connection.rollback();
                    return false;
                }

                String itemId = dto.getItemId();
                int qtyToReduce = Integer.parseInt(dto.getItemQty());

                if (itemId.startsWith("P")) {
                    if (!plantDao.reduceQty(itemId, qtyToReduce, connection)) {
                        connection.rollback();
                        return false;
                    }
                } else if (itemId.startsWith("F")) {
                    if (!flowerDao.reduceQty(itemId, qtyToReduce)) {
                        connection.rollback();
                        return false;
                    }
                }
            }

            if (!OrderDaoImpl.insertOrderSummary()) {
                connection.rollback();
                return false;
            }
            connection.commit();
            isSuccess = true;

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return isSuccess;
    }

    @Override
    public double getYesterdayTotalSaleForDashBoard() throws SQLException {
        return orderDao.getYesterdayTotalSaleForDashBoard();
    }

    @Override
    public double getTodayTotalSaleForDashBoard() throws SQLException {
        return orderDao.getTodayTotalSaleForDashBoard();
    }

    @Override
    public String getNextOrderId() throws SQLException {
        return orderDao.getNextId();
    }
}
