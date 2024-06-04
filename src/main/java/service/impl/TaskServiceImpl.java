package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Date;
import java.util.Scanner;
import service.TaskService;

public class TaskServiceImpl implements TaskService {

    @Override
    public void addTask(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter task description: ");
            String description = scanner.nextLine();
            System.out.print("Enter deadline (YYYY-MM-DD): ");
            String deadlineString = scanner.nextLine();

            System.out.print("Enter status: ");
            String status = scanner.nextLine();
            System.out.print("Enter project title: ");
            String projectTitle = scanner.nextLine();
            System.out.print("Enter employee's first name: ");
            String employeeFirstName = scanner.nextLine();
            System.out.print("Enter employee's last name: ");
            String employeeLastName = scanner.nextLine();

            try {
                String selectProjectIdSql = "SELECT ID FROM PROJECT WHERE TITLE = ?";
                PreparedStatement pstmt1 = con.prepareStatement(selectProjectIdSql);
                pstmt1.setString(1, projectTitle);
                ResultSet rs = pstmt1.executeQuery();

                long projectId = -1;
                if (rs.next()) {
                    projectId = rs.getLong("ID");
                } else {
                    System.out.println("No project found with the given title.");
                    return;
                }

                String selectEmployeeIdSql = "SELECT ID FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
                PreparedStatement pstmt2 = con.prepareStatement(selectEmployeeIdSql);
                pstmt2.setString(1, employeeFirstName);
                pstmt2.setString(2, employeeLastName);
                ResultSet rs1 = pstmt1.executeQuery();

                long employeeId = -1;
                if (rs1.next()) {
                    employeeId = rs1.getLong("ID");
                } else {
                    System.out.println("No employee found with the given name.");
                    return;
                }

                String insertSql = "INSERT INTO TASK (DESCRIPTION, DEADLINE, STATUS, PROJECT_ID, EMPLOYEE_ID) VALUES (?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertSql);

                pstmt.setString(1, description);
                pstmt.setDate(2, Date.valueOf(deadlineString));
                pstmt.setString(3, status);
                pstmt.setLong(4, projectId);
                pstmt.setLong(5, employeeId);

                pstmt.executeUpdate();
                System.out.println("Task added successfully!");
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
    public void getTaskByDescription(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter task description: ");
            String description = scanner.nextLine();

            String selectSql = "SELECT * FROM TASK WHERE DESCRIPTION = ?";
            pstmt = con.prepareStatement(selectSql);

            pstmt.setString(1, description);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Description: " + rs.getString("DESCRIPTION"));
                System.out.println("Deadline: " + rs.getDate("DEADLINE"));
                System.out.println("Status: " + rs.getString("STATUS"));
                System.out.println("Project ID: " + rs.getLong("PROJECT_ID"));
                System.out.println("Employee ID: " + rs.getLong("EMPLOYEE_ID"));
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
    public void updateTaskByDescription(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter current task description: ");
            String currentDescription = scanner.nextLine();
            System.out.print("Enter new task description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter new deadline (YYYY-MM-DD): ");
            String newDeadlineString = scanner.nextLine();
            Date newDeadline = java.sql.Date.valueOf(newDeadlineString);
            System.out.print("Enter new status: ");
            String newStatus = scanner.nextLine();
            System.out.print("Enter new project title: ");
            String newProjectTitle = scanner.nextLine();
            System.out.print("Enter new employee's first name: ");
            String newEmployeeFirstName = scanner.nextLine();
            System.out.print("Enter new employee's last name: ");
            String newEmployeeLastName = scanner.nextLine();

            try {
                String selectProjectIdSql = "SELECT ID FROM PROJECT WHERE TITLE = ?";
                PreparedStatement pstmt1 = con.prepareStatement(selectProjectIdSql);
                pstmt1.setString(1, newProjectTitle);
                ResultSet rs = pstmt1.executeQuery();

                long projectId = -1;
                if (rs.next()) {
                    projectId = rs.getLong("ID");
                } else {
                    System.out.println("No project found with the given title.");
                    return;
                }

                String selectEmployeeIdSql = "SELECT ID FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
                PreparedStatement pstmt2 = con.prepareStatement(selectEmployeeIdSql);
                pstmt2.setString(1, newEmployeeFirstName);
                pstmt2.setString(2, newEmployeeLastName);
                ResultSet rs1 = pstmt1.executeQuery();

                long employeeId = -1;
                if (rs1.next()) {
                    employeeId = rs1.getLong("ID");
                } else {
                    System.out.println("No employee found with the given name.");
                    return;
                }

                String updateSql = "UPDATE TASK SET DESCRIPTION = ?, DEADLINE = ?, STATUS = ?, PROJECT_ID = ?, EMPLOYEE_ID = ? WHERE DESCRIPTION = ?";
                pstmt = con.prepareStatement(updateSql);

                pstmt.setString(1, newDescription);
                pstmt.setDate(2, new java.sql.Date(newDeadline.getTime()));
                pstmt.setString(3, newStatus);
                pstmt.setLong(4, projectId);
                pstmt.setLong(5, employeeId);
                pstmt.setString(6, currentDescription);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Task updated successfully!");
                } else {
                    System.out.println("Task not found!");
                }
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
    public void deleteTaskByDescription(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter task description to delete: ");
            String description = scanner.nextLine();

            String deleteSql = "DELETE FROM TASK WHERE DESCRIPTION = ?";
            pstmt = con.prepareStatement(deleteSql);

            pstmt.setString(1, description);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task deleted successfully!");
            } else {
                System.out.println("Task not found!");
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
    public void getTasksByEmployee(Connection con) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee first name: ");
        String employeeFirstName = scanner.nextLine();
        System.out.print("Enter employee last name: ");
        String employeeLastName = scanner.nextLine();

        try {
            String selectEmployeeIdSql = "SELECT ID FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            PreparedStatement pstmt1 = con.prepareStatement(selectEmployeeIdSql);
            pstmt1.setString(1, employeeFirstName);
            pstmt1.setString(2, employeeLastName);
            ResultSet rs = pstmt1.executeQuery();

            long employeeId = -1;
            if (rs.next()) {
                employeeId = rs.getLong("ID");
            } else {
                System.out.println("No employee found with the given name.");
                return;
            }

            String selectTaskSql = "SELECT ID, DESCRIPTION FROM TASK WHERE EMPLOYEE_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(selectTaskSql);
            pstmt.setLong(1, employeeId);
            ResultSet rs1 = pstmt.executeQuery();

            System.out.println("Tasks for employee " + employeeFirstName + " " + employeeLastName + ":");
            while (rs1.next()) {
                System.out.println("ID: " + rs1.getLong("ID"));
                System.out.println("Description: " + rs1.getString("DESCRIPTION"));
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTasksByProject(Connection con) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter project title: ");
        String projectTitle = scanner.nextLine();

        try {
            String selectProjectIdSql = "SELECT ID FROM PROJECT WHERE TITLE = ?";
            PreparedStatement pstmt1 = con.prepareStatement(selectProjectIdSql);
            pstmt1.setString(1, projectTitle);
            ResultSet rs = pstmt1.executeQuery();

            long projectId = -1;
            if (rs.next()) {
                projectId = rs.getLong("ID");
            } else {
                System.out.println("No project found with the given title.");
                return;
            }

            String selectTaskSql = "SELECT ID, DESCRIPTION FROM TASK WHERE PROJECT_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(selectTaskSql);
            pstmt.setLong(1, projectId);
            ResultSet rs1 = pstmt.executeQuery();

            System.out.println("Tasks for project " + projectTitle + ":");
            while (rs1.next()) {
                System.out.println("ID: " + rs1.getLong("ID"));
                System.out.println("Description: " + rs1.getString("DESCRIPTION"));
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}