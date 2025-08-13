package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.FlowerWasteDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FlowerWasteBO extends SuperBO {

    ArrayList<FlowerWasteDto> getAllWastedFlowers() throws SQLException ;

}
