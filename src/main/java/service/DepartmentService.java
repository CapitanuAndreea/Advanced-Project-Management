package service;

import java.sql.Connection;
public interface DepartmentService {
    public void addDepartment(Connection con);
    public void getDepartments(Connection con);
    public void updateDepartmentByName(Connection con);
    public void deleteDepartmentByName(Connection con);
}
