package com.edt.controller;

import com.edt.dao.UserDAO;
import com.edt.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import javax.swing.*;

import static com.edt.App.*;
import static com.edt.utils.DatabaseManager.getConnection;

public class AuthController {

    @FXML
    private TextField emailField;

    @FXML
    private Label forgotPwd;

    @FXML
    private CheckBox keepAuth;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordFieldShown;

    @FXML
    private CheckBox showPwd;

    @FXML
    void login(ActionEvent event) {
        try {
            System.out.println("Keep auth? " + keepAuth.isSelected());
            UserDAO userDAO = new UserDAO(getConnection());
            User user = userDAO.findByEmail(emailField.getText());
            if (user == null) {
                System.out.println("User not found");
                return;
            }
            passwordField.setText(passwordFieldShown.getText());
            if (user.getMotDePasse().equals(passwordField.getText())) {
                System.out.println("Login OK");
                loginButton.setDisable(true);
                Thread.sleep(100);
                if (user.getType().equals("Etudiant")) {
                    Parent root = CalendarLoader.load();
                    DashboardController controller = CalendarLoader.getController();

                    controller.setUserId(user.getId());
                    switchScene(root, "Calendrier Etudiant");
                }
            } else {
                System.out.println("Wrong password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void forgotPwd(ActionEvent event) {}

    @FXML
    void togglePwdView(ActionEvent event) {
        passwordFieldShown.setVisible(showPwd.isSelected());
        passwordField.setVisible(!showPwd.isSelected());
    }

    @FXML
    void linkFields(KeyEvent event) {
        if (showPwd.isSelected()) passwordField.setText(passwordFieldShown.getText() + event.getText());
        else passwordFieldShown.setText(passwordField.getText() + event.getText());
    }

    @FXML
    void toggleKeepAuth(ActionEvent event) {

    }

}
