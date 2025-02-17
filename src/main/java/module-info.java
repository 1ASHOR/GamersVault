module org.example.gamersvault {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.gamersvault to javafx.fxml;
    exports org.example.gamersvault;
}