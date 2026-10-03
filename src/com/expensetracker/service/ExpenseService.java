package com.expensetracker.service;

import java.util.ArrayList;
import java.util.Comparator;

import com.expensetracker.exception.ExpenseNotFoundException;
import com.expensetracker.exception.InvalidExpenseException;
import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.util.InputValidator;

public class ExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository) {

        this.expenseRepository =
                expenseRepository;
    }

    // =========================
    // Add Expense
    // =========================
    public void addExpense(
            Expense expense) {

        if (expense == null) {

            throw new InvalidExpenseException(
                    "Expense cannot be null"
            );
        }

        if (!InputValidator.isValidName(
                expense.getName())) {

            throw new InvalidExpenseException(
                    "Expense name cannot be empty"
            );
        }

        if (!InputValidator.isValidAmount(
                expense.getAmount())) {

            throw new InvalidExpenseException(
                    "Expense amount must be greater than zero"
            );
        }

        if (!InputValidator.isValidCategory(
                expense.getCategory())) {

            throw new InvalidExpenseException(
                    "Expense category cannot be empty"
            );
        }

        if (!InputValidator.isValidDate(
                expense.getDate())) {

            throw new InvalidExpenseException(
                    "Expense date is invalid"
            );
        }

        expenseRepository.addExpense(
                expense
        );
    }

    // =========================
    // Get All Expenses
    // =========================
    public ArrayList<Expense> getAllExpenses() {

        return expenseRepository
                .findAllExpenses();
    }

    // =========================
    // Get Expense By ID
    // =========================
    public Expense getExpenseById(
            int id) {

        Expense expense =
                expenseRepository
                        .findExpenseById(id);

        if (expense == null) {

            throw new ExpenseNotFoundException(
                    "Expense with ID "
                    + id
                    + " not found"
            );
        }

        return expense;
    }

    // =========================
    // Update Expense
    // =========================
    public boolean updateExpense(
            Expense expense) {

        if (expense == null) {

            throw new InvalidExpenseException(
                    "Expense cannot be null"
            );
        }

        if (!InputValidator.isValidName(
                expense.getName())) {

            throw new InvalidExpenseException(
                    "Expense name cannot be empty"
            );
        }

        if (!InputValidator.isValidAmount(
                expense.getAmount())) {

            throw new InvalidExpenseException(
                    "Expense amount must be greater than zero"
            );
        }

        if (!InputValidator.isValidCategory(
                expense.getCategory())) {

            throw new InvalidExpenseException(
                    "Expense category cannot be empty"
            );
        }

        if (!InputValidator.isValidDate(
                expense.getDate())) {

            throw new InvalidExpenseException(
                    "Expense date is invalid"
            );
        }

        return expenseRepository
                .updateExpense(expense);
    }

    // =========================
    // Delete Expense
    // =========================
    public boolean deleteExpense(
            int id) {

        Expense expense =
                expenseRepository
                        .findExpenseById(id);

        if (expense == null) {

            throw new ExpenseNotFoundException(
                    "Expense with ID "
                    + id
                    + " not found"
            );
        }

        return expenseRepository
                .deleteExpense(id);
    }

    // =========================
    // Delete All Expenses
    // Of User
    // =========================
    public boolean deleteExpensesByUserId(
            int userId) {

        return expenseRepository
                .deleteExpensesByUserId(
                        userId
                );
    }

    // =========================
    // Next Expense ID
    // =========================
    public int getNextExpenseId() {

        int maxId = 0;

        for (Expense expense :
                expenseRepository
                        .findAllExpenses()) {

            if (expense.getId()
                    > maxId) {

                maxId =
                        expense.getId();
            }
        }

        return maxId + 1;
    }

    // =========================
    // Get User Expenses
    // =========================
    public ArrayList<Expense>
    getExpensesByUserId(
            int userId) {

        ArrayList<Expense>
                userExpenses =
                new ArrayList<>();

        for (Expense expense :
                expenseRepository
                        .findAllExpenses()) {

            if (expense.getUserId()
                    == userId) {

                userExpenses.add(
                        expense
                );
            }
        }

        return userExpenses;
    }

    // =========================
    // Search By Category
    // =========================
    public ArrayList<Expense>
    getExpensesByUserAndCategory(
            int userId,
            String category) {

        ArrayList<Expense> results =
                new ArrayList<>();

        for (Expense expense :
                expenseRepository
                        .findAllExpenses()) {

            if (expense.getUserId()
                    == userId
                    && expense.getCategory()
                            .equalsIgnoreCase(
                                    category)) {

                results.add(expense);
            }
        }

        return results;
    }

    // =========================
    // Search By Date
    // =========================
    public ArrayList<Expense>
    getExpensesByUserAndDate(
            int userId,
            String date) {

        ArrayList<Expense> results =
                new ArrayList<>();

        for (Expense expense :
                expenseRepository
                        .findAllExpenses()) {

            if (expense.getUserId()
                    == userId
                    && expense.getDate()
                            .equals(date)) {

                results.add(expense);
            }
        }

        return results;
    }

    // =========================
    // Sort Amount
    // Low To High
    // =========================
    public ArrayList<Expense>
    sortByAmountLowToHigh(
            int userId) {

        ArrayList<Expense> expenses =
                getExpensesByUserId(userId);

        expenses.sort(
                Comparator.comparingDouble(
                        Expense::getAmount
                )
        );

        return expenses;
    }

    // =========================
    // Sort Amount
    // High To Low
    // =========================
    public ArrayList<Expense>
    sortByAmountHighToLow(
            int userId) {

        ArrayList<Expense> expenses =
                getExpensesByUserId(userId);

        expenses.sort(
                Comparator.comparingDouble(
                        Expense::getAmount
                ).reversed()
        );

        return expenses;
    }

    // =========================
    // Sort Date
    // Oldest To Newest
    // =========================
    public ArrayList<Expense>
    sortByDateOldestToNewest(
            int userId) {

        ArrayList<Expense> expenses =
                getExpensesByUserId(userId);

        expenses.sort(
                Comparator.comparing(
                        Expense::getDate
                )
        );

        return expenses;
    }

    // =========================
    // Sort Date
    // Newest To Oldest
    // =========================
    public ArrayList<Expense>
    sortByDateNewestToOldest(
            int userId) {

        ArrayList<Expense> expenses =
                getExpensesByUserId(userId);

        expenses.sort(
                Comparator.comparing(
                        Expense::getDate
                ).reversed()
        );

        return expenses;
    }

    // =========================
    // Sort Category A-Z
    // =========================
    public ArrayList<Expense>
    sortByCategory(
            int userId) {

        ArrayList<Expense> expenses =
                getExpensesByUserId(userId);

        expenses.sort(
                Comparator.comparing(
                        Expense::getCategory,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return expenses;
    }
}