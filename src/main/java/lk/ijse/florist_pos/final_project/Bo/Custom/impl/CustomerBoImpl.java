package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.CustomerBo;
import lk.ijse.florist_pos.final_project.Dao.Custom.CustomerDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.Customer;
import lk.ijse.florist_pos.final_project.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo{

    CustomerDao customerDao =
            (CustomerDao) DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.CUSTOMER);

    public ArrayList<CustomerDto> getAll() throws SQLException{
        ArrayList<Customer> customers = customerDao.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer:customers){
            customerDtos.add(new CustomerDto(customer.getCustomerId(),customer.getCustomerName(),
                    customer.getMobileNumber(),customer.getEmail(),
                    customer.getCustomerAddress(),customer.getRegisteredTime()));
        }
        return customerDtos;
    }
}

//customerId;
//private String customerName;
//private String mobileNumber;
//private String email;
//private String customerAddress;
//private String registeredTime;
