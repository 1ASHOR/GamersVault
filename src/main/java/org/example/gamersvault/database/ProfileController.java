package org.example.gamersvault.database;

import java.sql.SQLException;
import java.sql.Statement;

public class ProfileController {

    private Database database;

    public ProfileController(Database db) {
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








}