package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;
import org.example.gamersvault.database.UserController;
import org.example.gamersvault.database.VaultController;

public class VaultScreen {

    private Stage vaultStage;
    private Pane rootPane;
    private VBox vBox;
    private HBox hBox;
    private FlowPane flowPane;
    private Button accountButton;
    private Database database;
    private UserController userController;
    private Button addGameButton;
    private VaultController vc;

    public VaultScreen(Stage stage) {
        vaultStage = stage;
        System.out.println("HomeScreen initiated");

        rootPane = new Pane();
        Scene scene = new Scene(rootPane, 1200, 600);
        database = new Database();
        userController = new UserController(database);

        //Call methods to add content sections
        addVBOX();
        addHBOX();
        addFlowPane();

        updateUser();
        addGame();

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

    private void addHBOX(){
        //create variables
        hBox = new HBox();
        accountButton = new Button("Edit Profile");
        addGameButton = new Button("Add Game to Vault");

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
        hBox.getChildren().addAll(accountButton, addGameButton);
        rootPane.getChildren().add(hBox);
    }

    private void addFlowPane () {
        //create variables
        flowPane = new FlowPane();
        vc = new VaultController();

        //set sizes
        flowPane.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() - hBox.getPrefHeight());

        //set layout
        flowPane.setLayoutX(vBox.getPrefWidth());
        flowPane.setLayoutY(hBox.getPrefHeight());


        //add styling
//        flowPane.setPrefWrapLength(rootPane.getWidth() - vBox.getPrefWidth());
        flowPane.setPadding(new Insets(30));

        //add child to parent
        flowPane.getChildren().add(vc.showVault(flowPane.getPrefWidth()));
        rootPane.getChildren().add(flowPane);
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

}
