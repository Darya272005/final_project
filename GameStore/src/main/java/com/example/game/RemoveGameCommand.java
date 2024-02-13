package com.example.game;


public class RemoveGameCommand implements Command {
    private Game selectedGame;

    public RemoveGameCommand(Game selectedGame) {
        this.selectedGame = selectedGame;
    }

    @Override
    public void execute() { // Удаление записи из исходного списка данных
        Game.removeGame(selectedGame);
    }
}

