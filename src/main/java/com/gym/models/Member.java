package com.gym.models;

/**
 * Member class representing a gym customer with access to workouts and memberships.
 */
public class Member extends User {

    public Member(int userId, String username, String passwordHash, String email, String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "Member");
    }

    @Override
    public void displayMenu() {
        System.out.println("\nMember Menu:");
        System.out.println("1. View available workout classes");
        System.out.println("2. View total membership cost");
        System.out.println("3. Purchase membership");
        System.out.println("0. Logout");
    }
}
