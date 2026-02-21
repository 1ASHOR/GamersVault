package org.example.gamersvault.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDetailScreen extends VaultScreen {

    private Button returnToVault;
    private Database database;
    private BorderPane borderPane;

    public GameDetailScreen(Stage stage) {
        super(stage);

        returnToVault(stage);
    }

    @Override
    protected void addScrollPane(){
        borderPane = new BorderPane();
        borderPane.getChildren().add(viewGame());
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
        scrollGame.setPrefSize(super.getScrollPaneWidth(), super.getScrollPaneHeight());
        try {
            Statement stmt = database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM game WHERE name LIKE '"+ super.getVaultController().getSelectedGame() +"'");

            while(rs.next()){
                Label label = new Label(rs.getString("name"));
                System.out.println(label.getText());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scrollGame;
    }
}
