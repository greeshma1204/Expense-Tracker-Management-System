package com.expensetracker.service;

import java.util.ArrayList;

import com.expensetracker.exception.InvalidUserException;
import com.expensetracker.exception.UserNotFoundException;
import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.util.InputValidator;

public class UserService {

    private UserRepository userRepository;

    public UserService(
            UserRepository userRepository) {

        this.userRepository =
                userRepository;
    }

    // =========================
    // Add User
    // =========================
    public void addUser(User user) {

        if (user == null) {

            throw new InvalidUserException(
                    "User cannot be null"
            );
        }

        if (!InputValidator.isValidName(
                user.getName())) {

            throw new InvalidUserException(
                    "Name cannot be empty"
            );
        }

        if (!InputValidator.isValidEmail(
                user.getEmail())) {

            throw new InvalidUserException(
                    "Invalid email"
            );
        }

        if (!InputValidator.isValidPassword(
                user.getPassword())) {

            throw new InvalidUserException(
                    "Password must contain at least 6 characters"
            );
        }

        userRepository.addUser(user);
    }

    // =========================
    // Get User By ID
    // =========================
    public User getUserById(int userId) {

        User user =
                userRepository.findUserById(
                        userId
                );

        if (user == null) {

            throw new UserNotFoundException(
                    "User with ID "
                    + userId
                    + " not found"
            );
        }

        return user;
    }

    // =========================
    // Get User By Email
    // =========================
    public User getUserByEmail(
            String email) {

        return userRepository
                .findUserByEmail(email);
    }

    // =========================
    // Get All Users
    // =========================
    public ArrayList<User> getAllUsers() {

        return userRepository
                .findAllUsers();
    }

    // =========================
    // Delete User
    // =========================
    public boolean deleteUser(
            int userId) {

        getUserById(userId);

        return userRepository
                .deleteUser(userId);
    }

    // =========================
    // Generate Next User ID
    // =========================
    public int getNextUserId() {

        int maxId = 0;

        for (User user :
                userRepository.findAllUsers()) {

            if (user.getUserId()
                    > maxId) {

                maxId =
                        user.getUserId();
            }
        }

        return maxId + 1;
    }

    // =========================
    // Update Monthly Budget
    // =========================
    public boolean setMonthlyBudget(
            int userId,
            double monthlyBudget) {

        if (!InputValidator.isValidBudget(
                monthlyBudget)) {

            throw new InvalidUserException(
                    "Budget cannot be negative"
            );
        }

        User user =
                getUserById(userId);

        user.setMonthlyBudget(
                monthlyBudget
        );

        return userRepository
                .updateUser(user);
    }
}