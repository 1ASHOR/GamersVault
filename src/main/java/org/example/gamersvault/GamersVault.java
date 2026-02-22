package org.example.gamersvault;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.gamersvault.screens.VaultScreen;

import java.io.IOException;

public class GamersVault extends Application {
    // Deze methode wordt automatisch aangeroepen bij het starten van de JavaFX-applicatie
    @Override
    public void start(Stage stage) throws IOException {

        // Maak het hoofdscherm (VaultScreen) aan en geef de primaire stage mee
        VaultScreen homeScreen = new VaultScreen(stage);

        // Zorg dat het venster niet handmatig vergroot/verkleind kan worden
        stage.setResizable(false);
        // Stel de titel van het venster in
        stage.setTitle("The Gamers Vault");
        // Toon het venster
        stage.show();
    }

    // Main-methode: startpunt van het programma
    public static void main(String[] args) {
        launch();
    }
}