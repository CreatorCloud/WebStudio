package com.mycompany.webstudioproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    static Stage prevStage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("signin"), 700, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        App.prevStage = stage;
    }
    
    public static void switchToAdmin(Stage stage) throws IOException {
        scene = new Scene(loadFXML("admin"), 1300, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        prevStage.close();
        App.prevStage = stage;
    }
    
    public static void switchToMain(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 1300, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        prevStage.close();
        App.prevStage = stage;
    }
    
    public static void backToLogin(Stage stage) throws IOException {
        scene = new Scene(loadFXML("signin"), 700, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        prevStage.close();
        App.prevStage = stage;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}