package com.gym.dao;

import java.sql.*;

/**
 * Data Access Object (DAO) for handling gym membership-related database operations.
 */
public class MembershipDAO {
    private final String url = "jdbc:postgresql://localhost:5432/gymdb";
    private final String user = "gymuser";
    private final String password = "gympass";

    /**
     * Establishes a connection to the PostgreSQL database.
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Simulates a membership purchase for a given user ID.
     * For simplicity, it inserts a basic fixed membership type.
     */
    public void purchaseMembership(int userId) {
        String sql = "INSERT INTO memberships (membership_type, membership_description, membership_cost, member_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Standard");
            pstmt.setString(2, "Basic gym access and facilities");
            pstmt.setDouble(3, 49.99);
            pstmt.setInt(4, userId);
            pstmt.executeUpdate();
            System.out.println("Membership purchased.");
        } catch (SQLException e) {
            System.out.println("Error purchasing membership: " + e.getMessage());
        }
    }

    /**
     * Displays all memberships from the database.
     */
    public void viewAllMemberships() {
        String sql = "SELECT * FROM memberships";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nAll Gym Memberships:");
            while (rs.next()) {
                System.out.println("Membership ID: " + rs.getInt("membership_id"));
                System.out.println("Type: " + rs.getString("membership_type"));
                System.out.println("Description: " + rs.getString("membership_description"));
                System.out.println("Cost: $" + rs.getDouble("membership_cost"));
                System.out.println("Member ID: " + rs.getInt("member_id"));
                System.out.println("----");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing memberships: " + e.getMessage());
        }
    }

    /**
     * Calculates and displays total revenue generated from all memberships.
     */
    public void viewTotalRevenue() {
        String sql = "SELECT SUM(membership_cost) AS total FROM memberships";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                double total = rs.getDouble("total");
                System.out.println("Total revenue from memberships: $" + total);
            }
        } catch (SQLException e) {
            System.out.println("Error calculating revenue: " + e.getMessage());
        }
    }

    /**
     * Displays total membership cost paid by a specific user.
     *
     * @param userId The ID of the user.
     */
    public void viewUserExpenses(int userId) {
        String sql = "SELECT SUM(membership_cost) AS total FROM memberships WHERE member_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("total");
                System.out.println("Your total membership expenses: $" + total);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user expenses: " + e.getMessage());
        }
    }
}
