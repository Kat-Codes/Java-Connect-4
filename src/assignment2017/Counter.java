package assignment2017;

import java.awt.Color;

/**
 * 
 * Creates counters that are then displayed 
 * on the GUI
 */
import java.awt.Graphics;

import javax.swing.JPanel;

import assignment2017.codeprovided.Connect4GameState;

/**
 * 
 * Draws the shapes that are then used in the Jframe to represent coloured
 * counters
 */

@SuppressWarnings("serial")
public class Counter extends JPanel implements Shape {

    protected int colour;

    public Counter(int colour) {
        this.colour = colour;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (colour == Connect4GameState.RED) {
            g.setColor(Color.YELLOW);
            
        } else if (colour == Connect4GameState.YELLOW) {
            g.setColor(Color.RED);
            
        } else {
            g.setColor(Color.WHITE);
            
        }
        g.fillOval(10, 5, 30, 30);

    }


}
