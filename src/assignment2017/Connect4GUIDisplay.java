package assignment2017;
import java.awt.*;
import javax.swing.*;


import assignment2017.codeprovided.*;

@SuppressWarnings("serial")
public class Connect4GUIDisplay extends JFrame implements Connect4Displayable  {

	@Override
	public void displayBoard() {
		
		Toolkit toolkit =  Toolkit.getDefaultToolkit();

		setTitle("Connect4");
		//setSize("300, 200");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//creating menu bar for restarting and exiting the game
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Options");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Restart");
		menu.add(menuItem);
		JMenuItem menuItem2 = new JMenuItem("Exit");
		menu.add(menuItem2);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,0));
		buttonPanel.add(new JButton("0"));
		buttonPanel.add(new JButton("1"));
		buttonPanel.add(new JButton("2"));
		buttonPanel.add(new JButton("3"));
		buttonPanel.add(new JButton("4"));
		buttonPanel.add(new JButton("5"));
		buttonPanel.add(new JButton("6"));
		
	}

}
