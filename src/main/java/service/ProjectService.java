package service;

import java.sql.Connection;

public interface ProjectService {
    public void addProject(Connection con);
    public void getProjects(Connection con);
    public void getProjectByTitle(Connection con);
    public void updateProjectByTitle(Connection con);
    public void deleteProjectByTitle(Connection con);
}
