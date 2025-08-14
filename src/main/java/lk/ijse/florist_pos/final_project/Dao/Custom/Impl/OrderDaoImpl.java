package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.OrderDao;
import lk.ijse.florist_pos.final_project.Entity.OrderDetails;
import lk.ijse.florist_pos.final_project.dto.OrderItemDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    @Override
    public String getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");
        char tableCharacter = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(tableCharacter + "%03d", nextIdNumber);
        }
        return tableCharacter + "001";
    }



    public OrderItemDto getItemDetails(String code) throws SQLException {
        ResultSet rs;
        if (code.startsWith("P")) {
            rs = CrudUtil.execute("SELECT plant_name AS item_name, plant_available_qty AS qty, plant_price AS price FROM plant WHERE plant_id = ?", code);
        } else if (code.startsWith("F")) {
            rs = CrudUtil.execute("SELECT flower_name AS item_name, flower_available_qty AS qty, flower_price AS price FROM flower WHERE flower_id = ?", code);
        } else {
            throw new IllegalArgumentException("Invalid code format: " + code);
        }

        if (rs.next()) {
            String name = rs.getString("item_name");
            int qty = rs.getInt("qty");
            double unitPrice = rs.getDouble("price");
            return new OrderItemDto(name, unitPrice, qty);
        }
        return null;
    }


    public static boolean insertOrderSummary() throws SQLException {
        String sql = """
            INSERT INTO order_item_details (order_id, customer_name, total_bill, handled_by, order_date)
            SELECT 
                o1.order_id,
                o1.customer_name,
                o1.total_bill,
                o1.handled_by,
                o1.order_date
            FROM orders o1
            WHERE NOT EXISTS (
                SELECT 1 
                FROM order_item_details od 
                WHERE od.order_id = o1.order_id
            )
            AND o1.single_order_id = (
                SELECT MIN(o2.single_order_id)
                FROM orders o2
                WHERE o2.order_id = o1.order_id
            )
        """;

        return CrudUtil.execute(sql);
    }

    public static BigDecimal getTodayTotalSales() throws SQLException {
        String sql = "SELECT SUM(CAST(total_bill AS DECIMAL(10, 2))) AS today_total " +
                "FROM order_item_details " +
                "WHERE DATE(order_date) = CURDATE()";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return resultSet.getBigDecimal("today_total") != null
                    ? resultSet.getBigDecimal("today_total")
                    : BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getYesterdayTotalSales() throws SQLException {
        String qry = "SELECT SUM(CAST(total_bill AS DECIMAL(10, 2))) AS yesterday_total FROM order_item_details WHERE" +
                " DATE(order_date) = CURDATE() - INTERVAL 1 DAY";


        ResultSet resultSet = CrudUtil.execute(qry);

        if (resultSet.next()) {
            return resultSet.getBigDecimal("yesterday_total") != null
                    ? resultSet.getBigDecimal("yesterday_total")
                    : BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    public static double getTodayTotalSaleForDashBoard() throws SQLException {
        String sql = "SELECT SUM(CAST(total_bill AS DECIMAL(10,2))) AS today_sales FROM order_item_details WHERE DATE(order_date) = CURDATE()";

        ResultSet rs = CrudUtil.execute(sql);
        double todaySale = 0;
        if (rs.next()) {
            todaySale = rs.getInt("today_sales");
        }
        return todaySale;
    }

    public static double getYesterdayTotalSaleForDashBoard() throws SQLException {
        String sql = "SELECT SUM(CAST(total_bill AS DECIMAL(10,2))) AS yesterday_sales FROM order_item_details WHERE DATE(order_date) = CURDATE() - INTERVAL 1 DAY";

        ResultSet rs = CrudUtil.execute(sql);
        double yesterdaySale = 0;
        if (rs.next()) {
            yesterdaySale = rs.getInt("yesterday_sales");
        }
        return yesterdaySale;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO orders (order_id, customer_name, item_name, item_id, payment_type," +
                        " item_qty, total_amount, handled_by, total_bill) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getOrderId(),
                entity.getCustomerName(),
                entity.getItemName(),
                entity.getItemId(),
                entity.getPaymentType(),
                entity.getItemQty(),
                entity.getTotalAmount(),
                entity.getHandleBy(),
                entity.getTotalBill()
        );
    }


    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException {
        return false;
    }

    @Override
    public OrderDetails search(String number) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException {
        return null;
    }
}
