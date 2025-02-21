package org.example.gamersvault.screens;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameDetailScreen extends VaultScreen {

    private Button returnToVault;

    public GameDetailScreen(Stage stage) {
        super(stage);
        addScrollPane();
        returnToVault(stage);
    }

    @Override
    protected void addScrollPane(){
        
    }

    @Override
    protected void addHBox(){
        super.addHBox();
        returnToVault = new Button("Return to Vault");
        super.getHBox().getChildren().add(returnToVault);
    }


    private void returnToVault(Stage stage) {
        returnToVault.setOnAction(e -> {
            VaultScreen vaultScreen = new VaultScreen(stage);
            stage.close();
        });
    }
}
