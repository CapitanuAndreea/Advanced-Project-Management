package service;

import java.sql.Connection;

public interface TaskService {
    public void addTask(Connection con);
    public void getTaskByDescription(Connection con);
    public void updateTaskByDescription(Connection con);
    public void deleteTaskByDescription(Connection con);
    public void getTasksByEmployee(Connection con);
    public void getTasksByProject(Connection con);
}
