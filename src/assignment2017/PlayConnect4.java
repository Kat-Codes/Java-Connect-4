package assignment2017;

import java.util.Scanner;

import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

/**
 * 
 * Creates all objects required for the game to start
 * Calls the play() method to start the game
 */

public class PlayConnect4 {
	
	static Connect4GameState gameState = new MyGameState();
	static Connect4Player red = new RandomPlayer();
	static Connect4Player yellow = new KeyboardPlayer();
	static Connect4GUIDisplay display = new Connect4GUIDisplay();
	//Connect4ConsoleDisplay display = new Connect4ConsoleDisplay();
	static Connect4 game = new Connect4(gameState, red, yellow, display);
	
		public static void main(String[] args) {	
			
			game.play(gameState, red, yellow, display);	
		}
		
}
