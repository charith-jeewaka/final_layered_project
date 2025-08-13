package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.Plant;

import java.sql.Connection;
import java.sql.SQLException;

public interface PlantDao extends CrudDao<Plant> {

    boolean reduceQty(String plantId, int qtyToReduce, Connection connection) throws SQLException ;

    int getTotalPlantQty() throws SQLException ;

}
