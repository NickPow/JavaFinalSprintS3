package com.gym.models;

/**
 * Trainer class representing a gym trainer with ability to manage workout classes.
 */
public class Trainer extends User {

    public Trainer(int userId, String username, String passwordHash, String email, String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "Trainer");
    }

    @Override
    public void displayMenu() {
        System.out.println("\nTrainer Menu:");
        System.out.println("1. Add workout class");
        System.out.println("2. Update workout class");
        System.out.println("3. Delete workout class");
        System.out.println("4. View assigned classes");
        System.out.println("5. Purchase membership");
        System.out.println("0. Logout");
    }
}
