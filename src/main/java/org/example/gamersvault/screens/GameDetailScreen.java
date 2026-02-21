package org.example.gamersvault.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDetailScreen extends VaultScreen {

    // Knop om terug te keren naar het Vault-overzicht
    private Button returnToVault;
    // Database-object voor het ophalen van gamegegevens
    private Database database;
    // BorderPane layout voor dit scherm
    private BorderPane borderPane;

    public GameDetailScreen(Stage stage) {
        // Roep de constructor van VaultScreen aan
        super(stage);

        // Stel de actie in voor terugkeren naar Vault
        returnToVault(stage);
    }

    @Override
    protected void addScrollPane(){
        // Maak een nieuwe BorderPane aan
        borderPane = new BorderPane();
        // Voeg de game-weergave toe aan de layout
        borderPane.getChildren().add(viewGame());
    }

    @Override
    protected void addHBox(){
        // Roep eerst de standaard HBox-instellingen van de superclass aan
        super.addHBox();
        // Maak een knop aan om terug te gaan naar het Vault-scherm
        returnToVault = new Button("Return to Vault");
        // Voeg de knop toe aan de bestaande HBox
        super.getHBox().getChildren().add(returnToVault);

    }

    private void returnToVault(Stage stage) {
        // Voeg een actie toe aan de knop
        returnToVault.setOnAction(e -> {
            // Maak een nieuw VaultScreen aan (terug naar overzicht)
            VaultScreen vaultScreen = new VaultScreen(stage);
        });
    }

    private ScrollPane viewGame(){
        // Maak een nieuwe databaseverbinding aan
        database = new Database();
        // Maak een ScrollPane waarin de gamegegevens worden weergegeven
        ScrollPane scrollGame = new ScrollPane();
        // Stel de grootte van de ScrollPane in op basis van de superclass
        scrollGame.setPrefSize(super.getScrollPaneWidth(), super.getScrollPaneHeight());
        try {
            // Maak een SQL-statement aan
            Statement stmt = database.getConnection().createStatement();
            // Voer een query uit om de geselecteerde game op te halen
            ResultSet rs = stmt.executeQuery("SELECT name FROM game WHERE name LIKE '"+ super.getVaultController().getSelectedGame() +"'");

            // Loop door de resultaten
            while(rs.next()){
                // Maak een label aan met de naam van de game
                Label label = new Label(rs.getString("name"));
                // Print de naam van de game in de console (voor debugging)
                System.out.println(label.getText());
            }

        } catch (SQLException e) {
            // Gooi een RuntimeException als er een SQL-fout optreedt
            throw new RuntimeException(e);
        }
        // Geef de ScrollPane terug
        return scrollGame;
    }
}
