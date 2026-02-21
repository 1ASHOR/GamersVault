package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;
import org.example.gamersvault.database.VaultController;

public class AddGameScreen {

    // Stage voor het toevoegen van een nieuw spel
    private Stage addGameStage;
    // Hoofd layout (GridPane) waarin alle elementen worden geplaatst
    private GridPane rootGrid;

    // Invoervelden voor alle gamegegevens
    private TextField name;
    private TextArea description;
    private TextField hours;
    private TextField progress;
    private TextArea opinion;

    // Knop om het spel op te slaan
    private Button saveGameButton;
    // Controller die communicatie met de database regelt
    private VaultController vc;

    public AddGameScreen() {
        // Maak een nieuwe GridPane layout aan
        rootGrid = new GridPane();
        // Maak een nieuwe scene met vaste grootte
        Scene scene = new Scene(rootGrid, 800, 500);

        // Voeg UI-elementen, styling en functionaliteit functies toe
        addElements();
        addStyling();
        saveGame();

        // Maak een nieuwe Stage en koppel de scene
        addGameStage = new Stage();
        addGameStage.setScene(scene);
        // Toon het venster
        addGameStage.show();
    }

    private void addElements(){
        // Maak labels en invoervelden aan
        Label giveName = new Label("Name");
        name = new TextField();
        description = new TextArea();
        Label givePlaytime = new Label("Playtime");
        hours = new TextField();
        Label giveProgress = new Label("Progress");
        progress = new TextField();
        opinion = new TextArea();
        Label giveGenre = new Label("Genre");
        Label givePlatform = new Label("Platform");
        Label giveDeveloper = new Label("Developer");

        // Maak de knop aan om het spel toe te voegen
        saveGameButton = new Button("Add game to vault");
        // Initialiseer de controller met een nieuwe databaseverbinding
        vc = new VaultController(new Database());

        // Voeg alle labels en invoervelden toe aan de GridPane
        rootGrid.add(giveName, 0, 0, 1, 1);
        rootGrid.add(name, 1, 0, 1, 1);
        rootGrid.add(givePlaytime, 0, 1, 1, 1);
        rootGrid.add(hours, 1, 1, 1, 1);
        rootGrid.add(giveProgress, 0, 2, 1, 1);
        rootGrid.add(progress, 1, 2, 1, 1);
        rootGrid.add(giveGenre, 0, 3, 1, 1);
        rootGrid.add(givePlatform, 0, 4, 1, 1);
        rootGrid.add(giveDeveloper, 0, 5, 1, 1);
        rootGrid.add(saveGameButton, 2, 6, 1, 1);

        // Voeg ComboBoxen (met data uit de database) toe aan de layout
        rootGrid.add(vc.getGenres(), 1, 3, 1, 1);
        rootGrid.add(vc.getPlatforms(), 1, 4, 1, 1);
        rootGrid.add(vc.getDevs(), 1, 5, 1, 1);

        // Voeg tekstvakken toe die meerdere gridcellen innemen
        rootGrid.add(description, 2, 0, 2, 2);
        rootGrid.add(opinion, 2, 2, 2, 2);
    }

    private void addStyling(){
        // Stel horizontale en verticale ruimte tussen elementen in
        rootGrid.setHgap(20);
        rootGrid.setVgap(20);
        // Stel padding rondom de layout in
        rootGrid.setPadding(new Insets(20));

        // Placeholder tekst voor beschrijving
        description.setPromptText("Give game description...");
        description.setWrapText(true); // Zorgt dat tekst automatisch wordt afgebroken
        // Placeholder tekst voor mening
        opinion.setPromptText("Give opinion...");
        opinion.setWrapText(true); // Zorgt dat tekst automatisch wordt afgebroken
    }

    public void saveGame(){

        // Voeg een actie toe aan de knop
        saveGameButton.setOnAction(e -> {

            // Zet ingevoerde tekst om naar numerieke waarden
            double playtime = Double.parseDouble(hours.getText());
            int progression = Integer.parseInt(progress.getText());

            // Haal geselecteerde waarden op uit de ComboBoxen
            String genre = vc.getSelectedGenre();
            String platform = vc.getSelectedPlatform();
            String dev = vc.getSelectedDev();

            // Voeg het spel toe aan de database via de controller
            vc.addToVault(name.getText(), description.getText(), playtime, progression, opinion.getText(), genre, platform, dev);
            // Sluit het huidige venster
            addGameStage.close();

            // Open het Vault-overzichtsscherm opnieuw
            VaultScreen vaultScreen = new VaultScreen(addGameStage);
            vaultScreen.getVaultStage().show();
        });
    }
}
