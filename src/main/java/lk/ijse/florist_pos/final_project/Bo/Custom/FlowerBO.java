package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.FlowerDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FlowerBO extends SuperBO {

    ArrayList<FlowerDto> getAllFlowers() throws SQLException;

    boolean saveFlower(FlowerDto flowerDto) throws SQLException, ClassNotFoundException ;

    boolean updateFlower(FlowerDto flowerDto) throws SQLException, ClassNotFoundException ;

    boolean deleteFlower(String id) throws SQLException, ClassNotFoundException ;

    String getNextFlowerId() throws SQLException, ClassNotFoundException ;

    void updateFlowerLifeStatus() throws SQLException ;

    int getTotalFlowerQty() throws SQLException;


}
