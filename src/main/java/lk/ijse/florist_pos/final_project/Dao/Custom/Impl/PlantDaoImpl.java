package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.PlantDao;
import lk.ijse.florist_pos.final_project.Entity.Plant;
import lk.ijse.florist_pos.final_project.dto.PlantDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlantDaoImpl implements PlantDao {

    public String getNextId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select plant_id from plant order by plant_id desc limit 1");
        char tableCharacter = 'P'; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(1); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
            return nextIdString;
        }
        // No data recode in table so return initial primary key
        return tableCharacter + "001";
    }

    public ArrayList<Plant>getAll() throws SQLException{
        ResultSet resultSet = CrudUtil.execute("select * from plant");
        ArrayList<Plant> plants = new ArrayList<>();
        while (resultSet.next()){
            Plant plant = new Plant(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            plants.add(plant);
        }
        return plants;
    }

    public boolean save(Plant plant) throws SQLException {
        return CrudUtil.execute("insert into plant (plant_id, plant_name, plant_height, plant_price, plant_varient, plant_available_qty) values (?,?,?,?,?,?)",
                plant.getPlantId(),
                plant.getPlantName(),
                plant.getPlantHeight(),
                plant.getPlantPrice(),
                plant.getPlantVarient(),
                plant.getPlantAvailableQty()
                );
    }

    public boolean update(Plant plant) throws SQLException {
        return CrudUtil.execute(
                "UPDATE plant SET plant_id = ?, plant_name = ?, plant_height = ?, plant_price = ?, " +
                        "plant_varient = ?, plant_available_qty = ?, plant_registered_time = ? WHERE plant_id = ?;",
                plant.getPlantId(),
                plant.getPlantName(),
                plant.getPlantHeight(),
                plant.getPlantPrice(),
                plant.getPlantVarient(),
                plant.getPlantAvailableQty(),
                plant.getPlantRegisteredTime(),
                plant.getPlantId() // This is the WHERE condition ID
        );
    }

    @Override
    public Plant search(String number) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String plantId) throws SQLException {
        return CrudUtil.execute(
                "delete from plant where plant_id=?",
                plantId
        );
    }

//    public boolean reducePlantQty(Connection con, String plantId, int qty) throws SQLException {
//        String sql = "UPDATE plant SET plant_available_qty = plant_available_qty - ? WHERE plant_id = ?";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, qty);
//        ps.setString(2, plantId);
//        return ps.executeUpdate() > 0;
//    }


    public boolean reduceQty(String plantId, int qtyToReduce, Connection connection) throws SQLException {
        String sql = "UPDATE plant SET plant_available_qty = plant_available_qty - ? WHERE plant_id = ? AND plant_available_qty >= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, qtyToReduce);
        pstm.setString(2, plantId);
        pstm.setInt(3, qtyToReduce);
        return pstm.executeUpdate() > 0;
    }
    public  int getTotalPlantQty() throws SQLException {
        String sql = "SELECT COUNT(plant_id) AS plant_count FROM plant";
        ResultSet rs = CrudUtil.execute(sql);
        int plantCount = 0;
        if (rs.next()) {
            plantCount = rs.getInt("plant_count");
        }
        return plantCount;
    }
}
