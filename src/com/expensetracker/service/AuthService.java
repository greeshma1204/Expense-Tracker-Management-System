package com.expensetracker.service;

import com.expensetracker.model.Role;
import com.expensetracker.model.User;
import com.expensetracker.util.InputValidator;

public class AuthService {

    private UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // Register User Object
    // =========================
    public boolean register(User user) {

        User existingUser =
                userService.getUserByEmail(
                        user.getEmail()
                );

        if (existingUser != null) {
            return false;
        }

        userService.addUser(user);

        return true;
    }

    // =========================
    // Register User
    // =========================
    public boolean register(
            String name,
            String email,
            String password) {

        int userId =
                userService.getNextUserId();

        User user =
                new User(
                        userId,
                        name,
                        email,
                        password,
                        Role.USER,
                        0.0
                );

        return register(user);
    }

    // =========================
    // Login
    // =========================
    public User login(
            String email,
            String password) {

        if (!InputValidator.isValidEmail(email)) {
            return null;
        }

        if (!InputValidator.isValidPassword(password)) {
            return null;
        }

        User user =
                userService.getUserByEmail(email);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    // =========================
    // Create Default Admin
    // =========================
    public void createDefaultAdmin() {

        String adminEmail =
                "admin@gmail.com";

        User existingAdmin =
                userService.getUserByEmail(
                        adminEmail
                );

        if (existingAdmin == null) {

            int adminId =
                    userService.getNextUserId();

            User admin =
                    new User(
                            adminId,
                            "Administrator",
                            adminEmail,
                            "admin123",
                            Role.ADMIN,
                            0.0
                    );

            userService.addUser(admin);

            System.out.println(
                    "Default admin account created."
            );
        }
    }
}