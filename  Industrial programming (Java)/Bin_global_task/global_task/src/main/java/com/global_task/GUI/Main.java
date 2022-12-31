package com.global_task.GUI;

import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(Main.class.getResource("Interface.fxml"));
        Scene scene = new Scene(parent, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Convert Application");
        stage.show();
    }

    public static void main(String[] args) throws Throwable {
        launch();
    }
}