package org.example.gamersvault.screens;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen {

    private Pane rootPane;
    private VBox vBox;
    private HBox hBox;
    private FlowPane flowPane;

    public HomeScreen(Stage homeStage) {
        System.out.println("HomeScreen initiated");

        rootPane = new Pane();
        Scene scene = new Scene(rootPane, 2000, 1000);

        //Call methods to add content sections
        addVBOX();
        addHBOX();
        addFlowPane();

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

        //set sizes
        hBox.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() / 6);

        //set layout
        hBox.setLayoutX(vBox.getPrefWidth());

        //add styling
        hBox.setStyle("-fx-border-color: black");

        //add child to parent
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


}
