package com.example.game.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.game.EqualsSearchStrategy;
import com.example.game.Game;
import com.example.game.PartialSearchStrategy;
import com.example.game.RemoveGameCommand;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Name_field;

    @FXML
    private TextField Make_field;

    @FXML
    private TextField Price_field;

    @FXML
    private Button addButton;

    @FXML
    private TableView<Game> Table;

    @FXML
    private TableColumn<Game, String> NameColumn;

    @FXML
    private TableColumn<Game, String> makeColumn;

    @FXML
    private TableColumn<Game, Integer> priceColumn;

    @FXML
    private Button exitButton;
    @FXML
    private TextField Fiend_field;

    @FXML
    private Button fiendButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Button fiendButton2;

    @FXML
    private Button print;


    @FXML
    void initialize() {
        errorLabel.setTextFill(Color.RED);

        NameColumn.setCellValueFactory(new PropertyValueFactory<>("nameGame"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("typeGame"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ArrayList<Game> games = Game.deserialize();
        Table.getItems().addAll(games);
        addButton.setOnAction(event->{
            String name = Name_field.getText();
            String type = Make_field.getText();
            String price= Price_field.getText(); // Значение цены по умолчанию

            if (!Name_field.getText().isEmpty() && !Make_field.getText().isEmpty()&&!Price_field.getText().isEmpty()) {
                try {
                    int priceValue = Integer.parseInt(price);
                    if (priceValue > 0) {
                        Game game = new Game(name, type, priceValue);
                        errorLabel.setText("Заказ добавлен");
                        Game.serialize(game);
                        Name_field.clear();
                        Make_field.clear();
                        Price_field.clear();
                        errorLabel.setText("");
                        Table.getItems().clear();
                        ArrayList<Game> orders1 = Game.deserialize();
                        Table.getItems().addAll(orders1);
                    } else {
                        errorLabel.setText("Цена должна быть больше 0");
                    }
                } catch (NumberFormatException e) {
                    errorLabel.setText("Цена должна быть целым числом");
                }
            } else {
                errorLabel.setText("Заполните все поля");
            }
        });
        exitButton.setOnAction(event->{
            LoginController g=new LoginController();
            g.openNewScene(exitButton, "/com/example/game/hello-view.fxml");
        });
        Table.setRowFactory(tv -> {
            TableRow<Game> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Game selectedOrder = row.getItem();
                    RemoveGameCommand command = new RemoveGameCommand(selectedOrder);// Удаление записи из таблицы
                    command.execute();
//                    Table.getItems().remove(selectedOrder);
                    Table.getItems().clear();
                    ArrayList<Game> orders1 = Game.deserialize();
                    Table.getItems().addAll(orders1);
                    errorLabel.setText("Запись удалена");
                }
            });
            return row;
        });
        fiendButton.setOnAction(event -> {
            String name = Fiend_field.getText();

            // Очистка таблицы от предыдущих записей
            Table.getItems().clear();
            Fiend_field.clear();
            EqualsSearchStrategy equalsSearchStrategy=new EqualsSearchStrategy();
            ArrayList<Game> game = Game.findGameByName(name,equalsSearchStrategy);
            Table.getItems().addAll(game);
        });
        fiendButton2.setOnAction(event -> {
            String name = Fiend_field.getText();

            // Очистка таблицы от предыдущих записей
            Table.getItems().clear();
            Fiend_field.clear();
            PartialSearchStrategy partialSearchStrategy=new PartialSearchStrategy();
            ArrayList<Game> game = Game.findGameByName(name,partialSearchStrategy);
            Table.getItems().addAll(game);
        });
        print.setOnAction(event->{
            Table.getItems().clear();
            ArrayList<Game> orders1 = Game.deserialize();
            Table.getItems().addAll(orders1);
        });
    }
}
