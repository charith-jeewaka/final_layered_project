package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.CustomerDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBO {

     ArrayList<CustomerDto> getAllCustomers() throws SQLException ;

     boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException ;

     boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException ;

     boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

     String getNextCustomerId() throws SQLException, ClassNotFoundException ;

     CustomerDto searchCustomerByPhone(String phoneNumber) throws SQLException ;

     String getCustomerName(String number) throws SQLException;


}
