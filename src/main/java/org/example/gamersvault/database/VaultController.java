package org.example.gamersvault.database;

import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VaultController {

    private Database database;

    public VaultController() {
        Database db = new Database();
        this.database = db;
    }

    public ComboBox getGenres(){
        ComboBox genres = new ComboBox();

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
        ComboBox platforms = new ComboBox();

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
        ComboBox devs = new ComboBox();

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


}