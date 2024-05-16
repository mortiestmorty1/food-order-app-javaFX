package com.viteats.vitetats1.controller;

import com.viteats.vitetats1.model.Feedback;
import com.viteats.vitetats1.model.Order;
import com.viteats.vitetats1.util.DataUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.List;

public class CustomerController {
    private Stage stage;
    private String username;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsername(String username) {
        this.username = username;
        loadOrders();
    }
    @FXML
    ListView<String> orderListView;

    @FXML
    protected void viewMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
            Parent root = loader.load();
            MenuItemController menuItemController = loader.getController();
            menuItemController.setStage(stage); // Passing the stage to the menu controller
            menuItemController.setUsername(username); // passing the username to menu controller
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void loadOrders() {
        List<Order> orders = DataUtil.getOrders();
        orderListView.getItems().clear();
        for (Order order : orders) {
            if (order.getUsername().equals(username)) {
                orderListView.getItems().add(
                        "Order by: " + order.getUsername() +
                                " - Driver: " + (order.getDriver() != null ? order.getDriver() : "None") +
                                " - Status: " + order.getStatus() +
                                " - Items: " + String.join(", ", order.getItems())
                );
            }
        }
    }
    @FXML
    TextField feedbackField;

    @FXML
    protected void addFeedback() {
        String feedbackMessage = feedbackField.getText();
        if (feedbackMessage.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Feedback cannot be empty.");
            alert.show();
            return;
        }
        DataUtil.addFeedback(new Feedback(username, feedbackMessage));
        feedbackField.clear();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Feedback added successfully.");
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

    public void checkPromotions() {

    }

    public void initialize() {
    }
}
