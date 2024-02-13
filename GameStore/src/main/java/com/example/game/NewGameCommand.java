package com.example.game;

import java.util.ArrayList;

class NewGameCommand implements Command {
    private Game newGame;

    NewGameCommand(Game newGame) {
        this.newGame = newGame;
    }

    @Override
    public void execute() {
        ArrayList<Game> games = Game.deserialize();
        games.add(newGame);
        for (Game i : games) {
            Game.serialize(i);
        }

    }

}
