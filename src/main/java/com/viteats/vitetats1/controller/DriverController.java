package com.viteats.vitetats1.controller;

import com.viteats.vitetats1.model.Order;
import com.viteats.vitetats1.util.DataUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class DriverController {

    private Stage stage;
    private String username;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    ListView<String> ordersListView;

    @FXML
    public void initialize() {
        loadDeliveryOrders();
    }

    void loadDeliveryOrders() {
        List<Order> orders = DataUtil.getOrdersForDelivery();
        ordersListView.getItems().clear();
        for (Order order : orders) {
            ordersListView.getItems().add(
                    "Order by: " + order.getUsername() +
                            " - Items: " + String.join(", ", order.getItems()) +
                            " - Status: " + order.getStatus() +
                            " - Driver: " + (order.getDriver() != null ? order.getDriver() : "None"));
        }
    }

    @FXML
    protected void acceptOrder() {
        int selectedIndex = ordersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            DataUtil.updateOrderStatus(selectedIndex, "Accepted", username);
            loadDeliveryOrders();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Order accepted.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No order selected. Please select an order to accept.");
            alert.show();
        }
    }

    @FXML
    protected void updateStatus() {
        int selectedIndex = ordersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            DataUtil.updateOrderStatus(selectedIndex, "Delivered", username);
            loadDeliveryOrders();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Order status updated to Delivered.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No order selected. Please select an order to update.");
            alert.show();
        }
    }

    @FXML
    protected void viewEarnings() {
        double earnings = DataUtil.calculateDriverEarnings(username);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Your earnings: $" + earnings);
        alert.show();
    }

    @FXML
    protected void logout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
