package com.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {
    @FXML
    VBox serverTable, clientTable;
    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void btnCopyAction(ActionEvent actionEvent) {
        PanelControllerClient clientPC = (PanelControllerClient)clientTable.getProperties().get("copy");
        PanelControllerServer serverPC = (PanelControllerServer)serverTable.getProperties().get("copy");

        if (serverPC.getSelectedFilename() == null && clientPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No file was selected.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

//        if (serverTable.isFocused()) {
//
//        } else {
//            try {
//
//            } catch (IOException e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to copy file.");
//            }
//        }

//        PanelControllerClient srcPC = null;
//        PanelControllerServer dstPC = null;
//        if (serverPC.getSelectedFilename() != null) {
//            srcPC = serverPC;
//            dstPC = clientPC;
//        }
//        if (clientPC.getSelectedFilename() != null) {
//            srcPC = clientPC;
//            dstPC = serverPC;
//        }
//
//        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFilename());
//        Path dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());
//
//        try {
//            Files.copy(srcPath, dstPath);
//            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
//        } catch (IOException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось скопировать указанный файл", ButtonType.OK);
//            alert.showAndWait();
//        }
    }
}
