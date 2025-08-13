package lk.ijse.florist_pos.final_project.Dao.Custom;

import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.Employee;

import java.sql.SQLException;

public interface StaffDao extends CrudDao<Employee> {
    int getTotalEmployees() throws SQLException ;
}
