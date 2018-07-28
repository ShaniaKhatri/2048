package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Shania Khatri
//Only used as an intermediary to test 2048 game function before building algorithm
//DONT SUBMIT

public class Runner {

public static void main(String[] args) throws IOException{
		
		Board bObj = new Board();
		
		//boolean invalidInput = false;
		
		//bObj.printBoard();
		
		
		String command = "";
		

		while(!(command.equals("quit"))){
			
			bObj.randomGenerator();
			bObj.printBoard();
			
			System.out.println();	
			System.out.println("Please enter 'up' to swipe up, 'down' to swipe down, 'left' to swipe left, and 'right' to swipe right, and 'quit' to quit.");

			
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader input = new BufferedReader(reader);
			command = input.readLine();
			
			boolean move;
			
			while(!(command.equals("up") || command.equals("down") || command.equals("left") || command.equals("right"))){
				
				//invalidInput = true;
				
				System.out.println("That is not a valid input. Please enter 'up' to swipe up, 'down' to swipe down, 'left' to swipe left, and 'right' to swipe right, and 'quit' to quit.");

				command = input.readLine();
			}
				
			
			
				if(command.equals("up")){
					
					move = bObj.up();
				}
				
				else if(command.equals("down")){
					
					move = bObj.down();
				}
				
				else if(command.equals("left")){
					
					move = bObj.left();
				}
				
				else{ //(command.equals("right"))
					
					move = bObj.right();
				}
			
				
				int state = bObj.boardState(); //move);
				
				if(state == 1){
						
					bObj.printBoard();
					System.out.println("Congrats! You have won 2048!");
					command = "quit";
						
				}
					
				else if(state == -1){
						
					bObj.printBoard();
					System.out.println("The game has ended. Please play again.");
					command = "quit";
						
				}
									
			}
		
		
		}
}
