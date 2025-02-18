package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddGameScreen {

    private Stage addGameStage;
    private GridPane rootGrid;
    private TextField name;
    private TextArea description;
    private TextField hours;
    private TextField progress;
    private TextArea opinion;
    private ComboBox genre;
    private ComboBox platform;
    private ComboBox developer;
    private Button saveGame;

    public AddGameScreen() {
        rootGrid = new GridPane();
        Scene scene = new Scene(rootGrid, 800, 500);

        addElements();
        addStyling();

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
        genre = new ComboBox();
        Label givePlatform = new Label("Platform");
        platform = new ComboBox();
        Label giveDeveloper = new Label("Developer");
        developer = new ComboBox();
        saveGame = new Button("Add game to vault");

        //add to grid
        rootGrid.add(giveName, 0, 0, 1, 1);
        rootGrid.add(name, 1, 0, 1, 1);
        rootGrid.add(givePlaytime, 0, 1, 1, 1);
        rootGrid.add(hours, 1, 1, 1, 1);
        rootGrid.add(giveProgress, 0, 2, 1, 1);
        rootGrid.add(progress, 1, 2, 1, 1);
        rootGrid.add(giveGenre, 0, 3, 1, 1);
        rootGrid.add(genre, 1, 3, 1, 1);
        rootGrid.add(givePlatform, 0, 4, 1, 1);
        rootGrid.add(platform, 1, 4, 1, 1);
        rootGrid.add(giveDeveloper, 0, 5, 1, 1);
        rootGrid.add(developer, 1, 5, 1, 1);
        rootGrid.add(saveGame, 2, 6, 1, 1);

        rootGrid.add(description, 2, 0, 2, 2);
        rootGrid.add(opinion, 2, 2, 2, 2);
    }

    private void addStyling(){
        rootGrid.setHgap(20);
        rootGrid.setVgap(20);
        rootGrid.setPadding(new Insets(20));

        description.setPromptText("Give game description...");
        opinion.setPromptText("Give opinion...");
    }

}
