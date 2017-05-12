package assignment2017;

import java.awt.Graphics;

/**
 * 
 * Interface to ensure all objects drawn
 * inherit the painComponent method
 * 
 */

public interface Shape {
    
    abstract void paintComponent(Graphics g);
}
