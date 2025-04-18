package com.gym.models;

/**
 * Admin class with access to all system-wide administrative features.
 */
public class Admin extends User {

    public Admin(int userId, String username, String passwordHash, String email, String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "Admin");
    }

    @Override
    public void displayMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. View all users");
        System.out.println("2. Delete a user");
        System.out.println("3. View all memberships");
        System.out.println("4. View total revenue");
        System.out.println("0. Logout");
    }
}
