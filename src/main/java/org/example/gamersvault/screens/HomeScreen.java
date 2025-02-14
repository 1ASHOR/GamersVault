package org.example.gamersvault.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomeScreen {

    private Pane rootPane;
    private VBox vBox;
    private HBox hBox;
    private FlowPane flowPane;
    private Button accountButton;

    public HomeScreen(Stage homeStage) {
        System.out.println("HomeScreen initiated");

        rootPane = new Pane();
        Scene scene = new Scene(rootPane, 1200, 600);

        //Call methods to add content sections
        addVBOX();
        addHBOX();
        addFlowPane();

        updateProfile();
        
        homeStage.setScene(scene);
    }

    private void addVBOX () {
        //create variables
        vBox = new VBox();

        //set sizes
        vBox.setPrefSize(rootPane.getWidth() / 6, rootPane.getHeight());

        //add styling
        vBox.setStyle("-fx-border-color: black");

        //add child to parent
        rootPane.getChildren().add(vBox);
    }

    private void addHBOX(){
        //create variables
        hBox = new HBox();
        accountButton = new Button("Edit Profile");

        //set sizes
        hBox.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() / 6);
        accountButton.setMinWidth(Region.USE_COMPUTED_SIZE);
        //set layout
        hBox.setLayoutX(vBox.getPrefWidth());
        hBox.setAlignment(Pos.CENTER_RIGHT);


        //add styling
        hBox.setStyle("-fx-border-color: black");
        hBox.setPadding(new Insets(0, 25, 0, 0));

        //add child to parent
        hBox.getChildren().add(accountButton);
        rootPane.getChildren().add(hBox);
    }

    private void addFlowPane () {
        //create variables
        flowPane = new FlowPane();

        //set sizes
        flowPane.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() - hBox.getPrefHeight());

        //set layout
        flowPane.setLayoutX(vBox.getPrefWidth());
        flowPane.setLayoutY(hBox.getPrefHeight());

        //add styling
        flowPane.setStyle("-fx-background-color: lightgray");

        //add child to parent
        rootPane.getChildren().add(flowPane);
    }

    private void updateProfile() {
        //functionality button
        accountButton.setOnAction(e -> {
            Profile profile = new Profile();
            System.out.println("profile can be updated");
        });
    }

}
