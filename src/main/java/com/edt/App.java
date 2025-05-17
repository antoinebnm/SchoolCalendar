package com.edt;

import com.edt.controller.DashboardController;
import com.edt.utils.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;
    public static FXMLLoader loginLoader;
    public static FXMLLoader CalendarLoader;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        loginLoader = new FXMLLoader(getClass().getResource("/views/auth.fxml"));
        CalendarLoader = new FXMLLoader(getClass().getResource("/views/calendar.fxml"));

        primaryStage.setResizable(true);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        //Parent root = CalendarLoader.load();
        //DashboardController c = CalendarLoader.getController();
        //c.setUserId(3);
        Parent root = loginLoader.load();
        switchScene(root, "Connexion");
    }

    public static void switchScene(Parent root, String title) {
        primaryStage.setTitle("Emploi du Temps - " + title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        DatabaseManager.testConnection();
        launch(args);
    }
}