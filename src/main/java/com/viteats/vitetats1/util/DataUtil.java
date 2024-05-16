package com.viteats.vitetats1.util;

import com.viteats.vitetats1.model.*;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    private static List<User> users = new ArrayList<>();
    private static List<MenuItem> menuItems = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static List<Feedback> feedbacks = new ArrayList<>();
    private static List<Promotion> promotions = new ArrayList<>();

    static {
        // Users
        users.add(new Admin("admin", "admin"));
        users.add(new Customer("customer", "customer", 100.0));
        users.add(new Driver("driver", "driver", "D001", "Car", true));

        // Menu Items
        menuItems.add(new MenuItem("Pizza", 12.99, 10, 30));
        menuItems.add(new MenuItem("Burger", 8.99, 20, 15));
        menuItems.add(new MenuItem("Pasta", 10.99, 15, 25));
        menuItems.add(new MenuItem("Salad", 6.99, 25, 10));
    }

    public static User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public static void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static List<Order> getOrdersForDelivery() {
        List<Order> deliveryOrders = new ArrayList<>();
        for (Order order : orders) {
            if ("Delivery".equals(order.getModeOfEating())) {
                deliveryOrders.add(order);
            }
        }
        return deliveryOrders;
    }

    public static void updateOrderStatus(int orderId, String status, String driver) {
        Order order = orders.get(orderId);
        order.setStatus(status);
        order.setDriver(driver);
    }

    public static double calculateDriverEarnings(String driverUsername) {
        double earnings = 0.0;
        for (Order order : orders) {
            if (order.getDriver() != null && order.getDriver().equals(driverUsername) && order.getStatus().equals("Delivered")) {
                earnings += order.getRevenue() * 0.10;
            }
        }
        return earnings;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public static void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public static List<Promotion> getPromotions() {
        return promotions;
    }

    public static void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public static void removePromotion(Promotion promotion) {
        promotions.remove(promotion);
    }

    public static void resetData() {
        users.clear();
        menuItems.clear();
        orders.clear();
        feedbacks.clear();
        promotions.clear();

        // Reset initial data
        users.add(new Admin("admin", "admin"));
        users.add(new Customer("customer", "customer", 100.0));
        users.add(new Driver("driver", "driver", "D001", "Car", true));

        menuItems.add(new MenuItem("Pizza", 12.99, 10, 30));
        menuItems.add(new MenuItem("Burger", 8.99, 20, 15));
        menuItems.add(new MenuItem("Pasta", 10.99, 15, 25));
        menuItems.add(new MenuItem("Salad", 6.99, 25, 10));
    }
}