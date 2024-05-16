module com.viteats.viteats1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.viteats.vitetats1 to javafx.fxml;
    opens com.viteats.vitetats1.controller to javafx.fxml;
    opens com.viteats.vitetats1.model to javafx.base;
    exports com.viteats.vitetats1;
    exports com.viteats.vitetats1.controller;
    exports com.viteats.vitetats1.model;
}