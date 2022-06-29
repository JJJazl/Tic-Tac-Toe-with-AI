package com.company;

public class Menu {
    public void chooseMenu() {
        GameTable gameTable = new GameTable();
        do {
            Game game = new Game();
            game.selectGameType();
            if ("exit".equals(game.arrayForGameType[0]))
                break;
            gameTable.fillArray();
            gameTable.printGameTable();
            while (!game.checkWinner(gameTable, game.secondPlayer)) {
                game.firstPlayer.move(gameTable, game, game.firstPlayer);
                gameTable.printGameTable();
                if (!game.checkWinner(gameTable, game.firstPlayer)) {
                    game.secondPlayer.move(gameTable, game, game.secondPlayer);
                    gameTable.printGameTable();
                } else break;
            }
        } while (true);

    }
}
