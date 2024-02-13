package com.example.game;

import java.io.*;
import java.util.ArrayList;
public class User implements Serializable{
    private static User instance;
    private String password;
    private String login;

//    public User(String password, String login) {
//        this.password = password;
//        this.login = login;
//    }
    private User(String password, String login) {
        this.password = password;
        this.login = login;
    }
    public static User getInstance() {
        if (instance == null) {
            instance = new User("", "");
        }
        return instance;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static void signUpUser(User user) {
        serialize(user);
        System.out.println("Users add");
    }
    public static User getUser(User user){
        ArrayList<User> users=deserialize();
        for (User i:users){
            if(i.getLogin().equals(user.getLogin()) &&
                    i.getPassword().equals(user.getPassword())){
                return i;
            }
        }
        return null;
    }

    public static void serialize(User user) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\БГУИР\\2 курс\\ОУИС\\GameStore\\users.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(user.getPassword() + "," + user.getLogin());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка при сериализации: " + e.getMessage());
        }
    }


    public static ArrayList<User> deserialize() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\БГУИР\\2 курс\\ОУИС\\GameStore\\users.txt");
        ArrayList<User> arrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String login = userData[0];
                String password = userData[1];
                User user = new User(login, password);
                arrayList.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
        return arrayList;
    }

}