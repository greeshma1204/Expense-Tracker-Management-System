package com.expensetracker.model;

public class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private Role role;
    private double monthlyBudget;

    // 5-argument constructor
    public User(
            int userId,
            String name,
            String email,
            String password,
            Role role) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.monthlyBudget = 0.0;
    }

    // 6-argument constructor
    public User(
            int userId,
            String name,
            String email,
            String password,
            Role role,
            double monthlyBudget) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.monthlyBudget = monthlyBudget;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    @Override
    public String toString() {

        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", monthlyBudget=" + monthlyBudget +
                '}';
    }
}