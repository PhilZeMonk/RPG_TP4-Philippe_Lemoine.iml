module com.example.rpg_philippelemoine2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rpg_philippelemoine2 to javafx.fxml;
    exports com.example.rpg_philippelemoine2;
}