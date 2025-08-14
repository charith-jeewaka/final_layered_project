package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.PlantDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlantBO extends SuperBO {

    ArrayList<PlantDto> getAllPlants() throws SQLException;

    boolean savePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException ;

    boolean updatePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException ;

    boolean deletePlant(String id) throws SQLException, ClassNotFoundException ;

    String getNextPlantId() throws SQLException, ClassNotFoundException ;

    int getTotalPlantQty() throws SQLException ;

}
