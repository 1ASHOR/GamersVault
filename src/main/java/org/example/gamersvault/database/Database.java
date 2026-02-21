package org.example.gamersvault.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Connectie-object voor de verbinding met de database
    private Connection conn;

    public Database() {

        try {
            // Maak verbinding met de MySQL database
            // jdbc:mysql://localhost:3306/gamers_vault = database locatie
            // "root" = gebruikersnaam
            // "" = wachtwoord (leeg in dit geval)
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers_vault", "root", "");
            // Bericht in console bij succesvolle verbinding
            System.out.println("Connected to database");
        } catch (SQLException e) {
            // Bericht in console als verbinding mislukt
            System.out.println("no connection");
            // Gooi een RuntimeException bij fout
            throw new RuntimeException(e);
        }
    }

    // Geef de actieve databaseverbinding terug
    public Connection getConnection() {
        return conn;
    }

}
