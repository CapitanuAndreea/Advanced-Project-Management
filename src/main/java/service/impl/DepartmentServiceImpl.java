package service.impl;

import model.Department;
import service.DepartmentService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public void addDepartment(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter department name: ");
            String departmentName = scanner.nextLine();

            String insertSql = "INSERT INTO DEPARTMENT (NAME) VALUES (?)";
            pstmt = con.prepareStatement(insertSql);

            pstmt.setString(1, departmentName);

            pstmt.executeUpdate();
            System.out.println("Department added successfully!");
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
    public void getDepartments(Connection con) {
        try {
            String selectSql = "SELECT * FROM DEPARTMENT";
            PreparedStatement pstmt = con.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Departments List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("ID"));
                System.out.println("Name: " + rs.getString("NAME"));
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepartmentByName(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter current department name: ");
            String currentDepartmentName = scanner.nextLine();
            System.out.print("Enter new department name: ");
            String newDepartmentName = scanner.nextLine();

            String updateSql = "UPDATE DEPARTMENT SET NAME = ? WHERE NAME = ?";
            pstmt = con.prepareStatement(updateSql);

            pstmt.setString(1, newDepartmentName);
            pstmt.setString(2, currentDepartmentName);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department updated successfully!");
            } else {
                System.out.println("Department not found!");
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
    public void deleteDepartmentByName(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter department name to delete: ");
            String departmentName = scanner.nextLine();

            String deleteSql = "DELETE FROM DEPARTMENT WHERE NAME = ?";
            pstmt = con.prepareStatement(deleteSql);

            pstmt.setString(1, departmentName);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department deleted successfully!");
            } else {
                System.out.println("Department not found!");
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
