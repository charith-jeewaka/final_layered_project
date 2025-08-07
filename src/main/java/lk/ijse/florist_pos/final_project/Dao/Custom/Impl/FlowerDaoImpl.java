package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.FlowerDao;
import lk.ijse.florist_pos.final_project.Entity.Flower;
import lk.ijse.florist_pos.final_project.dto.FlowerDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlowerDaoImpl implements FlowerDao {
    public String getNextFlowerId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select flower_id from flower order by flower_id desc limit 1");
        char tableCharacter = 'F'; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(1); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
            return nextIdString;
        }

        return tableCharacter + "001";
    }

    public ArrayList<Flower> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from flower");
        ArrayList<Flower> flowers = new ArrayList<>();
        while (resultSet.next()){
            Flower flower = new Flower(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            flowers.add(flower);
        }
        return flowers;
    }

    public boolean save(Flower flower) throws SQLException {
        return CrudUtil.execute("INSERT INTO flower (flower_id, flower_name, flower_catagory, flower_price, flower_status, flower_available_qty) VALUES (?, ?, ?, ?, ?, ?)",
        flower.getFlowerId(),
        flower.getFlowerName(),
        flower.getFlowerCategory(),
        flower.getFlowerPrice(),
        flower.getFlowerStatus(),
        flower.getFlowerAvailableQty()
        );
    }

    public boolean update(Flower flower) throws SQLException {
        return CrudUtil.execute(
                "UPDATE flower SET flower_name = ?, flower_catagory = ?, flower_price = ?, flower_available_qty = ? WHERE flower_id = ?",
                flower.getFlowerName(),
                flower.getFlowerCategory(),
                flower.getFlowerPrice(),
                flower.getFlowerAvailableQty(),
                flower.getFlowerId()
        );
    }

    public boolean delete(String flowerId) throws SQLException {
        return CrudUtil.execute("DELETE FROM flower WHERE flower_id = ?", flowerId);
    }

    @Override
    public Flower search(String number) throws SQLException {
        return null;
    }


    public void updateFlowerLifeStatus() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM flower");
        while (resultSet.next()) {
            String flowerId = resultSet.getString("flower_id");
            String flowerName = resultSet.getString("flower_name");
            int qty = Integer.parseInt(resultSet.getString("flower_available_qty"));
            double price = Double.parseDouble(resultSet.getString("flower_price"));
            String status = resultSet.getString("flower_status");
            java.sql.Timestamp enteredTime = resultSet.getTimestamp("flower_entered_time");

            long currentTime = System.currentTimeMillis();
            long timeDiff = currentTime - enteredTime.getTime();
            long days = timeDiff / (1000 * 60 * 60 * 24);

            if (days >= 4) {

                CrudUtil.execute(
                        "INSERT INTO flower_waste (flower_id, wasted_flower_name, wasted_flower_qty, reason) VALUES (?, ?, ?, ?)",
                        flowerId, flowerName, qty, "Expired - Over 4 days"
                );
                CrudUtil.execute("DELETE FROM flower WHERE flower_id = ?", flowerId);
            } else if (days >= 2 && !"Discounted".equalsIgnoreCase(status)) {
                double discountedPrice = price * 0.8;
                CrudUtil.execute(
                        "UPDATE flower SET flower_status = ?, flower_price = ? WHERE flower_id = ?",
                        "Discounted", String.valueOf((int) discountedPrice), flowerId
                );
            }
        }
    }

    public static boolean reduceQty(String flowerId, int qtyToReduce, Connection connection) throws SQLException {
        String sql = "UPDATE flower SET flower_available_qty = flower_available_qty - ? WHERE flower_id = ? AND flower_available_qty >= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, qtyToReduce);
        pstm.setString(2, flowerId);
        pstm.setInt(3, qtyToReduce);
        return pstm.executeUpdate() > 0;
    }

    public static int getTotalFlowerQty() throws SQLException {
        String sql = "SELECT COUNT(flower_id) AS flower_count FROM flower";
        ResultSet rs = CrudUtil.execute(sql);
        int flowerCount = 0;
        if (rs.next()) {
            flowerCount = rs.getInt("flower_count");
        }
        System.out.println("Total flowers: " + flowerCount);
        return flowerCount;
    }


}
