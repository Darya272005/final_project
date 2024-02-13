package com.example.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game implements Serializable {
    private String nameGame;
    private String typeGame;
    private int price;

    public Game(String nameGame, String typeGame, int price) {
        this.nameGame = nameGame;
        this.typeGame = typeGame;
        this.price = price;
    }


    public int getPrice() {
        return price;
    }

    public String getTypeGame() {
        return typeGame;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTypeGame(String typeGame) {
        this.typeGame = typeGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public static void executeCommand(Command command) {
        command.execute();
    }

    public static ArrayList<Game> findGameByName(String name, SearchStrategy strategy) {
        ArrayList<Game> games = deserialize();
        ArrayList<Game> gameArrayList = new ArrayList<>();

        for (Game game : games) {
            if (strategy.isMatch(game, name)) {
                gameArrayList.add(game);
            }
        }

        return gameArrayList;
    }

    public static void removeGame(Game game) {
        ArrayList<Game> orders1 = deserialize();
        orders1= (ArrayList<Game>) orders1.stream().filter(ord -> !Objects.equals(ord.getNameGame(), game.getNameGame())).collect(Collectors.toList());
        serializeAndWrite(orders1);
    }

    public static int serialize(Game game) {
        File file = new File("game.txt");
        try (FileOutputStream fos = new FileOutputStream(file, true);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter writer = new BufferedWriter(osw)) {
            writer.write(game.getNameGame() + "," + game.getTypeGame() + "," + game.getPrice());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка при сериализации: " + e.getMessage());
        }
        return 0;
    }


    public static ArrayList<Game> deserialize() {
        File file = new File("game.txt");
        ArrayList<Game> arrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String name = userData[0];
                String type = userData[1];
                int price = Integer.parseInt(userData[2]);
                Game game = new Game(name, type, price);
                arrayList.add(game);
            }
        } catch (IOException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
        return arrayList;
    }

    public static void serializeAndWrite(ArrayList<Game> games) {
        File file = new File("game.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Game game : games) {
                writer.write(game.getNameGame() + "," + game.getTypeGame() + "," + game.getPrice());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сериализации: " + e.getMessage());
        }
    }
}

