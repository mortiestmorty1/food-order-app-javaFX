package com.viteats.vitetats1.controller;

import com.viteats.vitetats1.model.MenuItem;
import com.viteats.vitetats1.model.Order;
import com.viteats.vitetats1.model.Promotion;
import com.viteats.vitetats1.util.DataUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MenuItemController {

    private Stage stage;
    private String username;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    ListView<String> menuListView;

    @FXML
    ComboBox<String> promotionsComboBox;

    @FXML
    ComboBox<String> modeOfEatingComboBox;

    @FXML
    ComboBox<String> paymentMethodComboBox;

    @FXML
    public void initialize() {
        List<MenuItem> menuItems = DataUtil.getMenuItems();
        for (MenuItem item : menuItems) {
            menuListView.getItems().add(item.getName() + " - $" + item.getPrice() + " - Stock: " + item.getStock() + " - Delivery Time: " + item.getEstimatedDeliveryTime() + " mins");
        }
        menuListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        promotionsComboBox.getItems().add("No promotion selected");
        List<Promotion> promotions = DataUtil.getPromotions();
        for (Promotion promotion : promotions) {
            promotionsComboBox.getItems().add(promotion.getDescription() + " - " + promotion.getDiscount() + "%");
        }
        promotionsComboBox.setValue("No promotion selected");

        modeOfEatingComboBox.getItems().addAll("Dine In", "Pickup", "Delivery");
        paymentMethodComboBox.getItems().addAll("Apple Pay", "Credit Card", "Cash on Delivery");
    }

    @FXML
    protected void placeOrder() {
        List<String> selectedItems = menuListView.getSelectionModel().getSelectedItems();
        if (selectedItems.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No items selected. Please select items to place an order.");
            alert.show();
            return;
        }

        String modeOfEating = modeOfEatingComboBox.getValue();
        String paymentMethod = paymentMethodComboBox.getValue();

        if (modeOfEating == null || paymentMethod == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please select a mode of eating and a payment method.");
            alert.show();
            return;
        }

        List<MenuItem> menuItems = DataUtil.getMenuItems();
        double totalRevenue = 0;
        for (String selectedItem : selectedItems) {
            for (MenuItem menuItem : menuItems) {
                if (selectedItem.contains(menuItem.getName())) {
                    totalRevenue += menuItem.getPrice();
                }
            }
        }

        double discount = 0;
        String selectedPromotion = promotionsComboBox.getValue();
        if (!selectedPromotion.equals("No promotion selected")) {
            for (Promotion promotion : DataUtil.getPromotions()) {
                if (selectedPromotion.contains(promotion.getDescription())) {
                    discount = promotion.getDiscount();
                    break;
                }
            }
        }

        double discountedPrice = totalRevenue - (totalRevenue * discount / 100);

        Order order = new Order(username, new ArrayList<>(selectedItems), totalRevenue, modeOfEating, paymentMethod);
        DataUtil.addOrder(order);

        StringBuilder orderSummary = new StringBuilder("Order placed for: \n");
        for (String item : selectedItems) {
            orderSummary.append(item).append("\n");
        }

        if (selectedPromotion.equals("No promotion selected")) {
            orderSummary.append("Total Price: $").append(totalRevenue);
        } else {
            orderSummary.append("Total Price after Discount: $").append(discountedPrice);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(orderSummary.toString());
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