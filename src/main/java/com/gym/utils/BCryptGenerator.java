package com.gym.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptGenerator {
    public static void main(String[] args) {
        String admin = "adminpass";
        String trainer = "trainerpass";
        String member = "memberpass";

        System.out.println("Admin Hash: " + BCrypt.hashpw(admin, BCrypt.gensalt()));
        System.out.println("Trainer Hash: " + BCrypt.hashpw(trainer, BCrypt.gensalt()));
        System.out.println("Member Hash: " + BCrypt.hashpw(member, BCrypt.gensalt()));
    }
}
