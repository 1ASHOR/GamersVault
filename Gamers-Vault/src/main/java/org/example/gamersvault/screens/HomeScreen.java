package org.example.gamersvault.screens;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeScreen {



    public HomeScreen(Stage homeStage) {
        System.out.println("HomeScreen initiated");

        Pane rootPane = new Pane();
        Scene scene = new Scene(rootPane, 1500, 1200);

        homeStage.setScene(scene);
    }



}
