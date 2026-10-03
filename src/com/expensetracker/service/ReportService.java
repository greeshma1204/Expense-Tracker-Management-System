package com.expensetracker.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.expensetracker.model.Expense;

public class ReportService {

    private ExpenseService expenseService;

    public ReportService(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    // =========================
    // Total All Expenses
    // =========================
    public double calculateTotalExpenses() {

        double total = 0;

        for (Expense expense :
                expenseService.getAllExpenses()) {

            total += expense.getAmount();
        }

        return total;
    }

    // =========================
    // Category Totals
    // =========================
    public Map<String, Double> getCategoryTotals() {

        return getCategoryTotals(
                expenseService.getAllExpenses()
        );
    }

    private Map<String, Double> getCategoryTotals(
            ArrayList<Expense> expenses) {

        Map<String, Double> categoryTotals =
                new HashMap<>();

        for (Expense expense : expenses) {

            String category =
                    expense.getCategory();

            double amount =
                    expense.getAmount();

            categoryTotals.put(
                    category,
                    categoryTotals.getOrDefault(
                            category,
                            0.0
                    ) + amount
            );
        }

        return categoryTotals;
    }

    // =========================
    // Highest Expense
    // =========================
    public Expense getHighestExpense() {

        return getHighestExpense(
                expenseService.getAllExpenses()
        );
    }

    private Expense getHighestExpense(
            ArrayList<Expense> expenses) {

        if (expenses.isEmpty()) {
            return null;
        }

        Expense highest =
                expenses.get(0);

        for (Expense expense : expenses) {

            if (expense.getAmount()
                    > highest.getAmount()) {

                highest = expense;
            }
        }

        return highest;
    }

    // =========================
    // Lowest Expense
    // =========================
    public Expense getLowestExpense() {

        return getLowestExpense(
                expenseService.getAllExpenses()
        );
    }

    private Expense getLowestExpense(
            ArrayList<Expense> expenses) {

        if (expenses.isEmpty()) {
            return null;
        }

        Expense lowest =
                expenses.get(0);

        for (Expense expense : expenses) {

            if (expense.getAmount()
                    < lowest.getAmount()) {

                lowest = expense;
            }
        }

        return lowest;
    }

    // =========================
    // User Total
    // =========================
    public double calculateUserTotal(
            int userId) {

        double total = 0;

        for (Expense expense :
                expenseService.getExpensesByUserId(
                        userId)) {

            total += expense.getAmount();
        }

        return total;
    }

    // =========================
    // User Category Totals
    // =========================
    public Map<String, Double> getUserCategoryTotals(
            int userId) {

        return getCategoryTotals(
                expenseService.getExpensesByUserId(
                        userId
                )
        );
    }

    // =========================
    // User Highest Expense
    // =========================
    public Expense getHighestExpenseForUser(
            int userId) {

        ArrayList<Expense> expenses =
                expenseService.getExpensesByUserId(
                        userId
                );

        return getHighestExpense(expenses);
    }

    // =========================
    // User Lowest Expense
    // =========================
    public Expense getLowestExpenseForUser(
            int userId) {

        ArrayList<Expense> expenses =
                expenseService.getExpensesByUserId(
                        userId
                );

        return getLowestExpense(expenses);
    }

    // =========================
    // Monthly Expenses
    // =========================
    private ArrayList<Expense> getMonthlyExpenses(
            String month) {

        YearMonth targetMonth =
                YearMonth.parse(month);

        ArrayList<Expense> results =
                new ArrayList<>();

        for (Expense expense :
                expenseService.getAllExpenses()) {

            LocalDate date =
                    LocalDate.parse(
                            expense.getDate()
                    );

            YearMonth expenseMonth =
                    YearMonth.from(date);

            if (expenseMonth.equals(
                    targetMonth)) {

                results.add(expense);
            }
        }

        return results;
    }

    // =========================
    // User Monthly Expenses
    // =========================
    private ArrayList<Expense> getUserMonthlyExpenses(
            int userId,
            String month) {

        YearMonth targetMonth =
                YearMonth.parse(month);

        ArrayList<Expense> results =
                new ArrayList<>();

        for (Expense expense :
                expenseService.getExpensesByUserId(
                        userId)) {

            LocalDate date =
                    LocalDate.parse(
                            expense.getDate()
                    );

            YearMonth expenseMonth =
                    YearMonth.from(date);

            if (expenseMonth.equals(
                    targetMonth)) {

                results.add(expense);
            }
        }

        return results;
    }

    // =========================
    // Overall Monthly Total
    // =========================
    public double calculateMonthlyTotal(
            String month) {

        double total = 0;

        for (Expense expense :
                getMonthlyExpenses(month)) {

            total += expense.getAmount();
        }

        return total;
    }

    // =========================
    // User Monthly Total
    // =========================
    public double calculateUserMonthlyTotal(
            int userId,
            String month) {

        double total = 0;

        for (Expense expense :
                getUserMonthlyExpenses(
                        userId,
                        month
                )) {

            total += expense.getAmount();
        }

        return total;
    }

    // =========================
    // Overall Monthly Categories
    // =========================
    public Map<String, Double>
    getMonthlyCategoryTotals(
            String month) {

        return getCategoryTotals(
                getMonthlyExpenses(month)
        );
    }

    // =========================
    // User Monthly Categories
    // =========================
    public Map<String, Double>
    getUserMonthlyCategoryTotals(
            int userId,
            String month) {

        return getCategoryTotals(
                getUserMonthlyExpenses(
                        userId,
                        month
                )
        );
    }

    // =========================
    // Overall Monthly Highest
    // =========================
    public Expense getMonthlyHighestExpense(
            String month) {

        return getHighestExpense(
                getMonthlyExpenses(month)
        );
    }

    // =========================
    // Overall Monthly Lowest
    // =========================
    public Expense getMonthlyLowestExpense(
            String month) {

        return getLowestExpense(
                getMonthlyExpenses(month)
        );
    }

    // =========================
    // User Monthly Highest
    // =========================
    public Expense getUserMonthlyHighestExpense(
            int userId,
            String month) {

        return getHighestExpense(
                getUserMonthlyExpenses(
                        userId,
                        month
                )
        );
    }

    // =========================
    // User Monthly Lowest
    // =========================
    public Expense getUserMonthlyLowestExpense(
            int userId,
            String month) {

        return getLowestExpense(
                getUserMonthlyExpenses(
                        userId,
                        month
                )
        );
    }
}