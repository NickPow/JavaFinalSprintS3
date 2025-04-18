package com.gym.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for handling password encryption and verification using BCrypt.
 */
public class PasswordUtils {

    /**
     * Hashes a plaintext password using BCrypt.
     *
     * @param plainPassword The plaintext password.
     * @return The hashed password.
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * Verifies a plaintext password against a hashed password.
     *
     * @param plainPassword  The plaintext password.
     * @param hashedPassword The stored hashed password.
     * @return True if the password matches, false otherwise.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
