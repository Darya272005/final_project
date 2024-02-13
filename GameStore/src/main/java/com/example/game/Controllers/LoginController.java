package com.example.game.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.game.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private Button authSinInButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;
    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
        errorLabel.setTextFill(Color.RED);
        loginSignUpButton.setOnAction(event ->{
            openNewScene(loginSignUpButton, "/com/example/game/registration.fxml");
        } );

        authSinInButton.setOnAction(event->{
            String loginText=login_field.getText().trim();
            String loginPassword=password_field.getText().trim();
            if(!loginText.equals("")&&!loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            }
            else{
                errorLabel.setText("Заполните все поля");
            }
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        User user = User.getInstance();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        User i=User.getUser(user);
        if(i!=null){
            openNewScene(authSinInButton, "/com/example/game/menu.fxml");
        }
        else{
            errorLabel.setText("Поля заполнены неправильно");
        }

    }
    public void openNewScene(Button button, String window) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(window));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Для закрытия окна, на котором была нажата кнопка
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
