package com.expensetracker.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.expensetracker.model.Expense;
import com.expensetracker.util.FileManager;

public class ExpenseRepository {

    private List<Expense> expenses;
    private FileManager fileManager;

    public ExpenseRepository(
            FileManager fileManager) {

        this.fileManager =
                fileManager;

        this.expenses =
                new ArrayList<>(
                        fileManager.loadExpenses()
                );
    }

    public void addExpense(Expense expense) {

        expenses.add(expense);

        fileManager.saveExpenses(expenses);
    }

    public ArrayList<Expense> findAllExpenses() {

        return new ArrayList<>(expenses);
    }

    public Expense findExpenseById(int id) {

        for (Expense expense : expenses) {

            if (expense.getId() == id) {

                return expense;
            }
        }

        return null;
    }

    public boolean updateExpense(
            Expense expense) {

        for (int i = 0;
                i < expenses.size();
                i++) {

            if (expenses.get(i).getId()
                    == expense.getId()) {

                expenses.set(i, expense);

                fileManager.saveExpenses(
                        expenses
                );

                return true;
            }
        }

        return false;
    }

    public boolean deleteExpense(int id) {

        for (int i = 0;
                i < expenses.size();
                i++) {

            if (expenses.get(i).getId()
                    == id) {

                expenses.remove(i);

                fileManager.saveExpenses(
                        expenses
                );

                return true;
            }
        }

        return false;
    }

    public boolean deleteExpensesByUserId(
            int userId) {

        boolean deleted = false;

        Iterator<Expense> iterator =
                expenses.iterator();

        while (iterator.hasNext()) {

            Expense expense =
                    iterator.next();

            if (expense.getUserId()
                    == userId) {

                iterator.remove();

                deleted = true;
            }
        }

        if (deleted) {

            fileManager.saveExpenses(
                    expenses
            );
        }

        return deleted;
    }
}