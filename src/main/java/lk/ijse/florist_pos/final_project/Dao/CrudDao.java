package lk.ijse.florist_pos.final_project.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao{

     String getNextId() throws SQLException;

     ArrayList<T> getAll() throws SQLException ;

     boolean save(T entity) throws SQLException ;

     boolean delete(String Id) throws SQLException ;

     boolean update(T entity) throws SQLException ;

     T search(String number) throws SQLException ;


}
