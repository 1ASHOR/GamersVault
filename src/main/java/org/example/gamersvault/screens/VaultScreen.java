package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;
import org.example.gamersvault.database.UserController;
import org.example.gamersvault.database.VaultController;

import java.sql.SQLException;
import java.sql.Statement;

public class VaultScreen {

    // Hoofdstage van het Vault-scherm
    private Stage vaultStage;
    // Root pane waarin alle layout onderdelen worden geplaatst
    private Pane rootPane;
    // Linker zijbalk (gebruikersinfo)
    private VBox vBox;
    // Bovenbalk met knoppen
    private HBox hBox;
    // ScrollPane waarin de games worden weergegeven
    private ScrollPane scrollPane;

    // Knoppen voor verschillende acties
    private Button accountButton;
    private Button deleteGameButton;
    private Button addGameButton;
    private Button viewGameButton;

    // Database en controllers
    private VaultController vc;
    private Database database;
    private UserController userController;

    public VaultScreen(Stage stage) {
        // Koppel de stage aan deze klasse
        vaultStage = stage;
        // Initialiseer database en controllers
        database = new Database();
        vc = new VaultController(database);
        userController = new UserController(database);

        // Maak root pane en scene aan
        rootPane = new Pane();
        Scene scene = new Scene(rootPane, 1200, 600);

        // Voeg de verschillende layout-onderdelen toe
        addVBOX();
        addHBox();
        addScrollPane();

        // Koppel functionaliteit aan knoppen
        updateUser();
        addGame();
        viewGame();
        deleteGame();

        // Stel de scene in op de stage
        vaultStage.setScene(scene);
    }

    // Geef de huidige stage terug
    public Stage getVaultStage() {
        return vaultStage;
    }

    private void addVBOX () {
        // Maak VBox aan voor gebruikersinformatie
        vBox = new VBox();

        // Stel grootte in (1/6 van de breedte)
        vBox.setPrefSize(rootPane.getWidth() / 6, rootPane.getHeight());

        // Centreer inhoud bovenaan
        vBox.setAlignment(Pos.TOP_CENTER);

        // Voeg rand toe als styling
        vBox.setStyle("-fx-border-color: black");

        // Voeg gebruikersinformatie toe aan VBox
        vBox.getChildren().add(userController.getUserInfo());
        // Voeg VBox toe aan rootPane
        rootPane.getChildren().add(vBox);
    }

    protected void addHBox(){
        // Maak HBox en knoppen aan
        hBox = new HBox();
        accountButton = new Button("Edit Profile");
        addGameButton = new Button("Add Game to Vault");
        viewGameButton = new Button("View selected game");
        deleteGameButton = new Button("Delete selected game");

        // Stel grootte in (rest van breedte, 1/6 van hoogte)
        hBox.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() / 6);
        // Zorg dat knoppen meegroeien met inhoud
        accountButton.setMinWidth(Region.USE_COMPUTED_SIZE);
        addGameButton.setMinWidth(Region.USE_COMPUTED_SIZE);
        // Positioneer HBox naast de VBox
        hBox.setLayoutX(vBox.getPrefWidth());
        // Lijn inhoud rechts uit
        hBox.setAlignment(Pos.CENTER_RIGHT);


        // Styling
        hBox.setStyle("-fx-border-color: black");
        hBox.setPadding(new Insets(0, 25, 0, 0));
        hBox.setSpacing(20);

        // Voeg knoppen en gamelijst (ComboBox) toe
        hBox.getChildren().addAll(accountButton, addGameButton, viewGameButton, deleteGameButton, vc.getGameList());
        // Voeg HBox toe aan rootPane
        rootPane.getChildren().add(hBox);
    }

    protected void addScrollPane() {
        // Maak ScrollPane aan
        scrollPane = new ScrollPane();

        // Stel grootte in (onder HBox, naast VBox)
        scrollPane.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() - hBox.getPrefHeight());

        // Positioneer ScrollPane correct
        scrollPane.setLayoutX(vBox.getPrefWidth());
        scrollPane.setLayoutY(hBox.getPrefHeight());

        // Voeg padding toe
        scrollPane.setPadding(new Insets(30));

        // Voeg content toe vanuit VaultController
        scrollPane.setContent(vc.showVault(scrollPane.getPrefWidth()));
        // Scrollbar instellingen
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // Voeg ScrollPane toe aan rootPane
        rootPane.getChildren().add(scrollPane);
    }

    private void updateUser() {
        // Open UserScreen bij klikken op profielknop
        accountButton.setOnAction(e -> {
            UserScreen profile = new UserScreen();
            vaultStage.close();
        });
    }

    private void addGame(){
        // Open AddGameScreen bij klikken op knop
        addGameButton.setOnAction(e -> {
            AddGameScreen game = new AddGameScreen();
            vaultStage.close();
        });
    }

    private void viewGame() {
        // Open detailweergave van geselecteerde game
        viewGameButton.setOnAction(e -> {
            GameDetailScreen gameDetail = new GameDetailScreen(vaultStage);
        });
    }

    // Geef HBox terug (gebruikt door subclasses)
    protected HBox getHBox() {
        return hBox;
    }

    // Geef breedte van ScrollPane terug
    protected double getScrollPaneWidth() {
        double i = scrollPane.getPrefWidth();
        return i;
    }

    protected double getScrollPaneHeight() {
        // Geef hoogte van ScrollPane terug
        double i = scrollPane.getPrefHeight();
        return i;
    }

    // Geef VaultController terug
    protected VaultController getVaultController() {
        return vc;
    }

    private void deleteGame() {
        // Functionaliteit voor verwijderen van geselecteerde game
        deleteGameButton.setOnAction(e -> {
            // Controleer of er een game geselecteerd is
            if(vc.getSelectedGame() != null){
                try {
                    // Maak statement aan en voer DELETE-query uit
                    Statement stmt = database.getConnection().createStatement();
                    stmt.execute("DELETE FROM game WHERE name LIKE '"+ vc.getSelectedGame() +"'");
                    System.out.println("game deleted");
                    // Vernieuw ScrollPane inhoud
                    addScrollPane();
                } catch (SQLException ex) {
                    // Gooi fout als er een SQL-probleem optreedt
                    throw new RuntimeException(ex);
                }
            } else {
                // Meld in console dat er geen game geselecteerd is
                System.out.println("no game selected");
            }
        });
    }
}