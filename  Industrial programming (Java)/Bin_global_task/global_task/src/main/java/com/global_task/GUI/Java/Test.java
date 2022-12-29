package com.global_task.GUI.Java;

import java.io.IOException;

import com.global_task.java.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(Main.class.getResource("src/main/resources/com/global_task/Interface.fxml"));
        Scene scene = new Scene(parent, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Convert");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    // public static void main(String[] args) throws Throwable {
    //     while(true) {
    //         Server serverget = new Server();
    //         serverget.createServer(9527);
    //         System.out.println("Please wait");
    //         String fileName = serverget.getFile();
    //         File tempFile = new File(fileName);
    //         tempFile.deleteOnExit();
    //     }
    // }
}
