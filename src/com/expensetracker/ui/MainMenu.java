package com.expensetracker.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

import com.expensetracker.admin.AdminService;
import com.expensetracker.exception.InvalidUserException;
import com.expensetracker.model.Role;
import com.expensetracker.model.User;
import com.expensetracker.service.AuthService;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.service.ReportService;
import com.expensetracker.service.UserService;

public class MainMenu {

    private AuthService authService;
    private UserService userService;
    private ExpenseService expenseService;
    private ReportService reportService;
    private AdminService adminService;
    private Scanner scanner;

    public MainMenu(
            AuthService authService,
            UserService userService,
            ExpenseService expenseService,
            ReportService reportService,
            AdminService adminService,
            Scanner scanner) {

        this.authService = authService;
        this.userService = userService;
        this.expenseService = expenseService;
        this.reportService = reportService;
        this.adminService = adminService;
        this.scanner = scanner;
    }

    // ================= MAIN MENU =================

    public void showMenu() {

        System.out.println();
        System.out.println("===== EXPENSE TRACKER =====");
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    // ================= READ CHOICE =================

    public int readChoice() {

        while (true) {

            try {

                int choice = scanner.nextInt();
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

    // ================= PASSWORD =================

    private String readPassword() {

        JPasswordField passwordField =
                new JPasswordField();

        int result = JOptionPane.showConfirmDialog(
                null,
                passwordField,
                "Enter Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {

            return new String(
                    passwordField.getPassword()
            );

        }

        return "";
    }

    // ================= REGISTER =================

    public void registerUser() {

        System.out.println();
        System.out.println("===== USER REGISTRATION =====");
        System.out.println();

        System.out.print("Enter name: ");

        String name = scanner.nextLine();

        System.out.println();

        System.out.print("Enter email: ");

        String email = scanner.nextLine();

        String password = readPassword();

        try {

            boolean registered =
                    authService.register(
                            name,
                            email,
                            password
                    );

            if (registered) {

                System.out.println();
                System.out.println(
                        "Registration successful!"
                );

            } else {

                System.out.println();
                System.out.println(
                        "Email already registered."
                );
            }

        } catch (InvalidUserException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // ================= LOGIN =================

    public void loginUser() {

        System.out.println();
        System.out.println("===== LOGIN =====");
        System.out.println();

        System.out.print("Enter email: ");

        String email = scanner.nextLine();

        String password = readPassword();

        try {

            User user =
                    authService.login(
                            email,
                            password
                    );

            if (user == null) {

                System.out.println();

                System.out.println(
                        "Invalid email or password."
                );

                return;
            }

            System.out.println();

            System.out.println(
                    "Login successful!"
            );

            System.out.println();

            System.out.println(
                    "Welcome " + user.getName()
            );

            System.out.println(
                    "Role: " + user.getRole()
            );

            // ================= USER =================

            if (user.getRole() == Role.USER) {

                UserMenu userMenu =
                        new UserMenu(
                                userService,
                                expenseService,
                                reportService,
                                scanner,
                                user
                        );

                userMenu.run();
            }

            // ================= ADMIN =================

            else if (user.getRole() == Role.ADMIN) {

                AdminMenu adminMenu =
                        new AdminMenu(
                                adminService,
                                reportService,
                                scanner
                        );

                adminMenu.run();
            }

        } catch (InvalidUserException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // ================= RUN =================

    public void run() {

        boolean running = true;

        while (running) {

            showMenu();

            int choice = readChoice();

            switch (choice) {

                case 1:

                    registerUser();

                    break;

                case 2:

                    loginUser();

                    break;

                case 3:

                    System.out.println();

                    System.out.println(
                            "Thank you for using Expense Tracker!"
                    );

                    running = false;

                    break;

                default:

                    System.out.println();

                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }
}