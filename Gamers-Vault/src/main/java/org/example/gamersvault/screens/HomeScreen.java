package org.example.gamersvault.screens;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen {

    private VBox vBox;
    private HBox hBox;
    private FlowPane flowPane;

    public HomeScreen(Stage homeStage) {
        System.out.println("HomeScreen initiated");

        Pane rootPane = new Pane();
        Scene scene = new Scene(rootPane, 2000, 1000);

        vBox = new VBox();
        vBox.setPrefSize(rootPane.getWidth() / 6, rootPane.getHeight());
        vBox.setStyle("-fx-border-color: black");

        hBox = new HBox();
        hBox.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() / 6);
        hBox.setLayoutX(vBox.getPrefWidth());
        hBox.setStyle("-fx-border-color: black");

        flowPane = new FlowPane();
        flowPane.setPrefSize(rootPane.getWidth() - vBox.getPrefWidth(), rootPane.getHeight() - hBox.getPrefHeight());
        flowPane.setLayoutX(vBox.getPrefWidth());
        flowPane.setLayoutY(hBox.getPrefHeight());
        flowPane.setStyle("-fx-background-color: lightgray");

        rootPane.getChildren().add(vBox);
        rootPane.getChildren().add(hBox);
        rootPane.getChildren().add(flowPane);
        homeStage.setScene(scene);
    }



}
