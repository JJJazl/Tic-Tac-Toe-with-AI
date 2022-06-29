package com.company;

abstract class Player {

    protected int chosenX;
    protected int chosenY;
    protected char currentSymbol;

    Player(char currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    public int getChosenX() {
        return chosenX;
    }

    public int getChosenY() {
        return chosenY;
    }

    public char getCurrentSymbol() {return currentSymbol; }

    protected void putASymbol(GameTable gameTable, Game game) {
        gameTable.setArray(chosenX, chosenY, currentSymbol);
        game.setCountOfTurns(game.getCountOfTurns() - 1);
    }
    protected abstract void move(GameTable gameTable, Game game, Player player);
}
