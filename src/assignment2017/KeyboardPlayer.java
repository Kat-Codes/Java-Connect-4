package assignment2017;

import assignment2017.codeprovided.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * Takes an input from the keyboard and checks for exceptions before calling the
 * move() method in MyGameState to drop a counter into the board
 */

public class KeyboardPlayer extends Connect4Player {

    @Override
    public void makeMove(Connect4GameState gameState) {

        @SuppressWarnings("resource")
        Scanner keyboard = new Scanner(System.in);

        boolean validInput = true;

        do {
            System.out.println("Please input number from 0 to 6");
            int col = keyboard.nextInt();
            validInput = true;

            try {
                gameState.move(col);
            } catch (IllegalColumnException e) {
                validInput = false;
                System.out.println("Column " + col + " is an invalid column number");

            } catch (ColumnFullException e) {
                validInput = false;
                System.out.println("Column " + col + " is full");

            } catch (InputMismatchException e) {
                validInput = false;
                System.out.println("Column " + col + " is an invalid column input");
            }
            // if the user inputs an incorrect input, they are given other
            // chance
        } while (!validInput);
        return;
    }

}