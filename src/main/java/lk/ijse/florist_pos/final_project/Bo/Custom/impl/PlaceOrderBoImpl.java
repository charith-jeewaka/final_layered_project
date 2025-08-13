package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.PlaceOrderBO;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.Dao.Custom.FlowerDao;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.FlowerDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.OrderDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.PlantDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.OrderDao;
import lk.ijse.florist_pos.final_project.Dao.Custom.PlantDao;
import lk.ijse.florist_pos.final_project.Entity.OrderDetails;
import lk.ijse.florist_pos.final_project.dto.OrderDetailsDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBO {

    OrderDao orderDao = new  OrderDaoImpl();
    PlantDao plantDao = new PlantDaoImpl();
    FlowerDao flowerDao = new FlowerDaoImpl();


    @Override
    public boolean placeOrder(List<OrderDetailsDto> orderDetailsList) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean isSuccess = false;

        try {
            connection.setAutoCommit(false); // Start transaction

            for (OrderDetailsDto dto : orderDetailsList) {

                // Call DAO save method instead of writing the query here
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

                if (!orderDao.save(orderDetails)) { // DAO method
                    connection.rollback();
                    return false;
                }

                // 2. Reduce item quantity
                String itemId = dto.getItemId();
                int qtyToReduce = Integer.parseInt(dto.getItemQty());

                if (itemId.startsWith("P")) {
                    if (!plantDao.reduceQty(itemId, qtyToReduce, connection)) {
                        connection.rollback();
                        return false;
                    }
                } else if (itemId.startsWith("F")) {
                    if (!flowerDao.reduceQty(itemId, qtyToReduce, connection)) {
                        connection.rollback();
                        return false;
                    }
                }
            }

            // Insert order summary
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


}
