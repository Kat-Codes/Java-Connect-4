package assignment2017;

import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

/**
 * 
 * Provides the play method to call the various methods to run the game. Runs
 * until the game is over and outputs the winner if there is one and outputs no
 * winner if the board is full
 */

public class Connect4 {
    Connect4GameState gameState;
    Connect4Player red;
    Connect4Player yellow;
    Connect4GuiDisplay guiDisplay;
    Connect4ConsoleDisplay consoleDisplay;

    public Connect4(Connect4GameState gameState, Connect4Player red, Connect4Player yellow,
            Connect4GuiDisplay display) {

        this.gameState = gameState;
        this.red = red;
        this.yellow = yellow;
        this.guiDisplay = display;
    }

    public Connect4(Connect4GameState gameState, Connect4Player red, Connect4Player yellow,
            Connect4ConsoleDisplay display) {

        this.gameState = gameState;
        this.red = red;
        this.yellow = yellow;
        this.consoleDisplay = display;
    }

    // GUI display
    public void play(Connect4GameState gameState, Connect4Player red, Connect4Player yellow,
            Connect4GuiDisplay guiDisplay) {

        gameState.startGame();
        guiDisplay.createBoard(gameState);

        do {
            guiDisplay.drawBoard(gameState);
            guiDisplay.displayBoard();
            red.makeMove(gameState);
            guiDisplay.displayBoard();

        } while (!gameState.gameOver());

        guiDisplay.winner(gameState);
    }

    // console display
    public void play(Connect4GameState gameState, Connect4Player red, Connect4Player yellow,
            Connect4ConsoleDisplay display) {

        // initialises game
        gameState.startGame();
        display.displayBoard();

        // runs until game is over
        do {
            yellow.makeMove(gameState);

            // clears the console window
            for (int clear = 0; clear < 1000; clear++) {
                System.out.println("\b");
            }
            display.displayBoard();
            red.makeMove(gameState);
            display.displayBoard();
            System.out.println("Computer has placed a Y counter");

        } while (!gameState.gameOver());

        if (gameState.getWinner() == Connect4GameState.RED) {
            System.out.println("Red has won!");
        
        } else if (gameState.getWinner() == Connect4GameState.YELLOW) {
            System.out.println("Yellow has won!");
        
        } else {
            System.out.println("There is no winner!");
        }
    }
}
