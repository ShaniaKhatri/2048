package main;
import javax.swing.*;
import java.awt.*;

//Shania Khatri

public class GUI extends JFrame {
	
	//Private Data
	private static final long serialVersionUID = 1L;
	JLabel statusBar;
	JLabel instructions;
	
	/**
	 * Makes GUI and Board objects. Calls the random generator twice (so 2 tiles are on the board when game starts)
	 * Repaints so generated tiles show. Integrates keyboard listener. Adds board to window.
	 * @param args
	 */
	public static void main(String[] args) {
		
		GUI guiObj = new GUI();
	    Board board = new Board();
	        
	    board.randomGenerator();
	    board.randomGenerator();
	    board.repaint();
	    KeyboardListener kb = KeyboardListener.getkeyboardListener(board, guiObj);
	    board.addKeyListener(kb);
	    guiObj.add(board);
	        
	    guiObj.setLocationRelativeTo(null);
	    guiObj.setVisible(true);
	 }
	    
	/**
	 * Sets window title, sets window to close when exit button is pressed. Sets fixed window dimensions. 
	 * Initializes and formats the instructions label and statusBar and adds them to window.
	 */
	 public GUI(){
		 setTitle("2048");
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setSize(680, 856); //340, 400);
		 setResizable(false);
	    	
		 instructions = new JLabel("<html><div style='text-align: center;'>" + "Welcome to 2048! Use the arrow keys to join the numbers and get to the 2048 tile." + "</html></div>");
		 instructions.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		 add(instructions, BorderLayout.NORTH);
		 
		 statusBar = new JLabel("");
		 statusBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		 add(statusBar, BorderLayout.SOUTH);
	 }
	    
	 /**
	  * Sets text of status bar to win message.
	  */
	  void win(){
		  statusBar.setText("    You won! Keep playing!");
	  }
	    
	  /**
	   * Sets text of status bar to lose message.
	   */
	  void lose(){
		  statusBar.setText("                You lose!");
	  }
	
}
