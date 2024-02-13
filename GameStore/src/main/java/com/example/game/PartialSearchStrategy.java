package com.example.game;

public class PartialSearchStrategy implements SearchStrategy{
    @Override
    public boolean isMatch(Game game, String name) {
        return game.getNameGame().contains(name);
    }
}
