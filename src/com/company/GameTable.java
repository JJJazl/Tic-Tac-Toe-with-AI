package com.company;

public class GameTable {

    private final int coordinates = 3;
    private final char[][] array = new char[coordinates][coordinates];

    public int getCoordinates() {
        return coordinates;
    }

    public char getArray(int chosenX, int chosenY) {

        return array[chosenX][chosenY];
    }

    public void setArray(int chosenX, int chosenY, char symbol) {
        this.array[chosenX][chosenY] = symbol;
    }

    public void fillArray() {

        for (int i = 0; i < coordinates; i++) {
            for (int j = 0; j < coordinates; j++) {
                array[i][j] = ' ';
            }
        }
    }

    public boolean emptyIndexes() {
        for (int i = 0; i < coordinates; i++) {
            for (int j = 0; j < coordinates; j++) {
                if (array[i][j] == ' ')
                    return true;
            }
        }
        return false;
    }

    public void printGameTable() {
        System.out.println("---------");
        System.out.println("| " + array[0][0] + " " + array[0][1] + " " + array[0][2] + " |\n" +
                "| " + array[1][0] + " " + array[1][1] + " " + array[1][2] + " |\n" +
                "| " + array[2][0] + " " + array[2][1] + " " + array[2][2] + " |");
        System.out.println("---------");
    }
}
