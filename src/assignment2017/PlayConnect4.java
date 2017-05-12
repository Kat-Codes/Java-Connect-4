package assignment2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

/**
 * 
 * Creates all objects required for the game to start Calls the play() method to
 * start the game as a console or GUI game depending on whether the user inputs
 * -gui or - nogui command
 */

@SuppressWarnings("serial")
public class PlayConnect4 extends JFrame {

    private static String player;
    private static boolean buttonPressed = false;

    public static void main(String[] args) {

        Connect4GameState gameState = new MyGameState();
        Connect4Player redIntel = new IntelligentPlayer();
        Connect4Player redRand = new RandomPlayer();
        // static Connect4Player guiYellow = new GUIPlayer();
        Connect4Player yellow = new KeyboardPlayer();
        Connect4GuiDisplay guiDisplay = new Connect4GuiDisplay();
        Connect4ConsoleDisplay display = new Connect4ConsoleDisplay();
        Connect4 game = new Connect4(gameState, redRand, yellow, display);
        Connect4 guiGameIntel = new Connect4(gameState, redIntel, yellow, guiDisplay);
        Connect4 guiGameRand = new Connect4(gameState, redRand, yellow, guiDisplay);

        
         if (args[0].equals("-nogui")) { 
             game.play(gameState, redRand, yellow, display);
          }
          
          else if (args[0].equals("-gui")) {
          
              if (player== "rand") { 
                  guiGameRand.play(gameState, redRand, yellow, guiDisplay); }
          }
          else { 
              System.out.print("Incorrect input"); 
              
              }
         
          new PlayConnect4().guiStartupDisplay();

          if (player == "intel") {
            guiGameIntel.play(gameState, redIntel, yellow, guiDisplay);
          
          } else {
            guiGameRand.play(gameState, redRand, yellow, guiDisplay);
        }
    }

    public void guiStartupDisplay() {

        JFrame frame = new JFrame();
        JLabel label = new JLabel("Please select an opponent: ");
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.setSize(350, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 0));

        buttonPanel.add(new JButton(new AbstractAction("Hard (intelligent)") {
            public void actionPerformed(ActionEvent e) {
                player = "intel";
                buttonPressed = true;
            }
        }));

        buttonPanel.add(new JButton(new AbstractAction("Easy (random)") {
            public void actionPerformed(ActionEvent e) {
                player = "easy";
                buttonPressed = true;
            }
        }));

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(label, BorderLayout.CENTER);
        frame.add(contentPane);
        frame.setVisible(true);

        while (!buttonPressed) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("e");
            }
        }
        frame.dispose();
        return;
    }

}
