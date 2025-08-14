package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.StaffBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.StaffDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.Employee;
import lk.ijse.florist_pos.final_project.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class StaffBoImpl implements StaffBO {

    StaffDao staffDao = (StaffDao)
            DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.STAFF);

    @Override
    public int getTotalEmployees() throws SQLException {
        return staffDao.getTotalEmployees();
    }

    @Override
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException{
        ArrayList<Employee> employees = staffDao.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee:employees){
            employeeDtos.add(new EmployeeDto(
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getEmployeeSalary(),
                    employee.getEmployeePosition(),
                    employee.getEmployeeEmail()
            )
            );
        }
        return employeeDtos;
    }
}
