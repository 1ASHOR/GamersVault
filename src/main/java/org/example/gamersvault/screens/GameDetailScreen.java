package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;
import org.example.gamersvault.database.VaultController;
import org.w3c.dom.ls.LSOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDetailScreen extends VaultScreen {

    private Button returnToVault;
    private Database database;

    public GameDetailScreen(Stage stage) {
        super(stage);

        addScrollPane();
        returnToVault(stage);
    }

    @Override
    protected void addScrollPane(){
        super.addScrollPane();

        super.getScrollPane().setContent(viewGame());
    }

    @Override
    protected void addHBox(){
        super.addHBox();
        returnToVault = new Button("Return to Vault");
        super.getHBox().getChildren().add(returnToVault);

    }

    private void returnToVault(Stage stage) {
        returnToVault.setOnAction(e -> {
            VaultScreen vaultScreen = new VaultScreen(stage);
        });
    }

    private ScrollPane viewGame(){
        database = new Database();
        ScrollPane scrollGame = new ScrollPane();
        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM game WHERE name LIKE '"+ super.getVaultController().getSelectedGame() +"'");

            while(rs.next()){
                scrollGame.setContent(new Label(rs.getString("name")));
                System.out.println(rs.getString("name"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scrollGame;
    }
}
