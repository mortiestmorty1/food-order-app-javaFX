package com.viteats.vitetats1.model;

public class Promotion {
    private String description;
    private double discount;

    public Promotion(String description, double discount) {
        this.description = description;
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
