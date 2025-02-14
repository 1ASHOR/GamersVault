package org.example.gamersvault;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.gamersvault.screens.HomeScreen;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        HomeScreen homeScreen = new HomeScreen(stage);

        stage.setResizable(false);
        stage.setTitle("The Gamers Vault");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}