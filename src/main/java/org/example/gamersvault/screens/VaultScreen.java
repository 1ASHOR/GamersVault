package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;
import org.example.gamersvault.database.UserController;
import org.example.gamersvault.database.VaultController;

import java.sql.SQLException;
import java.sql.Statement;

public class VaultScreen {

    private Stage vaultStage;
    private Pane rootPane;
    private VBox vBox;
    private HBox hBox;
    private ScrollPane scrollPane;
    private Button accountButton;
    private Database database;
    private UserController userController;
    private Button addGameButton;
    private Button viewGameButton;
    private VaultController vc;
    private Button deleteGameButton;

    public VaultScreen(Stage stage) {
        vaultStage = stage;
        vc = new VaultController();

        rootPane = new Pane();
        Scene scene = new Scene(rootPane, 1200, 600);
        database = new Database();
        userController = new UserController(database);

        //Call methods to add content sections
        addVBOX();
        addHBox();
        addScrollPane();

        updateUser();
        addGame();
        viewGame();
        deleteGame();


        vaultStage.setScene(scene);
    }

    public Stage getVaultStage() {
        return vaultStage;
    }

    private void addVBOX () {
        //create variables
        vBox = new VBox();

        //set sizes
        vBox.setPrefSize(rootPane.getWidth() / 6, rootPane.getHeight());

        //alignment
        vBox.setAlignment(Pos.TOP_CENTER);

        //add styling
        vBox.setStyle("-fx-border-color: black");

        //add child to parent
        vBox.getChildren().add(userController.getUserInfo());
        rootPane.getChildren().add(vBox);
    }

    protected void addHBox(){
        //create variables
        hBox = new HBox();
        accountButton = new Button("Edit Profile");
        addGameButton = new Button("Add Game to Vault");
        viewGameButton = new Button("View selected game");
        deleteGameButton = new Button("Delete selected game");

        //set sizes
        hBox.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() / 6);
        //scale button with content size
        accountButton.setMinWidth(Region.USE_COMPUTED_SIZE);
        addGameButton.setMinWidth(Region.USE_COMPUTED_SIZE);
        //set layout
        hBox.setLayoutX(vBox.getPrefWidth());
        hBox.setAlignment(Pos.CENTER_RIGHT);


        //add styling
        hBox.setStyle("-fx-border-color: black");
        hBox.setPadding(new Insets(0, 25, 0, 0));
        hBox.setSpacing(20);

        //add child to parent
        hBox.getChildren().addAll(accountButton, addGameButton, viewGameButton, deleteGameButton, vc.getGameList());
        rootPane.getChildren().add(hBox);
    }

    protected void addScrollPane() {
        //create variables
        scrollPane = new ScrollPane();

        //set sizes
        scrollPane.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() - hBox.getPrefHeight());

        //set layout
        scrollPane.setLayoutX(vBox.getPrefWidth());
        scrollPane.setLayoutY(hBox.getPrefHeight());


        //add styling
        scrollPane.setPadding(new Insets(30));

        //add child to parent
        scrollPane.setContent(vc.showVault(scrollPane.getPrefWidth()));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rootPane.getChildren().add(scrollPane);
    }

    private void updateUser() {
        //functionality button
        accountButton.setOnAction(e -> {
            UserScreen profile = new UserScreen();
            vaultStage.close();
        });
    }

    private void addGame(){
        addGameButton.setOnAction(e -> {
            AddGameScreen game = new AddGameScreen();
            vaultStage.close();
        });
    }

    private void viewGame() {
        viewGameButton.setOnAction(e -> {
            GameDetailScreen gameDetail = new GameDetailScreen(vaultStage);
        });
    }

    protected HBox getHBox() {
        return hBox;
    }

   protected ScrollPane getScrollPane() {
        return scrollPane;
   }

   protected VaultController getVaultController() {
        return vc;
   }

    private void deleteGame() {
        deleteGameButton.setOnAction(e -> {
            if(vc.getSelectedGame() != null){
                try {
                    Statement stmt = database.getConnection().createStatement();
                    stmt.execute("DELETE FROM game WHERE name LIKE '"+ vc.getSelectedGame() +"'");
                    System.out.println("game deleted");
                    addScrollPane();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                System.out.println("no game selected");
            }
        });
    }
}
















