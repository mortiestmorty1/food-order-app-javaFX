package com.viteats.vitetats1.model;

import java.util.List;

public class Order {
    private String username;
    private List<String> items;
    private double revenue;
    private String status;
    private String driver;
    private String modeOfEating;
    private String paymentMethod;

    public Order(String username, List<String> items, double revenue, String modeOfEating, String paymentMethod) {
        this.username = username;
        this.items = items;
        this.revenue = revenue;
        this.status = "Pending";
        this.driver = null;
        this.modeOfEating = modeOfEating;
        this.paymentMethod = paymentMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    public String getModeOfEating() {
        return modeOfEating;
    }

    public void setModeOfEating(String modeOfEating) {
        this.modeOfEating = modeOfEating;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}