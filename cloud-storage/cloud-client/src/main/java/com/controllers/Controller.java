package com.controllers;

import com.client.Network;
import com.help.utils.FileInfo;
import com.help.utils.FileService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

public class Controller {
    private Network network = Network.getInstance();

    @FXML
    VBox serverTable, clientTable;
    @FXML
    TextField clientPathField, serverPathField;
    @FXML
    TableView clientFilesTable, serverFilesTable;

    FileService fileService = new FileService();
    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void btnDownload(ActionEvent actionEvent) {

//        if (serverPC.getSelectedFilename() == null && clientPC.getSelectedFilename() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "No file was selected.", ButtonType.OK);
//            alert.showAndWait();
//            return;
//        }

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

    public void btnNewFolder(ActionEvent actionEvent) {
        if (clientTable.isFocused()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Create new folder");
            dialog.setHeaderText("Enter a name for the new folder");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    fileService.createDirectory(Paths.get(getCurrentPath()), result.get());
                } catch (Exception e) {
                    Alert alertError = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                    alertError.setTitle(e.getClass().getSimpleName());
                    alertError.showAndWait();
                } finally {
                    updateClientList(Paths.get(getCurrentPath()));
                }
            }
        }
    }

    public String getCurrentPath() {
        return clientPathField.getText();
    }

    public void updateClientList(Path path) {
        try {
            clientPathField.setText(path.normalize().toAbsolutePath().toString());
            clientFilesTable.getItems().clear();
            clientFilesTable.getItems().addAll(Files.list(path).map(FileInfo::new).collect(Collectors.toList()));
            clientFilesTable.sort();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Something went wrong", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
