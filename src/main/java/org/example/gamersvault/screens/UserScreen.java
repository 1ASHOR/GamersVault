package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;
import org.example.gamersvault.database.UserController;

public class UserScreen {

    // Stage voor het gebruikersprofiel
    private Stage profileStage;
    // Hoofd layout (VBox) waarin alle elementen verticaal worden geplaatst
    private VBox rootVbox;
    // Knop om profielgegevens op te slaan
    private Button saveButton;
    // Tekstvelden voor gamertag en Discord-naam
    private TextField gamertagTextField;
    private TextField discordTextField;

    public UserScreen(){
        // Maak een nieuwe VBox layout aan
        rootVbox = new VBox();
        // Maak een scene met vaste afmetingen
        Scene scene = new Scene(rootVbox, 400, 500);

        // Voeg invoerelementen, layout styling toe
        inputElements();
        layoutStyle();
        // Stel de functionaliteit van de save-knop in
        updateProfile();

        // Maak een nieuwe Stage en toon het scherm
        profileStage = new Stage();
        profileStage.setScene(scene);
        profileStage.show();
    }

    private void inputElements(){
        // Maak labels en tekstvelden aan
        Label gamertagLabel = new Label("Enter Gamertag");
        gamertagTextField = new TextField();
        Label discordLabel = new Label("Enter Discord Name");
        discordTextField = new TextField();
        // Maak de save-knop aan
        saveButton = new Button("Save");


        // Voeg alle elementen toe aan de VBox
        rootVbox.getChildren().addAll(gamertagLabel, gamertagTextField, discordLabel, discordTextField, saveButton);

        // Styling van de tekstvelden (tijdelijk, zou naar CSS moeten)
        gamertagTextField.setMaxWidth(200);
        gamertagTextField.setPrefHeight(30);
        discordTextField.setMaxWidth(200);
        discordTextField.setPrefHeight(30);

        // Lettergrootte instellen voor tekstvelden
        gamertagTextField.setStyle("-fx-font-size: 14px");
        discordTextField.setStyle("-fx-font-size: 14px");

        // Lettergrootte instellen voor labels
        gamertagLabel.setStyle("-fx-font-size: 16px");
        discordLabel.setStyle("-fx-font-size: 16px");

        // Styling van de save-knop
        saveButton.setStyle("-fx-font-size: 16px");
        saveButton.setPrefSize(100, 30);
    }

    private void layoutStyle(){
        // Positionering van de VBox-inhoud
        rootVbox.setAlignment(Pos.TOP_CENTER);
        // Ruimte tussen de elementen
        rootVbox.setSpacing(15);
        // Padding aan de bovenkant
        rootVbox.setPadding(new Insets(20, 0, 0, 0));
    }

    private void updateProfile(){
        // Maak benodigde objecten aan voor databasecommunicatie
        Database db = new Database();
        UserController pC = new UserController(db);

        // Stel de actie van de save-knop in
        saveButton.setOnAction(e -> {

            // Roep de methode aan om profielinformatie bij te werken
            pC.updateProfile(gamertagTextField.getText(), discordTextField.getText());

            // Sluit het huidige profielscherm
            profileStage.close();

            // Open opnieuw het Vault-overzicht
            VaultScreen vaultScreen = new VaultScreen(profileStage);
            vaultScreen.getVaultStage().show();
        });
    }
}
