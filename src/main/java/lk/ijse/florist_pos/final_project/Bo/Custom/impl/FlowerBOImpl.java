package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.FlowerBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.FlowerDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.Flower;
import lk.ijse.florist_pos.final_project.dto.FlowerDto;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlowerBOImpl implements FlowerBO {

    FlowerDao flowerDao =
            (FlowerDao) DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.FLOWER);


    @Override
    public ArrayList<FlowerDto> getAllFlowers() throws SQLException{
        ArrayList<Flower> flowers = flowerDao.getAll();
        ArrayList<FlowerDto> flowerDtos = new ArrayList<>();
        for (Flower flower:flowers){
            flowerDtos.add(new FlowerDto(flower.getFlowerId(),flower.getFlowerName(),
                    flower.getFlowerCategory(),flower.getFlowerPrice(),
                    flower.getFlowerStatus(),flower.getFlowerAvailableQty(),
                    flower.getFlowerEnteredTime()
            ));
        }
        return flowerDtos;
    }


    @Override
    public boolean saveFlower(FlowerDto flowerDto) throws SQLException, ClassNotFoundException {
        return flowerDao.save(
                new Flower(
                        flowerDto.getFlowerId(),
                        flowerDto.getFlowerName(),
                        flowerDto.getFlowerCategory(),
                        flowerDto.getFlowerPrice(),
                        flowerDto.getFlowerStatus(),
                        flowerDto.getFlowerAvailableQty(),
                        flowerDto.getFlowerEnteredTime()));
    }

    @Override
    public boolean updateFlower(FlowerDto flowerDto) throws SQLException, ClassNotFoundException {
        return flowerDao.update(new Flower(
                flowerDto.getFlowerId(),
                flowerDto.getFlowerName(),
                flowerDto.getFlowerCategory(),
                flowerDto.getFlowerPrice(),
                flowerDto.getFlowerStatus(),
                flowerDto.getFlowerAvailableQty(),
                flowerDto.getFlowerEnteredTime()));
    }

    @Override
    public boolean deleteFlower(String id) throws SQLException, ClassNotFoundException {
        return flowerDao.delete(id);
    }

    @Override
    public String getNextFlowerId() throws SQLException, ClassNotFoundException {
        return flowerDao.getNextId();
    }

    @Override
    public void updateFlowerLifeStatus() throws SQLException {
        flowerDao.updateFlowerLifeStatus();
    }

    @Override
    public int getTotalFlowerQty() throws SQLException {
        return flowerDao.getTotalFlowerQty();
    }

}
