package com.gym;

import com.gym.dao.UserDAO;
import com.gym.dao.WorkoutClassDAO;
import com.gym.models.*;
import com.gym.services.MembershipService;
import com.gym.utils.PasswordUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the Gym Management System application.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        MembershipService membershipService = new MembershipService();
        WorkoutClassDAO workoutClassDAO = new WorkoutClassDAO();

        boolean running = true;

        while (running) {
            System.out.println("\n===== Gym Management System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choiceInput = scanner.nextLine();

            if (!choiceInput.matches("[012]")) {
                System.out.println("Invalid input. Please enter 0, 1, or 2.");
                continue;
            }

            int choice = Integer.parseInt(choiceInput);

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    String hashedPassword = PasswordUtils.hashPassword(password);

                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                        System.out.println("Invalid email format.");
                        break;
                    }

                    System.out.print("Phone (10 digits): ");
                    String phone = scanner.nextLine();
                    if (!phone.matches("\\d{10}")) {
                        System.out.println("Invalid phone number.");
                        break;
                    }

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Role (a = Admin, t = Trainer, m = Member): ");
                    String roleInput = scanner.nextLine().toLowerCase();

                    String role;
                    switch (roleInput) {
                        case "a":
                            role = "Admin";
                            break;
                        case "t":
                            role = "Trainer";
                            break;
                        case "m":
                            role = "Member";
                            break;
                        default:
                            role = "";
                    }

                    if (role.isEmpty()) {
                        System.out.println("Invalid role selection.");
                        break;
                    }

                    User newUser;
                    switch (role) {
                        case "Admin":
                            newUser = new Admin(0, username, hashedPassword, email, phone, address);
                            break;
                        case "Trainer":
                            newUser = new Trainer(0, username, hashedPassword, email, phone, address);
                            break;
                        case "Member":
                            newUser = new Member(0, username, hashedPassword, email, phone, address);
                            break;
                        default:
                            newUser = null;
                    }

                    if (newUser != null && userDAO.registerUser(newUser)) {
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;

                case 2:
                    System.out.print("Username: ");
                    String loginUser = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPass = scanner.nextLine();

                    User user = userDAO.login(loginUser, loginPass);
                    if (user == null) {
                        System.out.println("Invalid credentials.");
                        break;
                    }

                    boolean stayLoggedIn = true;
                    while (stayLoggedIn) {
                        user.displayMenu();
                        System.out.print("Choose: ");
                        String optInput = scanner.nextLine();
                        int opt = optInput.matches("\\d+") ? Integer.parseInt(optInput) : -1;

                        if (user instanceof Admin) {
                            switch (opt) {
                                case 1:
                                    userDAO.viewAllUsers();
                                    break;
                                case 2:
                                    System.out.print("Enter User ID to delete: ");
                                    int delId = Integer.parseInt(scanner.nextLine());
                                    if (userDAO.deleteUserById(delId)) {
                                        System.out.println("User deleted.");
                                    } else {
                                        System.out.println("Deletion failed.");
                                    }
                                    break;
                                case 3:
                                    membershipService.viewAllMemberships();
                                    break;
                                case 4:
                                    membershipService.viewTotalRevenue();
                                    break;
                                case 0:
                                    stayLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }

                        } else if (user instanceof Trainer) {
                            switch (opt) {
                                case 1:
                                System.out.println("\n Add a New Workout Class");
                                System.out.print("Enter the name of the class (e.g., Yoga, HIIT, Zumba): ");
                                String t = scanner.nextLine();
                                
                                System.out.print("Enter a short description (e.g., Full-body workout, Core-focused session): ");
                                String d = scanner.nextLine();
                                
                                workoutClassDAO.addWorkoutClass(new WorkoutClass(0, t, d, user.getUserId()));
                                System.out.println("Class added: " + t);
                                
                                    break;
                                case 2: // 
                                System.out.println("\n Update Workout Class");
                                System.out.print("Enter the ID of the class you want to update: ");
                                int classId = Integer.parseInt(scanner.nextLine());
                                
                                System.out.print("New name for the class (e.g., Pilates, Cardio Burn): ");
                                String newType = scanner.nextLine();
                                
                                System.out.print("New description: ");
                                String newDesc = scanner.nextLine();
                                
                                workoutClassDAO.updateWorkoutClass(classId, newType, newDesc, user.getUserId());
                                
                                    break;
                                case 3:
                                    System.out.print("Class ID to delete: ");
                                    int id = Integer.parseInt(scanner.nextLine());
                                    workoutClassDAO.deleteWorkoutClass(id, user.getUserId());
                                    break;
                                case 4:
                                    List<WorkoutClass> trainerClasses = workoutClassDAO.getTrainerClasses(user.getUserId());
                                    trainerClasses.forEach(c -> System.out.println(c.getWorkoutClassId() + ": " + c.getWorkoutClassType()));
                                    break;
                                case 5:
                                    membershipService.purchaseMembership(user);
                                    break;
                                case 0:
                                    stayLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }

                        } else if (user instanceof Member) {
                            switch (opt) {
                                case 1:
                                    List<WorkoutClass> classes = workoutClassDAO.getAllWorkoutClasses();
                                    classes.forEach(c -> System.out.println(c.getWorkoutClassId() + ": " + c.getWorkoutClassType()));
                                    break;
                                case 2:
                                    membershipService.viewUserExpenses(user);
                                    break;
                                case 3:
                                    membershipService.purchaseMembership(user);
                                    break;
                                case 0:
                                    stayLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        }
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }

        scanner.close();
    }
}
