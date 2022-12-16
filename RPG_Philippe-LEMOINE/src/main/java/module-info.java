module com.example.rpg_philippelemoine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rpg_philippelemoine to javafx.fxml;
    exports com.example.rpg_philippelemoine;
}