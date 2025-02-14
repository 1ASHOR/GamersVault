package org.example.gamersvault.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Profile {

    private Stage profileStage;
    private GridPane rootGrid;

    public Profile(){
        rootGrid = new GridPane();
        Scene scene = new Scene(rootGrid, 400, 500);

        inputElements();

        profileStage = new Stage();
        profileStage.setScene(scene);
        profileStage.show();
    }

    private void inputElements(){
        Label gamertagLabel = new Label("Enter gamertag");
        TextField gamertagTextField = new TextField();
        Label discordLabel = new Label("Enter Discord ID");
        TextField discordTextField = new TextField();
        Button saveButton = new Button("Save");


        //add children to parent
        rootGrid.add(gamertagLabel, 0, 0);
        rootGrid.add(gamertagTextField, 1, 0);
        rootGrid.add(discordLabel, 0, 1);
        rootGrid.add(discordTextField, 1, 1);
        rootGrid.add(saveButton, 0, 2);

        //functionlity
        saveButton.setOnAction(e -> {
            System.out.println("Profile updated!");
            profileStage.close();
        });
    }
}
