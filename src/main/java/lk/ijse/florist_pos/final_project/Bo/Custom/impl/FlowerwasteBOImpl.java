package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.FlowerWasteBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.FlowerWasteDao;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.FlowerWasteDaoImpl;
import lk.ijse.florist_pos.final_project.Entity.FlowerWaste;
import lk.ijse.florist_pos.final_project.dto.FlowerWasteDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FlowerwasteBOImpl implements FlowerWasteBO {

    FlowerWasteDao flowerWasteDao = new FlowerWasteDaoImpl();

    @Override
    public ArrayList<FlowerWasteDto> getAllWastedFlowers() throws SQLException {
        ArrayList<FlowerWaste> flowerWastes = flowerWasteDao.getAll();
        ArrayList<FlowerWasteDto> flowerWasteDtos = new ArrayList<>();
        for (FlowerWaste flowerWaste:flowerWastes){
            flowerWasteDtos.add(new FlowerWasteDto(flowerWaste.getWastedId(),flowerWaste.getFlowerId(),
                    flowerWaste.getFlowerName(),flowerWaste.getFlowerQty(),
                    flowerWaste.getReason(),flowerWaste.getWasteDate()));
        }
        return flowerWasteDtos;
    }

}
