package com.expensetracker;

import java.util.Scanner;


import com.expensetracker.admin.AdminService;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.service.AuthService;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.service.ReportService;
import com.expensetracker.service.UserService;
import com.expensetracker.ui.MainMenu;
import com.expensetracker.util.FileManager;

public class Main {

    public static void main(String[] args) {

        // =========================
        // File Manager
        // =========================
        FileManager fileManager =
                new FileManager();

        // =========================
        // Repositories
        // =========================
        ExpenseRepository
                expenseRepository =
                new ExpenseRepository(
                        fileManager
                );

        UserRepository
                userRepository =
                new UserRepository(
                        fileManager
                );

        // =========================
        // Services
        // =========================
        ExpenseService
                expenseService =
                new ExpenseService(
                        expenseRepository
                );

        UserService
                userService =
                new UserService(
                        userRepository
                );

        AuthService
                authService =
                new AuthService(
                        userService
                );

        authService.createDefaultAdmin();

        ReportService
                reportService =
                new ReportService(
                        expenseService
                );

        // =========================
        // Admin Service
        // =========================
        AdminService
                adminService =
                new AdminService(
                        userService,
                        expenseService
                );

        // =========================
        // Scanner
        // =========================
        Scanner scanner =
                new Scanner(
                        System.in
                );

        // =========================
        // Main Menu
        // =========================
        MainMenu mainMenu =
                new MainMenu(
                        authService,
                        userService,
                        expenseService,
                        reportService,
                        adminService,
                        scanner
                );

        // =========================
        // Start Application
        // =========================
        mainMenu.run();

        scanner.close();
    }
}