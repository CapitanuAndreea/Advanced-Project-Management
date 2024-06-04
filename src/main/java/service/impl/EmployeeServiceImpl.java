package service.impl;

import model.Employee;
import service.EmployeeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void addEmployee(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter hire date (YYYY-MM-DD): ");
            String hireDateString = scanner.nextLine();
            System.out.print("Enter department name: ");
            String departmentName = scanner.nextLine();

            try {
                String selectDepartmentIdSql = "SELECT ID FROM DEPARTMENT WHERE NAME = ?";
                PreparedStatement pstmt1 = con.prepareStatement(selectDepartmentIdSql);
                pstmt1.setString(1, departmentName);
                ResultSet rs = pstmt1.executeQuery();

                long departmentId = -1;
                if (rs.next()) {
                    departmentId = rs.getLong("ID");
                } else {
                    System.out.println("No department found with the given name.");
                    return;
                }

                String insertSql = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, DEPARTMENT_ID) VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertSql);

                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, email);
                pstmt.setString(4, phoneNumber);
                pstmt.setDate(5, Date.valueOf(hireDateString));
                pstmt.setLong(6, departmentId);

                pstmt.executeUpdate();
                System.out.println("Employee added successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getEmployees(Connection con) {
        try {
            String selectSql = "SELECT ID, FIRST_NAME, LAST_NAME FROM EMPLOYEE";
            PreparedStatement pstmt = con.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Employees List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("ID"));
                System.out.println("First name: " + rs.getString("FIRST_NAME"));
                System.out.println("Last name: " + rs.getString("LAST_NAME"));
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getEmployeeByName(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter employee's first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter employee's last name: ");
            String lastName = scanner.nextLine();

            String selectSql = "SELECT * FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            pstmt = con.prepareStatement(selectSql);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Employee ID: " + rs.getInt("ID"));
                System.out.println("First Name: " + rs.getString("FIRST_NAME"));
                System.out.println("Last Name: " + rs.getString("LAST_NAME"));
                System.out.println("Email: " + rs.getString("EMAIL"));
                System.out.println("Phone Number: " + rs.getString("PHONE_NUMBER"));
                System.out.println("Hire Date: " + rs.getDate("HIRE_DATE"));
                System.out.println("Department ID: " + rs.getInt("DEPARTMENT_ID"));
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
    public void updateEmployeeByName(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter current first name: ");
            String currentFirstName = scanner.nextLine();
            System.out.print("Enter current last name: ");
            String currentLastName = scanner.nextLine();
            System.out.print("Enter new first name: ");
            String newFirstName = scanner.nextLine();
            System.out.print("Enter new last name: ");
            String newLastName = scanner.nextLine();
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();
            System.out.print("Enter new hire date (YYYY-MM-DD): ");
            String newHireDateString = scanner.nextLine();
            System.out.print("Enter department name: ");
            String departmentName = scanner.nextLine();

            try {
                String selectDepartmentIdSql = "SELECT ID FROM DEPARTMENT WHERE NAME = ?";
                PreparedStatement pstmt1 = con.prepareStatement(selectDepartmentIdSql);
                pstmt1.setString(1, departmentName);
                ResultSet rs = pstmt1.executeQuery();

                long departmentId = -1;
                if (rs.next()) {
                    departmentId = rs.getLong("ID");
                } else {
                    System.out.println("No department found with the given name.");
                    return;
                }

            String updateSql = "UPDATE EMPLOYEE SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, HIRE_DATE = ?, DEPARTMENT_ID = ? WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            pstmt = con.prepareStatement(updateSql);

            pstmt.setString(1, newFirstName);
            pstmt.setString(2, newLastName);
            pstmt.setString(3, newEmail);
            pstmt.setString(4, newPhoneNumber);
                pstmt.setDate(5, Date.valueOf(newHireDateString));
            pstmt.setLong(6, departmentId);
            pstmt.setString(7, currentFirstName);
            pstmt.setString(8, currentLastName);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void deleteEmployeeByName(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter employee's first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter employee's last name: ");
            String lastName = scanner.nextLine();

            String deleteSql = "DELETE FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            pstmt = con.prepareStatement(deleteSql);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found!");
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
    public void getEmployeeByDepartment(Connection con) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();

        try {
            String selectDepartmentIdSql = "SELECT ID FROM DEPARTMENT WHERE NAME = ?";
            PreparedStatement pstmt1 = con.prepareStatement(selectDepartmentIdSql);
            pstmt1.setString(1, departmentName);
            ResultSet rs = pstmt1.executeQuery();

            long departmentId = -1;
            if (rs.next()) {
                departmentId = rs.getLong("ID");
            } else {
                System.out.println("No department found with the given name.");
                return;
            }

            String selectEmployeeSql = "SELECT ID, FIRST_NAME, LAST_NAME FROM EMPLOYEE WHERE DEPARTMENT_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(selectEmployeeSql);
            pstmt.setLong(1, departmentId);
            ResultSet rs1 = pstmt.executeQuery();

            System.out.println("Employees for department " + departmentName + ":");
            while (rs1.next()) {
                System.out.println("ID: " + rs1.getLong("ID"));
                System.out.println("First name: " + rs1.getString("FIRST_NAME"));
                System.out.println("Last name: " + rs1.getString("LAST_NAME"));
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
