package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Profile {

    private Stage profileStage;
    private VBox rootVbox;
    private Button saveButton;

    public Profile(){
        rootVbox = new VBox();
        Scene scene = new Scene(rootVbox, 400, 500);

        inputElements();
        layoutStyle();
        updateProfile();

        profileStage = new Stage();
        profileStage.setScene(scene);
        profileStage.show();
    }

    private void inputElements(){
        Label gamertagLabel = new Label("Enter gamertag");
        TextField gamertagTextField = new TextField();
        Label discordLabel = new Label("Enter Discord ID");
        TextField discordTextField = new TextField();
        saveButton = new Button("Save");


        //add children to parent
        rootVbox.getChildren().addAll(gamertagLabel, gamertagTextField, discordLabel, discordTextField, saveButton);

        // add styling || NEEDS TO BE MOVED TO OTHER METHOD OR CSS FILE
        gamertagTextField.setMaxWidth(200);
        gamertagTextField.setPrefHeight(30);
        discordTextField.setMaxWidth(200);
        discordTextField.setPrefHeight(30);

        gamertagTextField.setStyle("-fx-font-size: 14px");
        discordTextField.setStyle("-fx-font-size: 14px");

        gamertagLabel.setStyle("-fx-font-size: 16px");
        discordLabel.setStyle("-fx-font-size: 16px");

        saveButton.setStyle("-fx-font-size: 16px");
        saveButton.setPrefSize(100, 30);
    }

    private void layoutStyle(){
        //positioning
        rootVbox.setAlignment(Pos.TOP_CENTER);
        rootVbox.setSpacing(15);
        rootVbox.setPadding(new Insets(20, 0, 0, 0));

        //styling
    }

    private void updateProfile(){
        //method for funcionality save button
        saveButton.setOnAction(e -> {
            System.out.println("Profile updated!");
            profileStage.close();
        });
    }
}
