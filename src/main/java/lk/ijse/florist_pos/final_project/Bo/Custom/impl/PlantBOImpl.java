package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.PlantBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.PlantDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.Plant;
import lk.ijse.florist_pos.final_project.dto.PlantDto;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlantBOImpl implements PlantBO {

    PlantDao plantDao =
            (PlantDao) DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.PLANT);

    @Override
    public ArrayList<PlantDto> getAllPlants() throws SQLException {
        ArrayList<Plant> plants = plantDao.getAll();
        ArrayList<PlantDto> plantDtos = new ArrayList<>();
        for (Plant plant:plants){
            plantDtos.add(new PlantDto(plant.getPlantId(),plant.getPlantName(),
                    plant.getPlantHeight(),plant.getPlantPrice(),
                    plant.getPlantVarient(),plant.getPlantAvailableQty(),
                    plant.getPlantRegisteredTime()
            ));
        }
        return plantDtos;
    }

    @Override
    public boolean savePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException {
        return plantDao.save(
                new Plant(
                        plantDto.getPlantId(),
                        plantDto.getPlantName(),
                        plantDto.getPlantHeight(),
                        plantDto.getPlantPrice(),
                        plantDto.getPlantVarient(),
                        plantDto.getPlantAvailableQty(),
                        plantDto.getPlantRegisteredTime()
                )
        );
    }

    @Override
    public boolean updatePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException {
        return plantDao.update(new Plant(
                plantDto.getPlantId(),
                plantDto.getPlantName(),
                plantDto.getPlantHeight(),
                plantDto.getPlantPrice(),
                plantDto.getPlantVarient(),
                plantDto.getPlantAvailableQty(),
                plantDto.getPlantRegisteredTime()

                )
        );
    }

    @Override
    public boolean deletePlant(String id) throws SQLException, ClassNotFoundException {
        return plantDao.delete(id);
    }

    @Override
    public String getNextPlantId() throws SQLException, ClassNotFoundException {
        return plantDao.getNextId();
    }

    @Override
    public int getTotalPlantQty() throws SQLException {
         return plantDao.getTotalPlantQty();
    }

}
