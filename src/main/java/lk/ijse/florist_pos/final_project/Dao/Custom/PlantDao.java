package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.Plant;
import lk.ijse.florist_pos.final_project.dto.OrderItemDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PlantDao extends CrudDao<Plant> {

    boolean reduceQty(String plantId, int qtyToReduce, Connection connection) throws SQLException ;

    int getTotalPlantQty() throws SQLException ;

    OrderItemDto getItemDetails(String code) throws SQLException ;




}
