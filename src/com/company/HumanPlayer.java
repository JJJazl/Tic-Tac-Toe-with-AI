package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    HumanPlayer(char currentSymbol) {
        super(currentSymbol);
    }
    @Override
    public void move(GameTable gameTable, Game game, Player player) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter the coordinates: > ");
                chosenX = scanner.nextInt() - 1;
                chosenY = scanner.nextInt() - 1;
                if (gameTable.getArray(chosenX, chosenY) != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Coordinates should be from 1 to 3!");
            } catch (InputMismatchException ex) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
        putASymbol(gameTable, game);
    }
}
