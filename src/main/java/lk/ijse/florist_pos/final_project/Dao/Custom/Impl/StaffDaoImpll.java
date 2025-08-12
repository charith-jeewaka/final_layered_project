package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.StaffDao;
import lk.ijse.florist_pos.final_project.Entity.Employee;
import lk.ijse.florist_pos.final_project.dto.EmployeeDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffDaoImpll implements StaffDao {

//    public String getNextFlowerId() throws SQLException {
//        ResultSet resultSet = CrudUtil.execute("select employee_id from employee order by employee_id desc limit 1");
//        char tableCharacter = 'E'; // Use any character Ex:- customer table for C, item table for I
//        if (resultSet.next()) {
//            String lastId = resultSet.getString(1); // "C001"
//            String lastIdNumberString = lastId.substring(1); // "001"
//            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
//            int nextIdNUmber = lastIdNumber + 1; // 2
//            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
//            return nextIdString;
//        }
//        // No data recode in table so return initial primary key
//        return tableCharacter + "001";
//    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from employee");
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()){
            Employee employee = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        return false;
    }

    @Override
    public Employee search(String number) throws SQLException {
        return null;
    }

    @Override
    public int getTotalEmployees() throws SQLException {
        String sql = "SELECT COUNT(employee_id) AS employee_count FROM employee";
        ResultSet rs = CrudUtil.execute(sql);
        int employeeCount = 0;
        if (rs.next()) {
            employeeCount = rs.getInt("employee_count");
        }
        System.out.println("Total employees: " + employeeCount);
        return employeeCount;
    }

}
