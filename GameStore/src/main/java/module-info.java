module com.example.car {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.game to javafx.fxml;
    exports com.example.game;
    exports com.example.game.Controllers;
    opens com.example.game.Controllers to javafx.fxml;
}