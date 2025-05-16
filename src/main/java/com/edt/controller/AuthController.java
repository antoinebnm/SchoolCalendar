package com.edt.controller;

import com.edt.dao.UserDAO;
import com.edt.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import javax.swing.*;

import static com.edt.App.CalendarScene;
import static com.edt.App.switchScene;
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
    private ProgressBar dataLoad;

    private void fillProgress()
    {
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                dataLoad.setProgress(i);

                // delay the thread
                Thread.sleep(100);
                i += 20;
            }
        }
        catch (Exception e) {
        }
    }

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
                dataLoad.setVisible(true);
                fillProgress();
                switchScene(CalendarScene,"Dashboard");
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
