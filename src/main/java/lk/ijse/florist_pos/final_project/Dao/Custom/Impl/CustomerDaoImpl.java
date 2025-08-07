package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.CustomerDao;
import lk.ijse.florist_pos.final_project.Entity.Customer;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public String getNextId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select customer_id from customer order by customer_id desc limit 1");
        char tableCharacter = 'C'; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(1); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
            return nextIdString;
        }
        return tableCharacter + "001";
    }
    @Override
    public ArrayList<Customer> getAll() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select * from customer");

        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            customers.add(customer);
        }
        System.out.println("hello");

        return customers;

    }

    @Override
    public boolean save(Customer customer) throws SQLException {
        return CrudUtil.execute(
                "insert into customer (customer_id, name, phone_number, email, address) values (?,?,?,?,?)",
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getMobileNumber(),
                customer.getEmail(),
                customer.getCustomerAddress()
        );
    }
    @Override
    public void delete(String customerId) throws SQLException {
        CrudUtil.execute("delete from customer where customer_id=?", customerId);
    }


    @Override
    public void update(Customer customer) throws SQLException {
        CrudUtil.execute(
                "UPDATE customer SET name = ?, phone_number = ?, email = ?, address = ? WHERE customer_id = ?",
                customer.getCustomerName(),
                customer.getMobileNumber(),
                customer.getEmail(),
                customer.getCustomerAddress(),
                customer.getCustomerId()
        );

    }
    @Override
    public Customer search(String number) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE phone_number = ?;", number);

        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        } else {
            return null;
        }
    }


}
