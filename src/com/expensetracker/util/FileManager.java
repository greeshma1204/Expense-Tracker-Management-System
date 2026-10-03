package com.expensetracker.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.expensetracker.model.Expense;
import com.expensetracker.model.Role;
import com.expensetracker.model.User;

public class FileManager {

    private String userFile = "users.txt";
    private String expenseFile = "expenses.txt";

    // =========================
    // Save Expenses
    // =========================
    public void saveExpenses(List<Expense> expenses) {

        try (BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(expenseFile))) {

            for (Expense expense : expenses) {

                writer.write(
                        expense.getId() + "|" +
                        expense.getName() + "|" +
                        expense.getAmount() + "|" +
                        expense.getCategory() + "|" +
                        expense.getUserId() + "|" +
                        expense.getDate()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while saving expenses."
            );
        }
    }

    // =========================
    // Load Expenses
    // =========================
    public List<Expense> loadExpenses() {

        List<Expense> expenses =
                new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(
                        new FileReader(expenseFile))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split("\\|");

                if (data.length < 6) {

                    System.out.println(
                            "Skipping old/invalid expense record."
                    );

                    continue;
                }

                int id =
                        Integer.parseInt(data[0]);

                String name =
                        data[1];

                double amount =
                        Double.parseDouble(data[2]);

                String category =
                        data[3];

                int userId =
                        Integer.parseInt(data[4]);

                String date =
                        data[5];

                Expense expense =
                        new Expense(
                                id,
                                name,
                                amount,
                                category,
                                userId,
                                date
                        );

                expenses.add(expense);
            }

        } catch (IOException e) {

            System.out.println(
                    "Expense file not found."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid expense data in file."
            );
        }

        return expenses;
    }

    // =========================
    // Save Users
    // =========================
    public void saveUsers(List<User> users) {

        try (BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(userFile))) {

            for (User user : users) {

                writer.write(
                        user.getUserId() + "|" +
                        user.getName() + "|" +
                        user.getEmail() + "|" +
                        user.getPassword() + "|" +
                        user.getRole() + "|" +
                        user.getMonthlyBudget()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while saving users."
            );
        }
    }

    // =========================
    // Load Users
    // =========================
    public List<User> loadUsers() {

        List<User> users =
                new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(
                        new FileReader(userFile))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split("\\|");

                if (data.length < 5) {

                    System.out.println(
                            "Skipping invalid user record."
                    );

                    continue;
                }

                int userId =
                        Integer.parseInt(data[0]);

                String name =
                        data[1];

                String email =
                        data[2];

                String password =
                        data[3];

                Role role =
                        Role.valueOf(data[4]);

                double monthlyBudget = 0.0;

                if (data.length >= 6) {

                    monthlyBudget =
                            Double.parseDouble(data[5]);
                }

                User user =
                        new User(
                                userId,
                                name,
                                email,
                                password,
                                role,
                                monthlyBudget
                        );

                users.add(user);
            }

        } catch (IOException e) {

            System.out.println(
                    "User file not found."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid user data in file."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid user role in file."
            );
        }

        return users;
    }
}