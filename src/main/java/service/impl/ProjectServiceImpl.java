package service.impl;

import model.Project;
import service.ProjectService;

import java.sql.*;
import java.util.Scanner;

public class ProjectServiceImpl implements ProjectService {

    @Override
    public void addProject(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter project title: ");
            String title = scanner.nextLine();
            System.out.print("Enter project type: ");
            String type = scanner.nextLine();
            System.out.print("Enter project date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine();

            String insertSql = "INSERT INTO PROJECT (TITLE, TYPE, DATE) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(insertSql);

            pstmt.setString(1, title);
            pstmt.setString(2, type);
            pstmt.setDate(3, Date.valueOf(dateString));

            pstmt.executeUpdate();
            System.out.println("Project added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void getProjects(Connection con){
        try {
            String selectSql = "SELECT ID, TITLE FROM PROJECT";
            PreparedStatement pstmt = con.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Projects List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("ID"));
                System.out.println("Title: " + rs.getString("TITLE"));
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getProjectByTitle(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter project title: ");
            String title = scanner.nextLine();

            String selectSql = "SELECT * FROM PROJECT WHERE TITLE = ?";
            pstmt = con.prepareStatement(selectSql);

            pstmt.setString(1, title);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Project ID: " + rs.getInt("ID"));
                System.out.println("Title: " + rs.getString("TITLE"));
                System.out.println("Type: " + rs.getString("TYPE"));
                System.out.println("Date: " + rs.getDate("DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateProjectByTitle(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter current project title: ");
            String currentTitle = scanner.nextLine();
            System.out.print("Enter new project title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new project type: ");
            String newType = scanner.nextLine();
            System.out.print("Enter new project date (YYYY-MM-DD): ");
            String newDateString = scanner.nextLine();

            String updateSql = "UPDATE PROJECT SET TITLE = ?, TYPE = ?, DATE = ? WHERE TITLE = ?";
            pstmt = con.prepareStatement(updateSql);

            pstmt.setString(1, newTitle);
            pstmt.setString(2, newType);
            pstmt.setDate(3, Date.valueOf(newDateString));
            pstmt.setString(4, currentTitle);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Project updated successfully!");
            } else {
                System.out.println("Project not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteProjectByTitle(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter project title to delete: ");
            String title = scanner.nextLine();

            String deleteSql = "DELETE FROM PROJECT WHERE TITLE = ?";
            pstmt = con.prepareStatement(deleteSql);

            pstmt.setString(1, title);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Project deleted successfully!");
            } else {
                System.out.println("Project not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
