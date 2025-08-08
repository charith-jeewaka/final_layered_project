package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.FlowerWasteDao;
import lk.ijse.florist_pos.final_project.Entity.FlowerWaste;
import lk.ijse.florist_pos.final_project.dto.FlowerWasteDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlowerWasteDaoImpl implements FlowerWasteDao {

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    public ArrayList<FlowerWaste> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from flower_waste");
        ArrayList<FlowerWaste> flowerWastes = new ArrayList<>();
        while (resultSet.next()){
            FlowerWaste flowerWaste = new FlowerWaste(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            flowerWastes.add(flowerWaste);
        }
        return flowerWastes;
    }

    @Override
    public boolean save(FlowerWaste entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(FlowerWaste entity) throws SQLException {
        return false;
    }

    @Override
    public FlowerWaste search(String number) throws SQLException {
        return null;
    }
}
