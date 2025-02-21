package org.example.gamersvault;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.gamersvault.screens.VaultScreen;

import java.io.IOException;

public class GamersVault extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        VaultScreen homeScreen = new VaultScreen(stage);

        stage.setResizable(false);
        stage.setTitle("The Gamers Vault");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}