package assignment2017;

import assignment2017.MyGameState;
import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;

/**
 * 
 * Displays the board to the console using the board array from the MyGameState
 * class
 */

public class Connect4ConsoleDisplay implements Connect4Displayable {

    @Override
    public void displayBoard() {

        for (int row = Connect4GameState.NUM_ROWS - 1; row >= 0; row--) {
            System.out.print(" | ");
            for (int col = 0; col < Connect4GameState.NUM_COLS; col++) {

                if (MyGameState.board[col][row] == MyGameState.EMPTY) {
                    System.out.print("   ");
                } else if (MyGameState.board[col][row] == MyGameState.YELLOW) {
                    System.out.print(" Y ");
                } else {
                    System.out.print(" R ");
                }
            }
            System.out.println(" | ");
        }
        System.out.println("   ---------------------");
        System.out.println("    0  1  2  3  4  5  6");
    }

}
