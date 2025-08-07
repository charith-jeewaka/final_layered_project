package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.Flower;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FlowerDao extends CrudDao<Flower>{

    public String getNextFlowerId() throws SQLException ;

    public void updateFlowerLifeStatus() throws SQLException ;

}
