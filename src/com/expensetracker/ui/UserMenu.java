package com.expensetracker.ui;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.expensetracker.exception.ExpenseNotFoundException;
import com.expensetracker.exception.InvalidExpenseException;
import com.expensetracker.exception.InvalidUserException;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.service.ReportService;
import com.expensetracker.service.UserService;
import com.expensetracker.util.InputValidator;

public class UserMenu {

    private UserService userService;
    private ExpenseService expenseService;
    private ReportService reportService;
    private Scanner scanner;
    private User loggedInUser;

    public UserMenu(
            UserService userService,
            ExpenseService expenseService,
            ReportService reportService,
            Scanner scanner,
            User loggedInUser) {

        this.userService =
                userService;

        this.expenseService =
                expenseService;

        this.reportService =
                reportService;

        this.scanner =
                scanner;

        this.loggedInUser =
                loggedInUser;
    }

    // =========================
    // Show Menu
    // =========================
    public void showMenu() {

        System.out.println();
        System.out.println(
                "===== USER MENU ====="
        );

        System.out.println(
                "1. Add Expense"
        );

        System.out.println(
                "2. View My Expenses"
        );

        System.out.println(
                "3. Update Expense"
        );

        System.out.println(
                "4. Delete Expense"
        );

        System.out.println(
                "5. Search Expense by Category"
        );

        System.out.println(
                "6. Search Expense by Date"
        );

        System.out.println(
                "7. Sort Expenses"
        );

        System.out.println(
                "8. View Report"
        );

        System.out.println(
                "9. View Monthly Report"
        );

        System.out.println(
                "10. Set Monthly Budget"
        );

        System.out.println(
                "11. View Budget Status"
        );

        System.out.println(
                "12. Logout"
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
    // Add Expense
    // =========================
    public void addExpense() {

        System.out.println();
        System.out.println(
                "===== ADD EXPENSE ====="
        );

        System.out.print(
                "Enter expense name: "
        );

        String name =
                scanner.nextLine();

        double amount;

        while (true) {

            try {

                System.out.print(
                        "Enter amount: "
                );

                amount =
                        scanner.nextDouble();

                scanner.nextLine();

                break;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Please enter a valid amount."
                );

                scanner.nextLine();
            }
        }

        System.out.print(
                "Enter category: "
        );

        String category =
                scanner.nextLine();

        String date;

        while (true) {

            System.out.print(
                    "Enter date (YYYY-MM-DD): "
            );

            date =
                    scanner.nextLine();

            if (InputValidator.isValidDate(
                    date)) {

                break;
            }

            System.out.println(
                    "Invalid date. Please use YYYY-MM-DD."
            );
        }

        int expenseId =
                expenseService
                        .getNextExpenseId();

        int userId =
                loggedInUser.getUserId();

        Expense expense =
                new Expense(
                        expenseId,
                        name,
                        amount,
                        category,
                        userId,
                        date
                );

        try {

            expenseService.addExpense(
                    expense
            );

            System.out.println(
                    "Expense added successfully!"
            );

        } catch (InvalidExpenseException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // =========================
    // View My Expenses
    // =========================
    public void viewMyExpenses() {

        System.out.println();
        System.out.println(
                "===== MY EXPENSES ====="
        );

        ArrayList<Expense> expenses =
                expenseService
                        .getExpensesByUserId(
                                loggedInUser
                                        .getUserId()
                        );

        if (expenses.isEmpty()) {

            System.out.println(
                    "No expenses found."
            );

            return;
        }

        for (Expense expense :
                expenses) {

            printExpense(expense);
        }
    }

    // =========================
    // Print Expense
    // =========================
    private void printExpense(
            Expense expense) {

        System.out.println(
                "ID       : "
                + expense.getId()
        );

        System.out.println(
                "Name     : "
                + expense.getName()
        );

        System.out.println(
                "Amount   : "
                + expense.getAmount()
        );

        System.out.println(
                "Category : "
                + expense.getCategory()
        );

        System.out.println(
                "Date     : "
                + expense.getDate()
        );

        System.out.println(
                "--------------------"
        );
    }

    // =========================
    // Update Expense
    // =========================
    public void updateMyExpense() {

        System.out.println();
        System.out.println(
                "===== UPDATE EXPENSE ====="
        );

        int expenseId;

        while (true) {

            try {

                System.out.print(
                        "Enter expense ID: "
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

        Expense existingExpense;

        try {

            existingExpense =
                    expenseService
                            .getExpenseById(
                                    expenseId
                            );

        } catch (ExpenseNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );

            return;
        }

        if (existingExpense
                .getUserId()
                != loggedInUser
                        .getUserId()) {

            System.out.println(
                    "You can update only your own expense."
            );

            return;
        }

        System.out.print(
                "Enter new expense name: "
        );

        String name =
                scanner.nextLine();

        double amount;

        while (true) {

            try {

                System.out.print(
                        "Enter new amount: "
                );

                amount =
                        scanner.nextDouble();

                scanner.nextLine();

                break;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid amount. Please enter a number."
                );

                scanner.nextLine();
            }
        }

        System.out.print(
                "Enter new category: "
        );

        String category =
                scanner.nextLine();

        String date;

        while (true) {

            System.out.print(
                    "Enter new date (YYYY-MM-DD): "
            );

            date =
                    scanner.nextLine();

            if (InputValidator.isValidDate(
                    date)) {

                break;
            }

            System.out.println(
                    "Invalid date. Please use YYYY-MM-DD."
            );
        }

        Expense updatedExpense =
                new Expense(
                        expenseId,
                        name,
                        amount,
                        category,
                        loggedInUser.getUserId(),
                        date
                );

        try {

            boolean updated =
                    expenseService
                            .updateExpense(
                                    updatedExpense
                            );

            if (updated) {

                System.out.println(
                        "Expense updated successfully!"
                );

            } else {

                System.out.println(
                        "Expense could not be updated."
                );
            }

        } catch (InvalidExpenseException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // =========================
    // Delete Expense
    // =========================
    public void deleteMyExpense() {

        System.out.println();
        System.out.println(
                "===== DELETE EXPENSE ====="
        );

        int expenseId;

        while (true) {

            try {

                System.out.print(
                        "Enter expense ID: "
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

            Expense expense =
                    expenseService
                            .getExpenseById(
                                    expenseId
                            );

            if (expense.getUserId()
                    != loggedInUser
                            .getUserId()) {

                System.out.println(
                        "You can delete only your own expense."
                );

                return;
            }

            boolean deleted =
                    expenseService
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
    // Search By Category
    // =========================
    public void searchMyExpenses() {

        System.out.println();
        System.out.println(
                "===== SEARCH EXPENSE BY CATEGORY ====="
        );

        System.out.print(
                "Enter category: "
        );

        String category =
                scanner.nextLine();

        ArrayList<Expense> expenses =
                expenseService
                        .getExpensesByUserAndCategory(
                                loggedInUser
                                        .getUserId(),
                                category
                        );

        if (expenses.isEmpty()) {

            System.out.println(
                    "No expenses found in this category."
            );

            return;
        }

        for (Expense expense :
                expenses) {

            printExpense(expense);
        }
    }

    // =========================
    // Search By Date
    // =========================
    public void searchExpensesByDate() {

        System.out.println();
        System.out.println(
                "===== SEARCH EXPENSE BY DATE ====="
        );

        String date;

        while (true) {

            System.out.print(
                    "Enter date (YYYY-MM-DD): "
            );

            date =
                    scanner.nextLine();

            if (InputValidator.isValidDate(
                    date)) {

                break;
            }

            System.out.println(
                    "Invalid date. Please enter a valid date."
            );
        }

        ArrayList<Expense> expenses =
                expenseService
                        .getExpensesByUserAndDate(
                                loggedInUser
                                        .getUserId(),
                                date
                        );

        if (expenses.isEmpty()) {

            System.out.println(
                    "No expenses found on "
                    + date
            );

            return;
        }

        for (Expense expense :
                expenses) {

            printExpense(expense);
        }
    }

    // =========================
    // Sort Expenses
    // =========================
    public void sortExpenses() {

        System.out.println();
        System.out.println(
                "===== SORT EXPENSES ====="
        );

        System.out.println(
                "1. Amount - Low to High"
        );

        System.out.println(
                "2. Amount - High to Low"
        );

        System.out.println(
                "3. Date - Oldest to Newest"
        );

        System.out.println(
                "4. Date - Newest to Oldest"
        );

        System.out.println(
                "5. Category - A to Z"
        );

        System.out.print(
                "Enter your choice: "
        );

        int choice;

        while (true) {

            try {

                choice =
                        scanner.nextInt();

                scanner.nextLine();

                break;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Please enter a number."
                );

                scanner.nextLine();
            }
        }

        ArrayList<Expense> expenses;

        switch (choice) {

            case 1:

                expenses =
                        expenseService
                                .sortByAmountLowToHigh(
                                        loggedInUser
                                                .getUserId()
                                );

                break;

            case 2:

                expenses =
                        expenseService
                                .sortByAmountHighToLow(
                                        loggedInUser
                                                .getUserId()
                                );

                break;

            case 3:

                expenses =
                        expenseService
                                .sortByDateOldestToNewest(
                                        loggedInUser
                                                .getUserId()
                                );

                break;

            case 4:

                expenses =
                        expenseService
                                .sortByDateNewestToOldest(
                                        loggedInUser
                                                .getUserId()
                                );

                break;

            case 5:

                expenses =
                        expenseService
                                .sortByCategory(
                                        loggedInUser
                                                .getUserId()
                                );

                break;

            default:

                System.out.println(
                        "Invalid sorting choice."
                );

                return;
        }

        if (expenses.isEmpty()) {

            System.out.println(
                    "No expenses found."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "===== SORTED EXPENSES ====="
        );

        for (Expense expense :
                expenses) {

            printExpense(expense);
        }
    }

    // =========================
    // Overall User Report
    // =========================
    public void viewMyReport() {

        System.out.println();
        System.out.println(
                "===== MY EXPENSE REPORT ====="
        );

        int userId =
                loggedInUser.getUserId();

        double total =
                reportService
                        .calculateUserTotal(
                                userId
                        );

        System.out.println(
                "Total Expenses : "
                + total
        );

        System.out.println();

        Map<String, Double>
                categoryTotals =
                reportService
                        .getUserCategoryTotals(
                                userId
                        );

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
                        .getHighestExpenseForUser(
                                userId
                        );

        Expense lowest =
                reportService
                        .getLowestExpenseForUser(
                                userId
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
    // Monthly Report
    // =========================
    public void viewMonthlyReport() {

        System.out.println();
        System.out.println(
                "===== MONTHLY REPORT ====="
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

        int userId =
                loggedInUser.getUserId();

        double total =
                reportService
                        .calculateUserMonthlyTotal(
                                userId,
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
                        .getUserMonthlyCategoryTotals(
                                userId,
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
                        .getUserMonthlyHighestExpense(
                                userId,
                                month
                        );

        Expense lowest =
                reportService
                        .getUserMonthlyLowestExpense(
                                userId,
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
    // Set Budget
    // =========================
    public void setMonthlyBudget() {

        System.out.println();
        System.out.println(
                "===== SET MONTHLY BUDGET ====="
        );

        double budget;

        while (true) {

            try {

                System.out.print(
                        "Enter monthly budget: "
                );

                budget =
                        scanner.nextDouble();

                scanner.nextLine();

                if (InputValidator
                        .isValidBudget(
                                budget
                        )) {

                    break;
                }

                System.out.println(
                        "Budget cannot be negative."
                );

            } catch (InputMismatchException e) {

                System.out.println(
                        "Please enter a valid amount."
                );

                scanner.nextLine();
            }
        }

        try {

            boolean updated =
                    userService
                            .setMonthlyBudget(
                                    loggedInUser
                                            .getUserId(),
                                    budget
                            );

            if (updated) {

                loggedInUser.setMonthlyBudget(
                        budget
                );

                System.out.println(
                        "Monthly budget updated successfully!"
                );
            }

        } catch (InvalidUserException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // =========================
    // Budget Status
    // =========================
    public void viewBudgetStatus() {

        System.out.println();
        System.out.println(
                "===== BUDGET STATUS ====="
        );

        double budget =
                loggedInUser
                        .getMonthlyBudget();

        if (budget <= 0) {

            System.out.println(
                    "No monthly budget set."
            );

            return;
        }

        String currentMonth =
                YearMonth.now().toString();

        double spent =
                reportService
                        .calculateUserMonthlyTotal(
                                loggedInUser
                                        .getUserId(),
                                currentMonth
                        );

        double remaining =
                budget - spent;

        System.out.println(
                "Month        : "
                + currentMonth
        );

        System.out.println(
                "Budget       : "
                + budget
        );

        System.out.println(
                "Spent        : "
                + spent
        );

        if (remaining >= 0) {

            System.out.println(
                    "Remaining    : "
                    + remaining
            );

        } else {

            System.out.println(
                    "Budget Exceeded By : "
                    + Math.abs(remaining)
            );
        }
    }

    // =========================
    // Run Menu
    // =========================
    public void run() {

        boolean running = true;

        while (running) {

            showMenu();

            int choice =
                    readChoice();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    viewMyExpenses();
                    break;

                case 3:
                    updateMyExpense();
                    break;

                case 4:
                    deleteMyExpense();
                    break;

                case 5:
                    searchMyExpenses();
                    break;

                case 6:
                    searchExpensesByDate();
                    break;

                case 7:
                    sortExpenses();
                    break;

                case 8:
                    viewMyReport();
                    break;

                case 9:
                    viewMonthlyReport();
                    break;

                case 10:
                    setMonthlyBudget();
                    break;

                case 11:
                    viewBudgetStatus();
                    break;

                case 12:
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
