package lk.ijse.florist_pos.final_project.Dao;

import lk.ijse.florist_pos.final_project.Entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao{

    public String getNextId() throws SQLException ;

    public ArrayList<T> getAll() throws SQLException ;

    public boolean save(T customerDTO) throws SQLException ;

    public void delete(String Id) throws SQLException ;

    public void update(T customerDTO) throws SQLException ;

    public T search(String number) throws SQLException ;
}
