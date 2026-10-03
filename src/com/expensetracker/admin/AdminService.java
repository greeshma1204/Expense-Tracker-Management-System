package com.expensetracker.admin;

import java.util.ArrayList;

import com.expensetracker.exception.UserNotFoundException;
import com.expensetracker.model.Expense;
import com.expensetracker.model.Role;
import com.expensetracker.model.User;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.service.UserService;

public class AdminService {

    private UserService userService;
    private ExpenseService expenseService;

    public AdminService(
            UserService userService,
            ExpenseService expenseService) {

        this.userService =
                userService;

        this.expenseService =
                expenseService;
    }

    public ArrayList<User> getAllUsers() {

        return userService.getAllUsers();
    }

    public ArrayList<Expense>
    getAllExpenses() {

        return expenseService.getAllExpenses();
    }

    public boolean deleteUser(
            int userId) {

        User user =
                userService.getUserById(
                        userId
                );

        if (user.getRole()
                == Role.ADMIN) {

            return false;
        }

        boolean deleted =
                userService.deleteUser(
                        userId
                );

        if (deleted) {

            expenseService
                    .deleteExpensesByUserId(
                            userId
                    );
        }

        return deleted;
    }

    public boolean deleteExpense(
            int expenseId) {

        return expenseService
                .deleteExpense(
                        expenseId
                );
    }
}