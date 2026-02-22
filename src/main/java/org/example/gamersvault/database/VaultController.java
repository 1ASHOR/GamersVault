package org.example.gamersvault.database;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VaultController {

    // Database-object voor communicatie met de database
    private Database database;

    // ComboBoxen voor genres, platformen, developers en gamelijst
    private ComboBox genres;
    private ComboBox platforms;
    private ComboBox devs;
    private ComboBox gameList;

    // Constructor die databaseverbinding ontvangt
    public VaultController(Database db) {
        this.database = db;
    }

    // Methode om het genre ComboBox te vullen met waarden uit de database
    public ComboBox getGenres(){
        genres = new ComboBox();

        try {
            // Haal alle genres op uit de tabel
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM genre");

            // Voeg elke genre toe aan de ComboBox
            while (rs.next()) {
                genres.getItems().add(rs.getString("name"));
            }

            genres.setPrefWidth(200);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    // Methode om de platform ComboBox te vullen
    public ComboBox getPlatforms(){
        platforms = new ComboBox();

        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM platform");

            while (rs.next()) {
                platforms.getItems().add(rs.getString("name"));
            }

            platforms.setPrefWidth(200);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return platforms;
    }

    // Methode om de developer ComboBox te vullen
    public ComboBox getDevs(){
        devs = new ComboBox();

        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM developer");

            while (rs.next()) {
                devs.getItems().add(rs.getString("name"));
            }

            devs.setPrefWidth(200);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return devs;
    }

    // Methode om geselecteerde genre op te halen
    public String getSelectedGenre() {
        String selectedGenre = String.valueOf(genres.getValue());
        return selectedGenre;
    }

    // Methode om geselecteerd platform op te halen
    public String getSelectedPlatform() {
        String selectedPlatform = String.valueOf(platforms.getValue());
        return selectedPlatform;
    }

    // Methode om geselecteerde developer op te halen
    public String getSelectedDev() {
        String selectedDev = String.valueOf(devs.getValue());
        return selectedDev;
    }

    // Methode om een nieuwe game toe te voegen aan de database
    public void addToVault(String name, String description, double playtime, int progression, String opinion, String genre, String platform, String dev) {
//        ADD CHECK FOR FILLED IN DATA -> PLAYTIME CANNOT CONTAIN , || SET PLACEHOLDER FOR INPUT THAT IS ALLOWED TO BE NULL;
        try {
            Statement stmt = database.getConnection().createStatement();
            stmt.execute("INSERT INTO game (name, description, hours_played, progression, opinion, genre_id, platform_id, developer_id, player) " +
                    "VALUES ('"+ name +"', '"+ description +"', '"+ playtime +"', '"+ progression +"', '"+ opinion +"', " +
                    "(SELECT id FROM genre WHERE name LIKE '"+ genre +"')," +
                    "(SELECT id FROM platform WHERE name LIKE '"+ platform +"')," +
                    "(SELECT id FROM developer WHERE name LIKE '"+ dev +"')," +
                    "(SELECT gamertag FROM user)" +
                    ")");
            System.out.println("Game added into Vault!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Methode om alle games visueel weer te geven in een FlowPane
    public FlowPane showVault(double size) {
        FlowPane gamePane = new FlowPane();
        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, description FROM game");

            // Loop door alle games
            while (rs.next()) {
                // Maak een VBox per game
                VBox gameBox = new VBox();

                // Labels voor naam en beschrijving
                Label gameName = new Label(rs.getString("name"));
                Label gameDescription = new Label(rs.getString("description"));
                gameBox.getChildren().addAll(gameName, gameDescription);

                // Styling van gameBox
                gameBox.setPrefSize(300, 150);
                gameBox.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: lightgray;");
                gameBox.setAlignment(Pos.CENTER);
                gameBox.setSpacing(20);

                // Tekst automatisch laten wrappen
                gameName.setWrapText(true);
                gameDescription.setWrapText(true);
                gameDescription.setStyle("-fx-font-size: 11; -fx-font-weight: normal;");

                // Voeg gameBox toe aan FlowPane
                gamePane.getChildren().add(gameBox);
            }

            // Styling van FlowPane
            gamePane.setPrefWrapLength(size);
            gamePane.setHgap(20);
            gamePane.setVgap(20);
            gamePane.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gamePane;
    }

    // Methode om ComboBox te vullen met alle game namen
    public ComboBox getGameList() {
        gameList = new ComboBox();
        gameList.setPrefWidth(200);
        gameList.setPromptText("Select Game");

        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select name From game");

            while (rs.next()) {
                gameList.getItems().add(rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gameList;
    }

    // Methode om geselecteerde game op te halen
    public String getSelectedGame() {
        String selectedGame = null;

        // Controleer of er een selectie is gemaakt
        if(gameList.getValue() != null) {
            selectedGame = String.valueOf(gameList.getSelectionModel().getSelectedItem());
        }
        return selectedGame;
    }

}