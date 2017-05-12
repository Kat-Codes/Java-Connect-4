package assignment2017;

import java.util.Random;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

/**
 * 
 * The class generates a random number to make moves for the human player to
 * play against
 */

public class RandomPlayer extends Connect4Player {

    @Override
    public void makeMove(Connect4GameState gameState) {

        Random randomNumber = new Random();
        boolean validInput;

        do {
            try {
                validInput = true;
                int randomMove = randomNumber.nextInt(6) + 0;
                gameState.move(randomMove);
                System.out.println("red made move at" + randomMove);

            } catch (IllegalColumnException e) {
                validInput = false;
                System.out.println("Column " + e + " is an invalid column number");

            } catch (ColumnFullException e) {
                validInput = false;
                System.out.println("Column " + e + " is full");
            }
        } while (!validInput);
    }

}
