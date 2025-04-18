package com.gym.dao;

import com.gym.models.*;
import com.gym.utils.PasswordUtils;

import java.sql.*;

/**
 * Data Access Object for User-related operations.
 */
public class UserDAO {
    private final String url = "jdbc:postgresql://localhost:5432/gymdb";
    private final String user = "gymuser";
    private final String password = "gympass";

    /**
     * Establishes a database connection.
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Registers a new user in the database.
     *
     * @param newUser The user object to register.
     * @return True if registration is successful, false otherwise.
     */
    public boolean registerUser(User newUser) {
        String sql = "INSERT INTO users (username, password_hash, email, phone_number, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPasswordHash());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPhoneNumber());
            pstmt.setString(5, newUser.getAddress());
            pstmt.setString(6, newUser.getRole());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Attempts to log in a user based on username and password.
     *
     * @param username The username entered.
     * @param plainPassword The password entered.
     * @return The logged-in User object, or null if invalid credentials.
     */
    public User login(String username, String plainPassword) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                if (PasswordUtils.checkPassword(plainPassword, hashedPassword)) {
                    int userId = rs.getInt("user_id");
                    String role = rs.getString("role");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone_number");
                    String address = rs.getString("address");

                    switch (role) {
                        case "Admin":
                            return new Admin(userId, username, hashedPassword, email, phone, address);
                        case "Trainer":
                            return new Trainer(userId, username, hashedPassword, email, phone, address);
                        case "Member":
                            return new Member(userId, username, hashedPassword, email, phone, address);
                        default:
                            return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a user from the database by ID.
     *
     * @param userId The ID of the user to delete.
     * @return True if deletion was successful, false otherwise.
     */
    public boolean deleteUserById(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Displays all users in the system with contact information.
     */
    public void viewAllUsers() {
        String sql = "SELECT user_id, username, email, phone_number, address, role FROM users ORDER BY user_id";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nAll Registered Users:");
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone_number"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Role: " + rs.getString("role"));
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }
}
