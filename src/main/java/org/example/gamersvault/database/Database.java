package org.example.gamersvault.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection conn;

    public Database() {

        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers_vault", "root", "");
        } catch (SQLException e) {
            System.out.println("no connection");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

}
