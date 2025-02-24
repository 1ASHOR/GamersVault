package org.example.gamersvault.database;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.example.gamersvault.screens.VaultScreen;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VaultController {

    private Database database;
    private ComboBox genres;
    private ComboBox platforms;
    private ComboBox devs;
    private ComboBox gameList;

    public VaultController(Database db) {
        this.database = db;
    }

    // methods underneath used to fill the three comboboxes with value's from database
    public ComboBox getGenres(){
        genres = new ComboBox();

        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM genre");

            while (rs.next()) {
                genres.getItems().add(rs.getString("name"));
            }

            genres.setPrefWidth(200);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

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

    // methods underneath to get the selected value from the comboboxes
    public String getSelectedGenre() {
        String selectedGenre = String.valueOf(genres.getValue());
        return selectedGenre;
    }

    public String getSelectedPlatform() {
        String selectedPlatform = String.valueOf(platforms.getValue());
        return selectedPlatform;
    }

    public String getSelectedDev() {
        String selectedDev = String.valueOf(devs.getValue());
        return selectedDev;
    }

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

    public FlowPane showVault(double size) {
        FlowPane gamePane = new FlowPane();
        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, description FROM game");

            while (rs.next()) {
                VBox gameBox = new VBox();
                Label gameName = new Label(rs.getString("name"));
                Label gameDescription = new Label(rs.getString("description"));
                gameBox.getChildren().addAll(gameName, gameDescription);

                //styling for vbox
                gameBox.setPrefSize(300, 150);
                gameBox.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: lightgray;");
                gameBox.setAlignment(Pos.CENTER);
                gameBox.setSpacing(20);
                gameName.setWrapText(true);
                gameDescription.setWrapText(true);
                gameDescription.setStyle("-fx-font-size: 11; -fx-font-weight: normal;");

                gamePane.getChildren().add(gameBox);
            }

            // styling for flowpane
            gamePane.setPrefWrapLength(size);
            gamePane.setHgap(20);
            gamePane.setVgap(20);
            gamePane.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gamePane;
    }

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

    public String getSelectedGame() {
        String selectedGame = null;
        if(gameList.getValue() != null) {
            selectedGame = String.valueOf(gameList.getSelectionModel().getSelectedItem());
        }
        return selectedGame;
    }

}