package org.example.gamersvault.database;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    // Database-object voor communicatie met de database
    private Database database;

    // Constructor die de databaseverbinding ontvangt
    public UserController(Database db) {
        this.database = db;
    }

    // Methode om het gebruikersprofiel bij te werken in de database
    public void updateProfile(String gamertag, String discordName){
        try {
            // Maak een SQL-statement aan
            Statement stmt = database.getConnection().createStatement();
            // Voer UPDATE-query uit om gamertag en Discord-naam te wijzigen
            stmt.execute("UPDATE user SET gamertag = '"+ gamertag+ "', discord_name = '"+ discordName+"'");
        } catch (SQLException e) {
            // Gooi RuntimeException bij een SQL-fout
            throw new RuntimeException(e);
        }
    }

    // Methode die gebruikersinformatie ophaalt en teruggeeft als VBox
    public VBox getUserInfo(){
        // Maak een VBox aan voor het tonen van gebruikersgegevens
        VBox vb = new VBox();

        try {
            // Maak SQL-statement aan
            Statement stmt = database.getConnection().createStatement();
            // Haal alle gegevens op uit de user-tabel
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            // Loop door de resultaten
            while (rs.next()) {
                // Voeg labels toe met gamertag en Discord-naam
                vb.getChildren().addAll(new Label(rs.getString("gamertag")), new Label(rs.getString("discord_name")));
            }

            // Styling van de VBox
            vb.setStyle("-fx-font-size: 20px");
            vb.setSpacing(20);
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(20, 0, 0, 0));

        } catch (SQLException e) {
            // Gooi RuntimeException bij een SQL-fout
            throw new RuntimeException(e);
        }
        // Geef de VBox met gebruikersinformatie terug
        return vb;
    }

}