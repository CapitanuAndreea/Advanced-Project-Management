package service;

import java.sql.Connection;

public interface EmployeeService {
    public void addEmployee(Connection con);
    public void getEmployees(Connection con);
    public void getEmployeeByName(Connection con);
    public void updateEmployeeByName(Connection con);
    public void deleteEmployeeByName(Connection con);
    public void getEmployeeByDepartment(Connection con);
}
