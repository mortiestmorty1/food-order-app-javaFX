package com.viteats.vitetats1.model;

public class Customer extends User {
    private double creditBalance;

    public Customer(String username, String password, double creditBalance) {
        super(username, password, "Customer");
        this.creditBalance = creditBalance;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }
}