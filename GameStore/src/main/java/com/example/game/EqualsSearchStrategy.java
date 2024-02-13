package com.example.game;

public class EqualsSearchStrategy implements SearchStrategy{
    @Override
    public boolean isMatch(Game game, String name) {
        return game.getNameGame().equals(name);
    }
}
