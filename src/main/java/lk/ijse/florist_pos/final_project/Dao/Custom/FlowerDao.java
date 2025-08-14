package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.Flower;
import java.sql.SQLException;

public interface FlowerDao extends CrudDao<Flower>{

     void updateFlowerLifeStatus() throws SQLException ;

     boolean reduceQty(String flowerId, int qtyToReduce) throws SQLException ;

     int getTotalFlowerQty() throws SQLException ;

}
