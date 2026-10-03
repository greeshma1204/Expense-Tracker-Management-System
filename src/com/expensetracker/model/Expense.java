package com.expensetracker.model;

public class Expense {

    private int id;
    private String name;
    private double amount;
    private String category;
    private int userId;
    private String date;

    public Expense(
            int id,
            String name,
            double amount,
            String category,
            int userId,
            String date) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.userId = userId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {

        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                '}';
    }
}