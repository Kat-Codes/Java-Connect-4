package assignment2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import assignment2017.codeprovided.*;

/**
 * 
 * Provides GUI which the user can interact with The GUI is initially created
 * and then redrawn each time a move is made
 * 
 */

@SuppressWarnings("serial")
public class Connect4GuiDisplay extends JFrame implements Connect4Displayable {

    private int buttonPressed;
    private boolean isButtonPressed;

    JPanel buttonPanel = new JPanel();
    JPanel board = new JPanel();
    JFrame frame = new JFrame();
    Container contentPane = getContentPane();

    @Override
    public void displayBoard() {

        board.removeAll();

        for (int row = Connect4GameState.NUM_ROWS - 1; row >= 0; row--) {

            for (int col = 0; col < Connect4GameState.NUM_COLS; col++) {

                board.add(new Counter(MyGameState.board[col][row]));
            }
        }
        repaint();
        contentPane.setBackground(Color.BLUE);
        contentPane.add(board, BorderLayout.CENTER);
        contentPane.setBackground(Color.BLUE);
        frame.add(contentPane);
        frame.setVisible(true);
    }

    public void drawBoard(Connect4GameState gameState) {

        displayBoard();

        while (!getButtonPressed()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("e");
            }
        }

        try {
            gameState.move(getMove());
        } catch (IllegalColumnException e) {
            System.out.println("col" + e);

        } catch (ColumnFullException e) {
            System.out.println("col" + e);
        }
        setButtonPressed(false);
        return;
    }

    public void createBoard(Connect4GameState gameState) {

        this.isButtonPressed = false;
        frame.setTitle("Connect 4");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board.setLayout(new GridLayout(Connect4GameState.NUM_ROWS, Connect4GameState.NUM_COLS));
        buttonPanel.setLayout(new GridLayout(1, 0));
        buttonPanel.setBackground(Color.ORANGE);
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.add(new JButton(new AbstractAction("0") {
            public void actionPerformed(ActionEvent e) {
                setMove(0);
                setButtonPressed(true);
            }
        }));
        buttonPanel.add(new JButton(new AbstractAction("1") {
            public void actionPerformed(ActionEvent e) {
                setMove(1);
                setButtonPressed(true);
            }
        }));
        buttonPanel.add(new JButton(new AbstractAction("2") {
            public void actionPerformed(ActionEvent e) {
                setMove(2);
                setButtonPressed(true);
            }
        }));
        buttonPanel.add(new JButton(new AbstractAction("3") {
            public void actionPerformed(ActionEvent e) {
                setMove(3);
                setButtonPressed(true);
            }
        }));
        buttonPanel.add(new JButton(new AbstractAction("4") {
            public void actionPerformed(ActionEvent e) {
                setMove(4);
                setButtonPressed(true);
            }
        }));
        buttonPanel.add(new JButton(new AbstractAction("5") {
            public void actionPerformed(ActionEvent e) {
                setMove(5);
                setButtonPressed(true);
            }
        }));
        buttonPanel.add(new JButton(new AbstractAction("6") {
            public void actionPerformed(ActionEvent e) {
                setMove(6);
                setButtonPressed(true);
            }
        }));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    public int getMove() {
        return this.buttonPressed;
    }

    public void setMove(int b) {
        this.buttonPressed = b;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.isButtonPressed = buttonPressed;
    }

    public boolean getButtonPressed() {
        return this.isButtonPressed;
    }

    public void winner(Connect4GameState gameState) {
        buttonPanel.removeAll();
        String winner;
        
        if (gameState.getWinner() == 1) {
            winner = "RED wins!";
        } else {
            winner = "YELLOW wins!";
        }
        JOptionPane.showMessageDialog(frame, winner);
        System.exit(0);
    }
}
