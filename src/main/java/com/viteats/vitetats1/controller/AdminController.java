package com.viteats.vitetats1.controller;

import com.viteats.vitetats1.model.*;
import com.viteats.vitetats1.util.DataUtil;
import com.viteats.vitetats1.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AdminController {

    private Stage stage;
    private String username;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    ListView<String> userListView;

    @FXML
    ListView<String> feedbackListView;
    @FXML
    ListView<String> menuListView;
    @FXML
    ListView<String> promotionListView;

    @FXML
    ListView<String> orderListView;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    TextField promotionDescriptionField;

    @FXML
    TextField promotionDiscountField;

    @FXML
    TextField driverUsernameField;

    @FXML
    TextField driverPasswordField;

    @FXML
    TextField driverIDField;

    @FXML
    TextField driverVehicleTypeField;

    @FXML
    TextField driverAvailabilityField;
    @FXML
    TextField itemNameField;

    @FXML
    TextField itemPriceField;

    @FXML
    TextField itemStockField;

    @FXML
    TextField itemDeliveryTimeField;

    @FXML
    protected void initialize() {
        loadUsers();
        loadFeedbacks();
        loadPromotions();
        loadOrders();
        loadMenuItems();
    }

    void loadUsers() {
        List<User> users = DataUtil.getUsers();
        userListView.getItems().clear();
        for (User user : users) {
            userListView.getItems().add(user.getUsername() + " - " + user.getRole());
        }
    }

    private void loadFeedbacks() {
        List<Feedback> feedbacks = DataUtil.getFeedbacks();
        feedbackListView.getItems().clear();
        for (Feedback feedback : feedbacks) {
            feedbackListView.getItems().add(feedback.getUsername() + ": " + feedback.getMessage());
        }
    }

    private void loadPromotions() {
        List<Promotion> promotions = DataUtil.getPromotions();
        promotionListView.getItems().clear();
        for (Promotion promotion : promotions) {
            promotionListView.getItems().add(promotion.getDescription() + " - " + promotion.getDiscount() + "%");
        }
    }
    private void loadMenuItems() {
        List<MenuItem> menuItems = DataUtil.getMenuItems();
        menuListView.getItems().clear();
        for (MenuItem item : menuItems) {
            menuListView.getItems().add(item.getName() + " - $" + item.getPrice() + " - Stock: " + item.getStock() + " - Delivery Time: " + item.getEstimatedDeliveryTime() + " mins");
        }
    }

    @FXML
    void loadOrders() {
        List<Order> orders = DataUtil.getOrders();
        orderListView.getItems().clear();
        for (Order order : orders) {
            orderListView.getItems().add(
                    "Order by: " + order.getUsername() +
                            " - Driver: " + (order.getDriver() != null ? order.getDriver() : "None") +
                            " - Status: " + order.getStatus() +
                            " - Items: " + String.join(", ", order.getItems()) +
                            " - Mode of Eating: " + order.getModeOfEating() +
                            " - Payment Method: " + order.getPaymentMethod()
            );
        }
    }

    @FXML
    protected void addUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Username and password cannot be empty.");
            alert.show();
            return;
        }
        User newUser = new Customer(username, password, 100.0); // Default to Customer to only add customers here
        DataUtil.addUser(newUser);
        loadUsers();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("User added successfully.");
        alert.show();
    }

    @FXML
    protected void removeUser() {
        int selectedIndex = userListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            User user = DataUtil.getUsers().get(selectedIndex);
            DataUtil.removeUser(user);
            loadUsers();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("User removed successfully.");
            alert.show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("No user selected. Please select a user to remove.");
            alert.show();
        }
    }

    @FXML
    protected void addPromotion() {
        String description = promotionDescriptionField.getText();
        double discount;
        try {
            discount = Double.parseDouble(promotionDiscountField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Invalid discount value.");
            alert.show();
            return;
        }
        if (description.isEmpty() || discount <= 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Description cannot be empty and discount must be greater than 0.");
            alert.show();
            return;
        }
        Promotion promotion = new Promotion(description, discount);
        DataUtil.addPromotion(promotion);
        loadPromotions();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Promotion added successfully.");
        alert.show();
    }

    @FXML
    protected void removePromotion() {
        int selectedIndex = promotionListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Promotion promotion = DataUtil.getPromotions().get(selectedIndex);
            DataUtil.removePromotion(promotion);
            loadPromotions();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Promotion removed successfully.");
            alert.show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("No promotion selected. Please select a promotion to remove.");
            alert.show();
        }
    }

    @FXML
    protected void addDriver() {
        String username = driverUsernameField.getText();
        String password = driverPasswordField.getText();
        String driverID = driverIDField.getText();
        String vehicleType = driverVehicleTypeField.getText();
        boolean isAvailable = Boolean.parseBoolean(driverAvailabilityField.getText());

        if (username.isEmpty() || password.isEmpty() || driverID.isEmpty() || vehicleType.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("All fields must be filled.");
            alert.show();
            return;
        }

        Driver newDriver = new Driver(username, password, driverID, vehicleType, isAvailable);
        DataUtil.addUser(newDriver);
        loadUsers();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Driver added successfully.");
        alert.show();
    }

    @FXML
    protected void viewFeedback() {
        // Feedback viewing logic is already handled in initialize
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Feedbacks loaded.");
        alert.show();
    }
    @FXML
    protected void addMenuItem() {
        String name = itemNameField.getText();
        double price;
        int stock;
        int deliveryTime;

        try {
            price = Double.parseDouble(itemPriceField.getText());
            stock = Integer.parseInt(itemStockField.getText());
            deliveryTime = Integer.parseInt(itemDeliveryTimeField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Invalid price, stock, or delivery time value.");
            alert.show();
            return;
        }

        if (name.isEmpty() || price <= 0 || stock <= 0 || deliveryTime <= 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("All fields must be filled with valid values.");
            alert.show();
            return;
        }

        MenuItem menuItem = new MenuItem(name, price, stock, deliveryTime);
        DataUtil.addMenuItem(menuItem);
        loadMenuItems();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Menu item added successfully.");
        alert.show();
    }
    @FXML
    protected void logout() {
        // Navigating to the logout screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}