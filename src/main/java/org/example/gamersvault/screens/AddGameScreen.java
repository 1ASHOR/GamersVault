package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.gamersvault.database.Database;
import org.example.gamersvault.database.VaultController;

public class AddGameScreen {

    private Stage addGameStage;
    private GridPane rootGrid;
    private TextField name;
    private TextArea description;
    private TextField hours;
    private TextField progress;
    private TextArea opinion;
    private Button saveGameButton;
    private VaultController vc;

    public AddGameScreen() {
        rootGrid = new GridPane();
        Scene scene = new Scene(rootGrid, 800, 500);

        addElements();
        addStyling();
        saveGame();

        addGameStage = new Stage();
        addGameStage.setScene(scene);
        addGameStage.show();
    }

    private void addElements(){
        //create elements
        Label giveName = new Label("Name");
        name = new TextField();
        description = new TextArea();
        Label givePlaytime = new Label("Playtime");
        hours = new TextField();
        Label giveProgress = new Label("Progress");
        progress = new TextField();
        opinion = new TextArea();
        Label giveGenre = new Label("Genre");
        Label givePlatform = new Label("Platform");
        Label giveDeveloper = new Label("Developer");
        saveGameButton = new Button("Add game to vault");
        vc = new VaultController();

        //add to grid
        rootGrid.add(giveName, 0, 0, 1, 1);
        rootGrid.add(name, 1, 0, 1, 1);
        rootGrid.add(givePlaytime, 0, 1, 1, 1);
        rootGrid.add(hours, 1, 1, 1, 1);
        rootGrid.add(giveProgress, 0, 2, 1, 1);
        rootGrid.add(progress, 1, 2, 1, 1);
        rootGrid.add(giveGenre, 0, 3, 1, 1);
        rootGrid.add(givePlatform, 0, 4, 1, 1);
        rootGrid.add(giveDeveloper, 0, 5, 1, 1);
        rootGrid.add(saveGameButton, 2, 6, 1, 1);

        //add combobox with values from database to rootGrid
        rootGrid.add(vc.getGenres(), 1, 3, 1, 1);
        rootGrid.add(vc.getPlatforms(), 1, 4, 1, 1);
        rootGrid.add(vc.getDevs(), 1, 5, 1, 1);

        // add textarea's to rootGrid, taking double grid spacing
        rootGrid.add(description, 2, 0, 2, 2);
        rootGrid.add(opinion, 2, 2, 2, 2);
    }

    private void addStyling(){
        rootGrid.setHgap(20);
        rootGrid.setVgap(20);
        rootGrid.setPadding(new Insets(20));

        description.setPromptText("Give game description...");
        description.setWrapText(true);
        opinion.setPromptText("Give opinion...");
        opinion.setWrapText(true);
    }

    public void saveGame(){
        saveGameButton.setOnAction(e -> {
            double playtime = Double.parseDouble(hours.getText());
            int progression = Integer.parseInt(progress.getText());

            vc.addToVault(name.getText(), description.getText(), playtime, progression, opinion.getText());
        });
    }
}
