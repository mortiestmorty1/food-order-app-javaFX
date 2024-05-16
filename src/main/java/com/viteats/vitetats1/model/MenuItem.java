package com.viteats.vitetats1.model;

public class MenuItem {
    private String name;
    private double price;
    private int stock;
    private int estimatedDeliveryTime; // in minutes

    public MenuItem(String name, double price, int stock, int estimatedDeliveryTime) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(int estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
}