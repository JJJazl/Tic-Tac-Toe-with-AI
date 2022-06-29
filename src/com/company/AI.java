package com.company;

import java.util.Random;

public class AI extends Player {

    public enum GameMode {
        EASY, MEDIUM, HARD
    }
    Random random = new Random();
    private final AI.GameMode gameMode;

    AI(char currentSymbol, AI.GameMode gameMode) {
        super(currentSymbol);
        this.gameMode = gameMode;
    }

    private void moveEasyAi(GameTable gameTable, Game game) {
        while (true) {
            chosenX = random.nextInt(3);
            chosenY = random.nextInt(3);
            if (gameTable.getArray(chosenX, chosenY) == ' ' && game.getCountOfTurns() != 0) {
                putASymbol(gameTable, game);
                break;
            }
            if (game.getCountOfTurns() == 0) {
                break;
            }
        }
    }

    private void moveMediumAi( GameTable gameTable,  Game game) {
        if (game.getCountOfTurns() == 0)
            return;
        int sizeOfArray = gameTable.getCoordinates();
        int countOfAiSymbol = 0;
        int countOfPlayerSymbol = 0;
        int freeIndex = 0;
        for (int i = 0; i < sizeOfArray; i++) {
            for (int j = 0; j < sizeOfArray; j++) {
                if (gameTable.getArray(i, j) == this.currentSymbol) {
                    countOfAiSymbol++;
                } else if (gameTable.getArray(i, j) != ' ') {
                    countOfPlayerSymbol++;
                } else freeIndex = j;
            }
            if ((countOfAiSymbol == 2 && countOfPlayerSymbol == 0) || (countOfPlayerSymbol == 2 && countOfAiSymbol == 0)) {
                chosenX = i;
                chosenY = freeIndex;
                putASymbol(gameTable, game);
                return;
            }
            countOfAiSymbol = 0;
            countOfPlayerSymbol = 0;
        }
        for (int i = 0; i < sizeOfArray; i++) {
            for (int j = 0; j < sizeOfArray; j++) {
                if (gameTable.getArray(j, i) == this.currentSymbol) {
                    countOfAiSymbol++;
                } else if (gameTable.getArray(j, i) != ' ') {
                    countOfPlayerSymbol++;
                } else freeIndex = j;
            }
            if ((countOfAiSymbol == 2 && countOfPlayerSymbol == 0) || (countOfPlayerSymbol == 2 && countOfAiSymbol == 0)) {
                chosenX = freeIndex;
                chosenY = i;
                putASymbol(gameTable, game);
                return;
            }
            countOfAiSymbol = 0;
            countOfPlayerSymbol = 0;
        }
        for (int i = 0; i < sizeOfArray; i++) {
            if (gameTable.getArray(i, i) == this.currentSymbol) {
                countOfAiSymbol++;
            } else if (gameTable.getArray(i, i) != ' ') {
                countOfPlayerSymbol++;
            } else {
                freeIndex = i;
            }
            if ((i == 2 && countOfAiSymbol == 2 && countOfPlayerSymbol == 0) || (i == 2 && countOfPlayerSymbol == 2 && countOfAiSymbol == 0)) {
                chosenX = freeIndex;
                chosenY = freeIndex;
                putASymbol(gameTable, game);
                return;
            }
        }
        countOfAiSymbol = 0;
        countOfPlayerSymbol = 0;

        for (int i = 0; i < sizeOfArray; i++) {
            for (int j = 0; j < sizeOfArray; j++) {
                if (i + j == sizeOfArray - 1) {
                    if (gameTable.getArray(i, j) == this.currentSymbol) {
                        countOfAiSymbol++;
                    } else if (gameTable.getArray(i, j) != ' ') {
                        countOfPlayerSymbol++;
                    } else {
                        chosenX = i;
                        freeIndex = j;
                    }
                }
            }
            if ((i == 2 && countOfAiSymbol == 2 && countOfPlayerSymbol == 0) || (i == 2 && countOfPlayerSymbol == 2 && countOfAiSymbol == 0)) {
                chosenY = freeIndex;
                putASymbol(gameTable, game);
                return;
            }
        }

        while (true) {
            chosenX = random.nextInt(3);
            chosenY = random.nextInt(3);
            if (gameTable.getArray(chosenX, chosenY) == ' ') {
                putASymbol(gameTable, game);
                break;
            }
        }
    }
    private int minimax( GameTable gameTable,  Game game, Player player,
                         char playerSymbol, char boxForAiSymbol, boolean isMax) {
        game.gameState(gameTable, player);
        if (game.getWinner() == boxForAiSymbol) {
            return 10;
        } else if (game.getWinner() == playerSymbol) {
            return -10;
        } else if (!gameTable.emptyIndexes()) {
            return 0;
        }

        int maxEval;
        if (isMax) {
            maxEval = -1000;
            for (int i = 0; i < gameTable.getCoordinates(); i++) {
                for (int j = 0; j < gameTable.getCoordinates(); j++) {
                    if (gameTable.getArray(i, j) == ' ') {
                        chosenX = i;
                        chosenY = j;
                        currentSymbol = boxForAiSymbol;
                        gameTable.setArray(i, j, currentSymbol);
                        int eval = minimax(gameTable, game, player, playerSymbol, boxForAiSymbol, false);
                        maxEval = Math.max(maxEval, eval);
                        gameTable.setArray(i, j, ' ');
                    }
                }
            }
            return maxEval;
        } else {
            maxEval = 1000;
            for (int i = 0; i < gameTable.getCoordinates(); i++) {
                for (int j = 0; j < gameTable.getCoordinates(); j++) {
                    if (gameTable.getArray(i, j) == ' ') {
                        chosenX = i;
                        chosenY = j;
                        currentSymbol = playerSymbol;
                        gameTable.setArray(i, j, currentSymbol);
                        int eval = minimax(gameTable, game, player, playerSymbol, boxForAiSymbol, true);
                        maxEval = Math.min(maxEval, eval);
                        gameTable.setArray(i, j, ' ');
                    }
                }
            }
            return maxEval;
        }
    }

    private void moveHardAi(GameTable gameTable, Game game, Player player) {
        char boxForAiSymbol = currentSymbol;
        char playerSymbol = currentSymbol == 'O' ? 'X' : 'O';
        int bestVal = -100;
        int boxForI = 0;
        int boxForJ = 0;
        for (int i = 0; i < gameTable.getCoordinates(); i++) {
            for (int j = 0; j < gameTable.getCoordinates(); j++) {
                if (gameTable.getArray(i, j) == ' ') {
                    gameTable.setArray(i, j, boxForAiSymbol);
                    chosenX = i;
                    chosenY = j;
                    int val = minimax(gameTable, game, player, playerSymbol, boxForAiSymbol, false);
                    gameTable.setArray(i, j, ' ');
                    if (val > bestVal) {
                        boxForI = i;
                        boxForJ = j;
                        bestVal = val;
                    }
                }
            }
        }
        chosenX = boxForI;
        chosenY = boxForJ;
        currentSymbol = boxForAiSymbol;
        putASymbol(gameTable, game);
    }

    @Override
    public void move(GameTable gameTable, Game game, Player player) {
        switch (gameMode) {
            case EASY:
                System.out.println("Making move level \"easy\"");
                moveEasyAi(gameTable, game);
                break;
            case MEDIUM:
                moveMediumAi(gameTable, game);
                break;
            case HARD:
                System.out.println("Making move level \"hard\"");
                moveHardAi(gameTable, game, player);
                break;
        }
    }
}
