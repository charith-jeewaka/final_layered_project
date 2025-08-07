package lk.ijse.florist_pos.final_project.Dao;

import lk.ijse.florist_pos.final_project.dto.CustomerDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao extends SuperDao{

    public String getNextCustomerId() throws SQLException ;

    public ArrayList<CustomerDto> getAllCustomer() throws SQLException ;

    public boolean saveCustomer(CustomerDto customerDTO) throws SQLException ;

    public void deleteCustomer(String customerId) throws SQLException ;

    public void updateCustomer(CustomerDto customerDTO) throws SQLException ;

    public CustomerDto searchCustomer(String number) throws SQLException ;
}
