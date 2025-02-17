package org.example.gamersvault.database;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    private Database database;

    public UserController(Database db) {
        this.database = db;
    }

    public void updateProfile(String gamertag, String discordName){
        try {
            Statement stmt = database.getConnection().createStatement();
            stmt.execute("UPDATE user SET gamertag = '"+ gamertag+ "', discord_name = '"+ discordName+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getUserInfo(){
        VBox vb = new VBox();
        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                vb.getChildren().addAll(new Label(rs.getString("gamertag")), new Label(rs.getString("discord_name")));
            }

            vb.setStyle("-fx-font-size: 20px");
            vb.setSpacing(20);
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(20, 0, 0, 0));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vb;
    }








}