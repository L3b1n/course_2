package com.global_task.GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {
    private boolean chooseFile;
    private File selectedFile;
    private File directoryFile;
    private String flag;
    private String compressLevel;
    private ArrayList<String> keys = new ArrayList<>();

    @FXML
    private TextArea textArea;
    
    @FXML
    private TextArea choosedFile;

    @FXML
    private Button convert;

    @FXML
    private Label fileLabel;

    @FXML
    public void initialize() {
        textArea.setPromptText("Choosed actions");
        choosedFile.setPromptText("Choosed file");
        convert.setDisable(true);
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            convert.setDisable(newValue.trim().matches(""));
        });
    }
    
    @FXML
    void actionHelp(ActionEvent event) throws Throwable {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Application instructions");
        dialog.setHeaderText(null);
        dialog.setContentText("1. Choose a file to work with\n2. Choose actions to open the file which you have chosen (you must choose only needed options in the right order):\n\t• Decompress\n\t• Decode (you must enter decode key of length 16)\n\t• Unzip \n3. If you want to calculate information, click on calculate button\n4. Choose actions to write the file:\n\t• Compress (you must enter compress level in range 0-9)\n\t• Encode (you must enter encode key of length 16)\n\t• Zip\n5. Click on convert button and choose final directory");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        dialog.showAndWait();
    }
    
    @FXML
    void actionButton(ActionEvent event) throws Throwable {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/main/tests/com/global_task/"));
        fileChooser.setTitle("Choose file");
        File tempFile = fileChooser.showOpenDialog(new Stage());
        if(tempFile != null) {
            selectedFile = tempFile;
            choosedFile.setText(selectedFile.getName());
            textArea.setText("");
            chooseFile = true;
            flag = "";
        }
    }

    @FXML
    void actionConvert(ActionEvent event) throws Throwable {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        } else if(getExtension(selectedFile.getName()).equals("zip") && flag.contains("Ca") && !flag.contains("Zu")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choosed file connot be calculated, because it isn't unzipped");
            alert.getDialogPane().setPrefWidth(250.0);
            alert.showAndWait();
            return;
        }
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("./src/main/result/com/global_task/"));
        directoryChooser.setTitle("Choose derictory for result file");
        directoryFile = directoryChooser.showDialog(new Stage());
        
        Client clientSend = new Client();
        System.out.println("Please wait");
        clientSend.connectServer(9527);
        clientSend.sendFile(selectedFile, directoryFile, flag, keys, compressLevel);
        clientSend.stopClient();
        
        Client clientGet = new Client();
        clientGet.connectServer(9528);
        String result = clientGet.getResult();
        clientGet.stopClient();
        if(result.length() != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.getDialogPane().setPrefWidth(275.0);
            alert.showAndWait();
        } else {
            System.out.println("Done");
        }

        choosedFile.setText("");
        textArea.setText("");
        compressLevel = new String();
        directoryChooser = null;
        selectedFile = null;
        chooseFile = false;
        keys.clear();
        flag = "";
    }

    @FXML
    void actionClear(ActionEvent event) {
        compressLevel = new String();
        textArea.setText("");
        keys = new ArrayList<>();
        flag = "";
    }

    @FXML
    void actionCompress(ActionEvent event) {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        }
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enter compress level");
        dialog.setHeaderText("Enter compress level in range 0-9");
        ButtonType loginButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        TextField username = new TextField();
        username.setPromptText("compressLevel");
        username.setPrefWidth(275.0);
        grid.add(username, 0, 0);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(!newValue.trim().matches("\\d"));
        });
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> username.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loginButtonType) {
                return username.getText();
            }
            return null;
        });
        Optional<String> optional = dialog.showAndWait();
        if(optional.isPresent()) {
            flag += "Cc";
            compressLevel = optional.get();
            textArea.setText(textArea.getText() + "Compress, level: " + compressLevel + "\n");
        }
    }

    @FXML
    void actionDecode(ActionEvent event) {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        }
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enter key");
        dialog.setHeaderText("Enter key of lenght to decode file");
        ButtonType loginButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        TextField username = new TextField();
        username.setPromptText("Decode key");
        username.setPrefWidth(275.0);
        grid.add(username, 0, 0);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().length() != 16);
        });
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> username.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loginButtonType) {
                return username.getText();
            }
            return null;
        });
        Optional<String> optional = dialog.showAndWait();
        if(optional.isPresent()) {
            flag += "Ed";
            keys.add(optional.get());
            textArea.setText(textArea.getText() + "Decode, key: " + keys.get(keys.size() - 1) + "\n");
        }
    }
    
    @FXML
    void actionDecompress(ActionEvent event) {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        }
        flag += "Cd";
        textArea.setText(textArea.getText() + "Decompress\n");
    }
    
    @FXML
    void actionEncode(ActionEvent event) {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        }
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enter key");
        dialog.setHeaderText("Enter key of length 16 to encode file");
        ButtonType loginButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        TextField username = new TextField();
        username.setPromptText("Encode key");
        username.setPrefWidth(275.0);
        grid.add(username, 0, 0);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().length() != 16);
        });
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> username.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loginButtonType) {
                return username.getText();
            }
            return null;
        });
        Optional<String> optional = dialog.showAndWait();
        if(optional.isPresent()) {
            flag += "Ee";
            keys.add(optional.get());
            textArea.setText(textArea.getText() + "Encode, key: " + keys.get(keys.size() - 1) + "\n");
        }
    }

    @FXML
    void actionUnzip(ActionEvent event) {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        }
        if(fileCanBeUnzipped(selectedFile.getName())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("File cannot be unzipped");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
        } else {
            flag += "Zu";
            textArea.setText(textArea.getText() + "Unzip \n");
        }
    }

    @FXML
    void actionZip(ActionEvent event) {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        }
        if(fileCanBeZipped(selectedFile.getName())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("File is already zipped");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
        } else {
            flag += "Zz";
            textArea.setText(textArea.getText() + "Zip\n");
        }
    }
    
    @FXML
    void actionCalculate(ActionEvent event) {
        if(!chooseFile) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Choose a file to work with");
            alert.getDialogPane().setPrefWidth(20.0);
            alert.showAndWait();
            return;
        }
        flag += "Ca";
        textArea.setText(textArea.getText() + "Calculate\n");
    }

    private boolean fileCanBeUnzipped(String fileName) {
        if(flag.matches("([acdeCE]*Zz[acdeCE]*Zu[acdeCE]*)+?")) {
            return true;
        } else if(getExtension(fileName).equals("zip") && flag.matches("[acdeCE]*Zu([acdeCE]*Zz[acdeCE]*Zu[acdeCE]*)*?")) {
            return true; 
        } else if(!getExtension(fileName).equals("zip") && !flag.contains("Zz")) {
            return true;
        }
        return false;
    }

    private boolean fileCanBeZipped(String fileName) {
        if(flag.matches("([acdeCE]*Zu[acdeCE]*Zz[acdeCE]*)+?")) {
            return true;
        } else if(!getExtension(fileName).equals("zip") && flag.matches("[acdeCE]*Zz([acdeCE]*Zu[acdeCE]*Zz[acdeCE]*)*?")) {
            return true;
        } else if(getExtension(fileName).equals("zip") && !flag.contains("Zu")) {
            return true;
        }
        return false;
    }
    
    String getExtension(String fileName) {
        String extension = null;
        int i = fileName.lastIndexOf('.');
        if(i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
