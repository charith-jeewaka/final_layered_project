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

    @Override
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



    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDao.save(
                new Customer(
                        customerDto.getCustomerId(),
                        customerDto.getCustomerName(),
                        customerDto.getMobileNumber(),
                        customerDto.getEmail(),
                        customerDto.getCustomerAddress(),
                        customerDto.getRegisteredTime()));
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDTO) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getMobileNumber(),
                customerDTO.getEmail(),
                customerDTO.getCustomerAddress(),
                customerDTO.getRegisteredTime()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDao.getNextId();
    }

    @Override
    public CustomerDto searchCustomerByPhone(String phoneNumber) throws SQLException {
        Customer customer = customerDao.search(phoneNumber);

        if (customer != null) {
            // Convert Entity → DTO
            return new CustomerDto(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getMobileNumber(),
                    customer.getEmail(),
                    customer.getCustomerAddress(),
                    customer.getRegisteredTime()
            );
        }
        return null;
    }
}




