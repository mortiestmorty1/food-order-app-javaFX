package com.viteats.vitetats1.controller;

import com.viteats.vitetats1.model.User;
import com.viteats.vitetats1.util.DataUtil;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = DataUtil.login(username, password);

        if (user != null) {
            try {
                String view = "";
                FXMLLoader loader = null;
                Parent root = null;
                Stage stage = (Stage) usernameField.getScene().getWindow();

                switch (user.getRole()) {
                    case "Admin":
                        view = "/fxml/AdminView.fxml";
                        loader = new FXMLLoader(getClass().getResource(view));
                        root = loader.load();
                        AdminController adminController = loader.getController();
                        adminController.setStage(stage);
                        adminController.setUsername(username);
                        break;
                    case "Customer":
                        view = "/fxml/CustomerView.fxml";
                        loader = new FXMLLoader(getClass().getResource(view));
                        root = loader.load();
                        CustomerController customerController = loader.getController();
                        customerController.setStage(stage);
                        customerController.setUsername(username);
                        break;
                    case "Driver":
                        view = "/fxml/DriverView.fxml";
                        loader = new FXMLLoader(getClass().getResource(view));
                        root = loader.load();
                        DriverController driverController = loader.getController();
                        driverController.setStage(stage);
                        driverController.setUsername(username);
                        break;
                    default:
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setContentText("Invalid role");
                        alert.show();
                        return;
                }

                stage.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Invalid username or password");
            alert.show();
        }
    }
}
