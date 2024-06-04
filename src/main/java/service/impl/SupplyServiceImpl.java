package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import service.SupplyService;

public class SupplyServiceImpl implements SupplyService {

    @Override
    public void addSupply(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter supply description: ");
            String description = scanner.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Enter price per unit: ");
            double pricePerUnit = scanner.nextDouble();
            scanner.nextLine();
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

                String insertSql = "INSERT INTO SUPPLY (DESCRIPTION, QUANTITY, PRICE_PER_UNIT, PROJECT_ID) VALUES (?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertSql);

                pstmt.setString(1, description);
                pstmt.setInt(2, quantity);
                pstmt.setDouble(3, pricePerUnit);
                pstmt.setLong(4, projectId);

                pstmt.executeUpdate();
                System.out.println("Supply added successfully!");
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
    public void getSupplyByDescription(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter supply description: ");
            String description = scanner.nextLine();

            String selectSql = "SELECT * FROM SUPPLY WHERE DESCRIPTION = ?";
            pstmt = con.prepareStatement(selectSql);

            pstmt.setString(1, description);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Description: " + rs.getString("DESCRIPTION"));
                System.out.println("Quantity: " + rs.getInt("QUANTITY"));
                System.out.println("Price per unit: " + rs.getDouble("PRICE_PER_UNIT"));
                System.out.println("Project ID: " + rs.getLong("PROJECT_ID"));
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
    public void updateSupplyByDescription(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter current supply description: ");
            String currentDescription = scanner.nextLine();
            System.out.print("Enter new supply description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter new quantity: ");
            int newQuantity = scanner.nextInt();
            System.out.print("Enter new price per unit: ");
            double newPricePerUnit = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter new project title: ");
            String newProjectTitle = scanner.nextLine();

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

                String updateSql = "UPDATE SUPPLY SET DESCRIPTION = ?, QUANTITY = ?, PRICE_PER_UNIT = ?, PROJECT_ID = ? WHERE DESCRIPTION = ?";
                pstmt = con.prepareStatement(updateSql);

                pstmt.setString(1, newDescription);
                pstmt.setInt(2, newQuantity);
                pstmt.setDouble(3, newPricePerUnit);
                pstmt.setLong(4, projectId);
                pstmt.setString(5, currentDescription);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Supply updated successfully!");
                } else {
                    System.out.println("Supply not found!");
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
    public void deleteSupplyByDescription(Connection con) {
        PreparedStatement pstmt = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter supply description to delete: ");
            String description = scanner.nextLine();

            String deleteSql = "DELETE FROM SUPPLY WHERE DESCRIPTION = ?";
            pstmt = con.prepareStatement(deleteSql);

            pstmt.setString(1, description);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Supply deleted successfully!");
            } else {
                System.out.println("Supply not found!");
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

    public void getSuppliesByProject(Connection con){
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

            String selectSupplySql = "SELECT ID, DESCRIPTION FROM SUPPLY WHERE PROJECT_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSupplySql);
            pstmt.setLong(1, projectId);
            ResultSet rs1 = pstmt.executeQuery();

            System.out.println("Supplies for project " + projectTitle + ":");
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
