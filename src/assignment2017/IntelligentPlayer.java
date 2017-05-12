package assignment2017;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

/**
 * 
 * Provides and intelligent opponent for the user to play against. It choses
 * moves based off an interpretation of the minimax algorithm
 * 
 */

public class IntelligentPlayer extends Connect4Player {

    @Override
    public void makeMove(Connect4GameState gameState) {
        
        int move;
        
            try {
                int[][] tempBoard = ((MyGameState) gameState).copyBoard();
                int backupCounters = MyGameState.countersInBoard;
                int[] colDecision = new int[Connect4GameState.NUM_COLS];
                move = startCheckMove(gameState, tempBoard, colDecision);

                MyGameState.countersInBoard = backupCounters;
                if (!gameState.isColumnFull(move) && move < Connect4GameState.NUM_COLS) {
                        gameState.move(move);
                }
                else {
                    makeMove(gameState);
                }

            } catch (IllegalColumnException e) {
                System.out.print("col " + e);
               
            } catch (ColumnFullException e) {
                System.out.print("col " + e);
            }
    }

    // includes initialisation of variables so that they are not
    // reinitialised during recursion
    public int startCheckMove(Connect4GameState gameState, int[][] tempBoard, int[] colDecision) {

        resetDecision(colDecision);

        return checkMove(gameState, tempBoard, colDecision);
    }

    public void resetDecision(int[] colDecision) {

        for (int i = 0; i < colDecision.length; i++) {
            colDecision[i] = 0;
        }
    }

    public int checkMove(Connect4GameState gameState, int[][] tempBoard, int[] colDecision) {

        for (int i = 1; i <= 2; i++) {

            MyGameState.countersInBoard = i;

            for (int col = 0; col < Connect4GameState.NUM_COLS; col++) {
                MyGameState.board = tempBoard;
                try {
                    gameState.move(col);
                    checkPoints(gameState, col, colDecision, 400);
                    MyGameState.countersInBoard--;

                    for (int secondMoveCol = 0; secondMoveCol < Connect4GameState.NUM_COLS; secondMoveCol++) {
                        gameState.move(secondMoveCol);
                        checkPoints(gameState, col, colDecision, 200);
                        MyGameState.countersInBoard--;
                        ((MyGameState) gameState).unplayMove(secondMoveCol);

                    }
                    ((MyGameState) gameState).unplayMove(col);

                } catch (IllegalColumnException e) {
                    System.out.println("col " + e);
                } catch (ColumnFullException e) {
                    System.out.println("col " + e);
                }
            }
        }
        return maxPoints(colDecision);
    }

    // checks each column to find which will provide a win and if
    // it will, assign a high number to it
    public void checkPoints(Connect4GameState gameState, int col, int[] colDecision, int points) {

        if (gameState.getWinner() != Connect4GameState.EMPTY) {
            colDecision[col] = colDecision[col] + points;
        }
    }

    // takes each col value from CheckPoints and finds the
    // maximum
    public int maxPoints(int[] colDecision) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, Connect4GameState.NUM_COLS - 1);
        int max = colDecision[randomNum];

        // finds the max number in the cols
        for (int i = 0; i < colDecision.length; i++) {

            if (colDecision[i] > max) {

                max = colDecision[i];
            }
        }
        ArrayList<Integer> maxCols = new ArrayList<Integer>();

        // adds all high value moves to make to an ArrayList
        for (int i = 0; i < colDecision.length; i++) {

            // stores all the max col nums in an arraylist
            if (colDecision[i] == max) {
                maxCols.add(i);
            }
        }

        // picks a random high value move to make
        Random rand = new Random();
        return maxCols.get(rand.nextInt(maxCols.size()));
    }

}
