package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Game {
    Player firstPlayer;
    Player secondPlayer;
    private int countOfTurns = 9;
    private char winner;
    public String[] arrayForGameType;

    public int getCountOfTurns() {
        return countOfTurns;
    }

    public void setCountOfTurns(int countOfTurns) {
        this.countOfTurns = countOfTurns;
    }

    public char getWinner() {
        return winner;
    }

    public void selectGameType() {
        Scanner scanner = new Scanner(System.in);
        final String START_EXIT = "start exit";
        final String POSSIBLE_OPTIONS = "user easy medium hard";
        String str;
        do {
            System.out.print("Input command: > ");
            str = scanner.nextLine();
            arrayForGameType = str.split(" ");
            if (arrayForGameType.length == 1 && arrayForGameType[0].equals("exit"))
                return;
            if (arrayForGameType.length != 3 || !START_EXIT.contains(arrayForGameType[0]) ||
                    !POSSIBLE_OPTIONS.contains(arrayForGameType[1]) ||
                    !POSSIBLE_OPTIONS.contains(arrayForGameType[2])) {
                System.out.println("Bad parameters!");
            } else break;

        } while (true);

        if (arrayForGameType[1].equals("user")) {
            firstPlayer = new HumanPlayer('X');
        } else {
            firstPlayer = new AI('X',AI.GameMode.valueOf(arrayForGameType[1].toUpperCase(Locale.ROOT)));
        }
        if (arrayForGameType.length == 3 && arrayForGameType[2].equals("user")) {
            secondPlayer = new HumanPlayer('O');
        } else {
            secondPlayer = new AI('O', AI.GameMode.valueOf(arrayForGameType[2].toUpperCase(Locale.ROOT)));
        }
    }

    public char gameState(GameTable gameTable, Player player) {

        for (int i = 0; i < gameTable.getCoordinates(); i++) {
            if (gameTable.getArray(player.getChosenX(), i) != player.getCurrentSymbol())
                break;
            if (i == gameTable.getCoordinates() - 1) {
                winner = player.getCurrentSymbol();
                return winner;
            }
        }
        for (int i = 0; i < gameTable.getCoordinates(); i++) {
            if (gameTable.getArray(i, player.getChosenY()) != player.getCurrentSymbol())
                break;
            if (i == gameTable.getCoordinates() - 1) {
                winner = player.getCurrentSymbol();
                return winner;
            }
        }
        if (player.getChosenX() == player.getChosenY()) {
            for (int i = 0; i < gameTable.getCoordinates(); i++) {
                if (gameTable.getArray(i, i) != player.getCurrentSymbol()) {
                    break;
                }
                if (i == gameTable.getCoordinates() - 1) {
                    winner = player.getCurrentSymbol();
                    return winner;
                }
            }
        }
        if (player.getChosenX() + player.getChosenY() == gameTable.getCoordinates() - 1) {
            for (int i = 0; i < gameTable.getCoordinates(); i++) {
                if (gameTable.getArray(i, (gameTable.getCoordinates() - i) - 1) != player.getCurrentSymbol()) {
                    break;
                }
                if (i == gameTable.getCoordinates() - 1) {
                    winner = player.getCurrentSymbol();
                    return winner;
                }
            }
        }

        if (countOfTurns == 0) {
            winner = 'D';
            return winner;
        }
        return winner = ' ';
    }

    public boolean checkWinner(GameTable gameTable, Player player) {
        gameState(gameTable, player);
        if (winner == 'X' || winner == 'O') {
            System.out.println(winner + " wins");
            return true;
        }
        if (winner == 'D') {
            System.out.println("Draw");
            return true;
        } else return false;
    }
}

