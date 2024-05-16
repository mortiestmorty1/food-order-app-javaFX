package com.viteats.vitetats1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class JavaFXTest {

    private static final CountDownLatch latch = new CountDownLatch(1);

    @BeforeAll
    public static void initJFX() throws InterruptedException {
        new Thread(() -> {
            try {
                Application.launch(TestApp.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        latch.await(5, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void teardown() {
        Platform.exit();
    }

    public static class TestApp extends Application {
        @Override
        public void start(Stage primaryStage) {
            latch.countDown();
        }
    }
}
