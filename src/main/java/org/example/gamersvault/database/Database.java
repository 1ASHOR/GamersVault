package org.example.gamersvault.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection conn;

    public Database() {

        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers_vault", "root", "");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
