package com.example.game.Controllers;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.game.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SinInButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField signUpName;
    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
        errorLabel.setTextFill(Color.RED);
        SinInButton.setOnAction(event -> {
            String login = login_field.getText().trim();
            String password = password_field.getText().trim();

            if (!login.isEmpty() && !password.isEmpty()) {
                User user = User.getInstance();
                user.setLogin(login);
                user.setPassword(password);
                User.signUpUser(user);
                LoginController g=new LoginController();
                g.openNewScene(SinInButton, "/com/example/game/hello-view.fxml");
            } else {
                errorLabel.setText("Заполните все поля");
            }
        });
    }
}
