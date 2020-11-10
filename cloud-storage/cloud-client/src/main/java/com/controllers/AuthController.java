package com.controllers;

import com.client.*;
import com.main.ClientApp;
import com.help.utils.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    private Network network = Network.getInstance();
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;
    FileService fileService = new FileService();


    public void btnLoginOnAction(ActionEvent event) {
        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showDialog("Authorization error", "First you need to enter your username and password!", Alert.AlertType.ERROR);
        } else {
            String username = replaceInvalidSymbols(loginField.getText());
            String password = replaceInvalidSymbols(passwordField.getText());
            fileService.sendCommand(network.getCurChannel(), String.format("/auth\n%s\n%s", username, password));
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void showDialog(String title, String msg, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, msg, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }

    private String replaceInvalidSymbols(String str) {
        return str.replaceAll("[^0-9a-zA-Z&!?$#*]", "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Thread t = new Thread(() -> {
//            network.
            network.getHandler().setCallback(serviceMsg -> {
//                System.out.println(serviceMsg);
                if (serviceMsg.equals("OK")) {
                    Platform.runLater(this::toMain);
                }
            });
        });
        t.start();
    }

    private void toMain() {
        ClientApp.getInstance().gotoMainApp();
    }
}
