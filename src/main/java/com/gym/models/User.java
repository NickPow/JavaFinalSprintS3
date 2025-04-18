package com.gym.models;

/**
 * Abstract class representing a general user of the gym.
 * Subclasses: Admin, Trainer, Member
 */
public abstract class User implements MenuDisplayable {
    protected int userId;
    protected String username;
    protected String passwordHash;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected String role;

    public User(int userId, String username, String passwordHash, String email, String phoneNumber, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }
}
