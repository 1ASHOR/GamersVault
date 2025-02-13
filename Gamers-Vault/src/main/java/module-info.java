module org.example.gamersvault {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.gamersvault to javafx.fxml;
    exports org.example.gamersvault;
}