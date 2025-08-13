package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.Entity.Customer;
import lk.ijse.florist_pos.final_project.dto.CustomerDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBO {
     ArrayList<CustomerDto> getAll() throws SQLException ;

    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    public String getNextCustomerId() throws SQLException, ClassNotFoundException ;

    public CustomerDto searchCustomerByPhone(String phoneNumber) throws SQLException ;

}
