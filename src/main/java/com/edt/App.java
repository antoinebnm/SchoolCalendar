package com.edt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Stage primaryStage;
    public static Scene loginScene;
    public static Scene CalendarScene;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        loginScene = new Scene(new FXMLLoader(getClass().getResource("/views/auth.fxml")).load());
        CalendarScene = new Scene(new FXMLLoader(getClass().getResource("/views/calendar.fxml")).load());

        primaryStage.setTitle("Emploi du Temps - Connexion");
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        primaryStage.show();
    }

    public static void switchScene(Scene scene, String title) {
        primaryStage.setTitle("Emploi du Temps - " + title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}