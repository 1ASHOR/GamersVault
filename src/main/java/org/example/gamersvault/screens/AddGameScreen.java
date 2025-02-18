package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private GridPane leftGrid;
    private GridPane rightGrid;

    public AddGameScreen() {
        rootGrid = new GridPane();
        Scene scene = new Scene(rootGrid, 1000, 500);

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
        hours = new TextField();
        progress = new TextField();
        opinion = new TextArea();
        genre = new ComboBox();
        platform = new ComboBox();
        developer = new ComboBox();

        leftGrid = new GridPane();
        rightGrid = new GridPane();

        //add to grid
        leftGrid.add(giveName, 0, 0);
        leftGrid.add(name, 1, 0);
        leftGrid.add(hours, 0, 1);
        leftGrid.add(progress, 0, 2);
        leftGrid.add(genre, 0, 3);
        leftGrid.add(platform, 0, 4);
        leftGrid.add(developer, 0, 5);

        rightGrid.add(description, 0, 0);
        rightGrid.add(opinion, 0, 1);

        rootGrid.add(leftGrid, 0, 0);
        rootGrid.add(rightGrid, 1, 0);
    }

    private void addStyling(){
        leftGrid.setPadding(new Insets(10));
        rightGrid.setPadding(new Insets(10));

        name.setPrefWidth(rootGrid.getWidth()/4);
    }

}
