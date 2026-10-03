# Expense Tracker Management System

A Core Java console-based application for managing personal expenses, monthly budgets, and expense reports.

## 📌 Project Overview

The Expense Tracker Management System allows users to register, log in, manage their expenses, track spending, and generate reports.

The application also provides an Admin role for managing users and expenses.

## 🚀 Features

### User Features
- User Registration
- User Login
- Add Expense
- View My Expenses
- Update Expense
- Delete Expense
- Search Expense by Category
- Search Expense by Date
- Sort Expenses
- View Expense Reports
- View Monthly Reports
- Set Monthly Budget
- View Budget Status

### Admin Features
- View All Users
- View All Expenses
- Delete Users
- Delete Expenses
- View Overall Reports

## 🛠️ Technologies Used

- Java
- OOP Concepts
- Collections Framework
- File Handling
- Exception Handling
- Java Date & Time API
- Git & GitHub

## 🏗️ Project Architecture

```text
Main
  ↓
MainMenu
  ↓
Login / Register
  ↓
 ┌───────────────┐
 ↓               ↓
USER           ADMIN
 ↓               ↓
UserMenu      AdminMenu
 ↓               ↓
ExpenseService AdminService
 ↓               ↓
ExpenseRepository / UserRepository
          ↓
      FileManager
          ↓
      .txt Files
ExpenseTracker
│
├── model
│   ├── User.java
│   ├── Expense.java
│   └── Role.java
│
├── repository
│   ├── UserRepository.java
│   └── ExpenseRepository.java
│
├── service
│   ├── AuthService.java
│   ├── UserService.java
│   ├── ExpenseService.java
│   └── ReportService.java
│
├── admin
│   └── AdminService.java
│
├── ui
│   ├── MainMenu.java
│   ├── UserMenu.java
│   └── AdminMenu.java
│
├── util
│   ├── FileManager.java
│   └── InputValidator.java
│
└── exception
    ├── UserNotFoundException.java
    ├── ExpenseNotFoundException.java
    ├── InvalidUserException.java
    └── InvalidExpenseException.java
## 💾 Data Storage

The application uses text files for persistent storage:

- `users.txt`
- `expenses.txt`

These files are excluded from GitHub using `.gitignore`.

## 📊 Reports

The application supports:

- Total Expense
- Category-wise Expense
- Highest Expense
- Lowest Expense
- User-wise Expense
- Monthly Expense
- Monthly Category-wise Expense
- Budget Status

## 🎯 Learning Concepts

This project demonstrates:

- Classes and Objects
- Encapsulation
- Inheritance
- Polymorphism
- Constructors
- Collections Framework
- ArrayList
- Exception Handling
- File Handling
- Java Date and Time API
- Layered Architecture
- Repository Pattern
- Service Layer

## 🔮 Future Enhancements

- MySQL database integration using JDBC
- Spring Boot REST API
- Web-based user interface
- Expense charts and dashboards
- Secure password hashing
- Email notifications
- Advanced analytics

## 👩‍💻 Author

**Greeshma Malineni**

GitHub: [greeshma1204](https://github.com/greeshma1204)
