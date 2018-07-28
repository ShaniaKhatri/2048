package main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Shania Khatri

public class KeyboardListener extends KeyAdapter {
	
	//Private Data
	private static GUI guiObj;
	private static Board board;
	private boolean debug = false;

	
	private static final KeyboardListener INSTANCE = new KeyboardListener();
	private boolean invalidKey;
	    
	//Constructor
	public static KeyboardListener getkeyboardListener(Board b, GUI g) {
		guiObj = g;
		board = b;
		return INSTANCE;
	}
	
	public boolean getInvalidKey(){
		return invalidKey;
	}
	
	/**
	 * Enters a while loop (which will end if up, down, left, or right are pressed). Uses switch statements to match
	 * arrow keys to moves. Saves boolean from method to a variable called move that tells whether that move was possible.
	 * If an invalidKey was entered, while loop does not break until an arrow key is entered. Generates a random number after move is executed, and checks the state of the board.
	 * If won or lost, outputs appropriate message.
	 */
	@Override
	public void keyPressed(KeyEvent e){
		
		boolean move = true;
		
		switch(e.getKeyCode()){
			
			case KeyEvent.VK_UP:
				move = board.up();
				invalidKey = false;
				break;
			
			case KeyEvent.VK_DOWN:
				move = board.down();
				invalidKey = false;
				break;
			
			case KeyEvent.VK_LEFT:
				move = board.left();
				invalidKey = false;
				break;
					
			case KeyEvent.VK_RIGHT:
				move = board.right();
				invalidKey = false;
				break;
					
			default:
				invalidKey = true;
				break;
		}//switch

		
		if(invalidKey == false){
			
			if(debug){System.out.println("before checking state");}
			
			int state = board.boardState();
			
			
			if(state == -1){
				guiObj.lose();				
			}
			
			else{ //state is 1, or 0
				
				if(state == 1){
					guiObj.win();
				}
				
				else{//state is 0
					
					//only generating a new tile if a valid move was made (so something was combined or something shifted)
					if(move){
					board.randomGenerator();
					}
				}
			}
			
			board.repaint();
		
		}
				
	}

}
