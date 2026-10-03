package com.expensetracker.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static boolean isValidAmount(double amount) {

        return amount > 0;
    }

    public static boolean isValidName(String name) {

        return name != null
                && !name.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {

        return email != null
                && email.contains("@")
                && email.contains(".");
    }

    public static boolean isValidCategory(String category) {

        return category != null
                && !category.trim().isEmpty();
    }

    public static boolean isValidPassword(String password) {

        return password != null
                && !password.trim().isEmpty()
                && password.length() >= 6;
    }

    public static boolean isValidDate(String date) {

        if (date == null || date.trim().isEmpty()) {
            return false;
        }

        try {

            LocalDate.parse(date);

            return true;

        } catch (DateTimeParseException e) {

            return false;
        }
    }

    public static boolean isValidMonth(String month) {

        if (month == null || month.trim().isEmpty()) {
            return false;
        }

        try {

            YearMonth.parse(month);

            return true;

        } catch (DateTimeParseException e) {

            return false;
        }
    }

    public static boolean isValidBudget(double budget) {

        return budget >= 0;
    }
}
