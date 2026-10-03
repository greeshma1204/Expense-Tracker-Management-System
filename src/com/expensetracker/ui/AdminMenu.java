package com.expensetracker.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.expensetracker.admin.AdminService;
import com.expensetracker.exception.ExpenseNotFoundException;
import com.expensetracker.exception.UserNotFoundException;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;
import com.expensetracker.service.ReportService;
import com.expensetracker.util.InputValidator;

public class AdminMenu {

    private AdminService adminService;
    private ReportService reportService;
    private Scanner scanner;

    public AdminMenu(
            AdminService adminService,
            ReportService reportService,
            Scanner scanner) {

        this.adminService =
                adminService;

        this.reportService =
                reportService;

        this.scanner =
                scanner;
    }

    // =========================
    // Show Menu
    // =========================
    public void showMenu() {

        System.out.println();
        System.out.println(
                "===== ADMIN MENU ====="
        );

        System.out.println(
                "1. View All Users"
        );

        System.out.println(
                "2. View All Expenses"
        );

        System.out.println(
                "3. Delete User"
        );

        System.out.println(
                "4. Delete Expense"
        );

        System.out.println(
                "5. View Overall Report"
        );

        System.out.println(
                "6. View Monthly Report"
        );

        System.out.println(
                "7. Logout"
        );

        System.out.print(
                "Enter your choice: "
        );
    }

    // =========================
    // Read Choice
    // =========================
    public int readChoice() {

        while (true) {

            try {

                int choice =
                        scanner.nextInt();

                scanner.nextLine();

                return choice;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }

    // =========================
    // View All Users
    // =========================
    public void viewAllUsers() {

        System.out.println();
        System.out.println(
                "===== ALL USERS ====="
        );

        ArrayList<User> users =
                adminService
                        .getAllUsers();

        if (users.isEmpty()) {

            System.out.println(
                    "No users found."
            );

            return;
        }

        for (User user :
                users) {

            System.out.println(
                    "User ID : "
                    + user.getUserId()
            );

            System.out.println(
                    "Name    : "
                    + user.getName()
            );

            System.out.println(
                    "Email   : "
                    + user.getEmail()
            );

            System.out.println(
                    "Role    : "
                    + user.getRole()
            );

            System.out.println(
                    "Budget  : "
                    + user.getMonthlyBudget()
            );

            System.out.println(
                    "--------------------"
            );
        }
    }

    // =========================
    // View All Expenses
    // =========================
    public void viewAllExpenses() {

        System.out.println();
        System.out.println(
                "===== ALL EXPENSES ====="
        );

        ArrayList<Expense> expenses =
                adminService
                        .getAllExpenses();

        if (expenses.isEmpty()) {

            System.out.println(
                    "No expenses found."
            );

            return;
        }

        for (Expense expense :
                expenses) {

            System.out.println(
                    "Expense ID : "
                    + expense.getId()
            );

            System.out.println(
                    "Name       : "
                    + expense.getName()
            );

            System.out.println(
                    "Amount     : "
                    + expense.getAmount()
            );

            System.out.println(
                    "Category   : "
                    + expense.getCategory()
            );

            System.out.println(
                    "User ID    : "
                    + expense.getUserId()
            );

            System.out.println(
                    "Date       : "
                    + expense.getDate()
            );

            System.out.println(
                    "-------------------------"
            );
        }
    }

    // =========================
    // Delete User
    // =========================
    public void deleteUser() {

        System.out.println();
        System.out.println(
                "===== DELETE USER ====="
        );

        int userId;

        while (true) {

            try {

                System.out.print(
                        "Enter user ID to delete: "
                );

                userId =
                        scanner.nextInt();

                scanner.nextLine();

                break;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid ID. Please enter a number."
                );

                scanner.nextLine();
            }
        }

        try {

            boolean deleted =
                    adminService
                            .deleteUser(
                                    userId
                            );

            if (deleted) {

                System.out.println(
                        "User deleted successfully!"
                );

            } else {

                System.out.println(
                        "Admin user cannot be deleted."
                );
            }

        } catch (UserNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // =========================
    // Delete Expense
    // =========================
    public void deleteExpense() {

        System.out.println();
        System.out.println(
                "===== DELETE EXPENSE ====="
        );

        int expenseId;

        while (true) {

            try {

                System.out.print(
                        "Enter expense ID to delete: "
                );

                expenseId =
                        scanner.nextInt();

                scanner.nextLine();

                break;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid ID. Please enter a number."
                );

                scanner.nextLine();
            }
        }

        try {

            boolean deleted =
                    adminService
                            .deleteExpense(
                                    expenseId
                            );

            if (deleted) {

                System.out.println(
                        "Expense deleted successfully!"
                );
            }

        } catch (ExpenseNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // =========================
    // Overall Report
    // =========================
    public void overallReport() {

        System.out.println();
        System.out.println(
                "===== OVERALL EXPENSE REPORT ====="
        );

        double total =
                reportService
                        .calculateTotalExpenses();

        System.out.println(
                "Total Expenses : "
                + total
        );

        System.out.println();

        Map<String, Double>
                categoryTotals =
                reportService
                        .getCategoryTotals();

        System.out.println(
                "Category-wise Expenses:"
        );

        if (categoryTotals.isEmpty()) {

            System.out.println(
                    "No expenses found."
            );

            return;
        }

        for (Map.Entry<String, Double>
                entry :
                categoryTotals.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " : "
                    + entry.getValue()
            );
        }

        Expense highest =
                reportService
                        .getHighestExpense();

        if (highest != null) {

            System.out.println();
            System.out.println(
                    "Highest Expense:"
            );

            System.out.println(
                    "Name   : "
                    + highest.getName()
            );

            System.out.println(
                    "Amount : "
                    + highest.getAmount()
            );

            System.out.println(
                    "Date   : "
                    + highest.getDate()
            );
        }

        Expense lowest =
                reportService
                        .getLowestExpense();

        if (lowest != null) {

            System.out.println();
            System.out.println(
                    "Lowest Expense:"
            );

            System.out.println(
                    "Name   : "
                    + lowest.getName()
            );

            System.out.println(
                    "Amount : "
                    + lowest.getAmount()
            );

            System.out.println(
                    "Date   : "
                    + lowest.getDate()
            );
        }
    }

    // =========================
    // Monthly Report
    // =========================
    public void monthlyReport() {

        System.out.println();
        System.out.println(
                "===== ADMIN MONTHLY REPORT ====="
        );

        String month;

        while (true) {

            System.out.print(
                    "Enter month (YYYY-MM): "
            );

            month =
                    scanner.nextLine();

            if (InputValidator.isValidMonth(
                    month)) {

                break;
            }

            System.out.println(
                    "Invalid month. Use YYYY-MM."
            );
        }

        double total =
                reportService
                        .calculateMonthlyTotal(
                                month
                        );

        System.out.println();
        System.out.println(
                "Month : "
                + month
        );

        System.out.println(
                "Total Expenses : "
                + total
        );

        System.out.println();
        System.out.println(
                "Category-wise Expenses:"
        );

        Map<String, Double>
                categoryTotals =
                reportService
                        .getMonthlyCategoryTotals(
                                month
                        );

        if (categoryTotals.isEmpty()) {

            System.out.println(
                    "No expenses found for this month."
            );

            return;
        }

        for (Map.Entry<String, Double>
                entry :
                categoryTotals.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " : "
                    + entry.getValue()
            );
        }

        Expense highest =
                reportService
                        .getMonthlyHighestExpense(
                                month
                        );

        Expense lowest =
                reportService
                        .getMonthlyLowestExpense(
                                month
                        );

        if (highest != null) {

            System.out.println();
            System.out.println(
                    "Highest Expense : "
                    + highest.getAmount()
            );
        }

        if (lowest != null) {

            System.out.println(
                    "Lowest Expense  : "
                    + lowest.getAmount()
            );
        }
    }

    // =========================
    // Run
    // =========================
    public void run() {

        boolean running = true;

        while (running) {

            showMenu();

            int choice =
                    readChoice();

            switch (choice) {

                case 1:
                    viewAllUsers();
                    break;

                case 2:
                    viewAllExpenses();
                    break;

                case 3:
                    deleteUser();
                    break;

                case 4:
                    deleteExpense();
                    break;

                case 5:
                    overallReport();
                    break;

                case 6:
                    monthlyReport();
                    break;

                case 7:

                    System.out.println(
                            "Logging out..."
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }
}