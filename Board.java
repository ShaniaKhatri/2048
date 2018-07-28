package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

//Shania khatri
public class Board extends JPanel{
	
	//Private Data
	private static final long serialVersionUID = 1L;

	private Tile[][] board = new Tile[4][4];
	
	private int len = board.length;
	
	private static final Color BG_COLOR = new Color(0xD3D3D3);

    private static final Font STR_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 40); //20);
   
    //pixel size of one side of a tile
    private static final int SIDE = 128; //64;
    
    //pixel size of margin between tiles
    private static final int MARGIN = 32; //16;
	
    //Constructor makes 4 x 4 board with 16 tiles
	public Board(){
		setFocusable(true);
		for(int x = 0; x < len; x++){
			for(int y = 0; y < len; y++){
				board[x][y] = new Tile();
			}
		}
	}
	
	/**
	 * Resets a tile so it appears empty by setting all of its private data to original values
	 * @param row Takes parameter of what row location of tile on board
	 * @param col Takes parameter of what row location of tile on board
	 */
	public void emptyTile(int row, int col){
		board[row][col].setValue(0);
		board[row][col].setColor(179, 179, 179);
		board[row][col].setEmpty(true);
		board[row][col].setAlreadyAdded(false);
	}
	
	
	/** 
	 * Uses 2 for loops to go through rows 1, 2, 3 and all columns.
	 * At each space, check if there is an empty spot above a filled tile. 
	 * If there is, shifts the tile as far up as is empty, and indicates that a valid move was made.
	 * Then, if you're not at the top of the board, and the tile hasn't already combined with a tile above it,
	 * and both tiles have the same value, combines the tile with the tile above it (emptying the lower tile). 
	 * Indicates that a valid move was made.
	 * The tiles are reset so alreadyAdded is false for all.
	 * @return boolean value for if move is valid or not (has anything shifted or added)
	 */
	public boolean up(){
		
		boolean valid = false;
		
		for(int row = 1; row < len; row++){  //only needs to look at rows 1, 2, 3
			
			for(int col = 0; col < len; col++){ //cols 0, 1, 2, 3
				
				if(board[row][col].isEmpty() == false){ //only execute all of this if there is actually a tile on the board at this location
					
					int aboveRow = row-1;
					
					int newRow = row;
					
					if(board[aboveRow][col].isEmpty()){ //aka there is room above
						
						while((aboveRow >= 0) && (board[aboveRow][col].isEmpty())){
							board[aboveRow][col].shiftTile(board[aboveRow+1][col]);
							emptyTile(aboveRow+1, col);
							aboveRow--;
							
							valid = true; //something shifted so move was valid
						}
						
						newRow = aboveRow+1; //new row value after tile has been shifted up
					}
					
					if((aboveRow >= 0) && (board[newRow][col].alreadyAdded() == false) 
							&& (board[aboveRow][col].alreadyAdded() == false) 
							&& (board[newRow][col].getValue() == board[aboveRow][col].getValue())){
						
						board[aboveRow][col].setValue(board[aboveRow][col].addTile(board[newRow][col]));
						
						board[aboveRow][col].recolorTile();
						board[aboveRow][col].setAlreadyAdded(true);
						emptyTile(newRow, col);
						
						valid = true;
						
					}
					
				}
				
				
			}//inner for loop
		}//outer for loop
		
		resetAlreadyAdded();
		
		return valid;
	}
	
	/** 
	 * Uses 2 for loops to go through rows 2, 1, 0 (in that order) and all columns.
	 * At each space, check if there is an empty spot below a filled tile. 
	 * If there is, shifts the tile as far down as is empty, and indicates that a valid move was made.
	 * Then, if you're not at the bottom of the board, and the tile hasn't already combined with a tile below it,
	 * and both tiles have the same value, combines the tile with the tile below it (emptying the upper tile). 
	 * Indicates that a valid move was made.
	 * The tiles are reset so alreadyAdded is false for all.
	 * @return boolean value for if move is valid or not (has anything shifted or added)
	 */
	public boolean down(){
		
		boolean valid = false;
		
		for(int row = 2; row >= 0; row--){
			
			for(int col = 0; col < len; col++){
				
				if(board[row][col].isEmpty() == false){ //only execute all of this if there is actually a tile on the board at this location
					
					int belowRow = row+1;
					
					int newRow = row;
					
					if(board[belowRow][col].isEmpty()){ //aka there is room below
						
						while((belowRow < len) && (board[belowRow][col].isEmpty())){
							board[belowRow][col].shiftTile(board[belowRow-1][col]);
							emptyTile(belowRow-1, col);
							belowRow++;
							
							valid = true; //something shifted so move was valid
						}
						
						newRow = belowRow-1; //new row value after tile has been shifted up
					}
					
					if((belowRow < len) && (board[newRow][col].alreadyAdded() == false) && (board[belowRow][col].alreadyAdded() == false) && (board[newRow][col].getValue() == board[belowRow][col].getValue())){
						
						board[belowRow][col].setValue(board[belowRow][col].addTile(board[newRow][col]));
						
						board[belowRow][col].recolorTile();
						board[belowRow][col].setAlreadyAdded(true);
						emptyTile(newRow, col);
						
						valid = true;
						
					}
					
				}
				
				
			}//inner for loop
		}//outer for loop
		
		resetAlreadyAdded();
		
		return valid;
	}
		

	/** 
	 * Uses 2 for loops to go through all rows and columns 1, 2, 3.
	 * At each space, check if there is an empty spot to the left a filled tile. 
	 * If there is, shifts the tile as far left as is empty, and indicates that a valid move was made.
	 * Then, if you're not at the left edge of the board, and the tile hasn't already combined with a tile left of it,
	 * and both tiles have the same value, combines the tile with the tile left of it (emptying the right tile). 
	 * Indicates that a valid move was made.
	 * The tiles are reset so alreadyAdded is false for all.
	 * @return boolean value for if move is valid or not (has anything shifted or added)
	 */
	public boolean left(){
		
		boolean valid = false;
		
		for(int row = 0; row < len; row++){
			
			for(int col = 1; col < len; col++){ //cols 1, 2, 3
				
				if(board[row][col].isEmpty() == false){ //only execute all of this if there is actually a tile on the board at this location
					
					int leftCol = col-1;
					
					int newCol = col;
					
					if(board[row][leftCol].isEmpty()){ //aka there is room on the left
						
						while((leftCol >= 0) && (board[row][leftCol].isEmpty())){
							board[row][leftCol].shiftTile(board[row][leftCol+1]);
							emptyTile(row, leftCol+1);
							leftCol--;
							
							valid = true; //something shifted so move was valid
						}
						
						newCol = leftCol+1; //new col value after tile has been shifted left
					}
					
					if((leftCol >= 0) && (board[row][newCol].alreadyAdded() == false) && (board[row][leftCol].alreadyAdded() == false) && (board[row][newCol].getValue() == board[row][leftCol].getValue())){
						
						board[row][leftCol].setValue(board[row][leftCol].addTile(board[row][newCol]));
						
						board[row][leftCol].recolorTile();
						board[row][leftCol].setAlreadyAdded(true);
						emptyTile(row, newCol);
						
						valid = true;
						
					}
					
				}
				
				
			}//inner for loop
		}//outer for loop
		
		resetAlreadyAdded();
		
		return valid;
	}
	
	
	/** 
	 * Uses 2 for loops to go through all rows and columns 2, 1, 0 (in that order).
	 * At each space, check if there is an empty spot to the right a filled tile. 
	 * If there is, shifts the tile as far right as is empty, and indicates that a valid move was made.
	 * Then, if you're not at the right edge of the board, and the tile hasn't already combined with a tile right of it,
	 * and both tiles have the same value, combines the tile with the tile to the right of it (emptying the left tile). 
	 * Indicates that a valid move was made.
	 * The tiles are reset so alreadyAdded is false for all.
	 * @return boolean value for if move is valid or not (has anything shifted or added)
	 */
	public boolean right(){
		
		boolean valid = false;
		
		for(int row = 0; row < len; row++){
			
			for(int col = 2; col >= 0; col--){
				
				if(board[row][col].isEmpty() == false){ //only execute all of this if there is actually a tile on the board at this location
					
					int rightCol = col+1;
					
					int newCol = col;
					
					if(board[row][rightCol].isEmpty()){ //aka there is room below
						
						while((rightCol < len) && (board[row][rightCol].isEmpty())){
							board[row][rightCol].shiftTile(board[row][rightCol-1]);
							emptyTile(row, rightCol-1);
							rightCol++;
							
							valid = true; //something shifted so move was valid
						}
						
						newCol = rightCol-1; //new row value after tile has been shifted up
					}
					
					if((rightCol < len) && (board[row][newCol].alreadyAdded() == false) && (board[row][rightCol].alreadyAdded() == false) && (board[row][newCol].getValue() == board[row][rightCol].getValue())){
						
						board[row][rightCol].setValue(board[row][rightCol].addTile(board[row][newCol]));
						
						board[row][rightCol].recolorTile();
						board[row][rightCol].setAlreadyAdded(true);
						emptyTile(row, newCol);
						
						valid = true;
						
					}
					
				}
				
				
			}//inner for loop
		}//outer for loop
		
		resetAlreadyAdded();
		
		return valid;
	}
		

	/**
	 * Checks if there are any empty spaces left on the board, or if 2048 has been reached (if so, return 1). 
	 * If there are no empty spaces (so no shifting can be done of tiles) and no tiles next to each other can be combined, 
	 * then no valid moves can potentially be made, so the player has lost.
	 * Otherwise, the player can keep playing.
	 * @return An int indicating whether the game has been won, lost, or neither.
	 */
	public int boardState(){ //has won, has lost, neither

		boolean full = true;
		boolean validMovesLeft = false;
	
		for(Tile[] x : board){
	
				for(Tile y : x){
		
					if((y.isEmpty())){ 
					//are there any empty spaces left to move?
		
						full = false;
					}
		
		
					else if(y.getValue() == 2048){
						return 1; //has won
					}
				}
		}

		if(full == true){
			
			for(int row = 0; row < len; row++){
				for(int col = 0; col < len; col++){
					
					int val = board[row][col].getValue();
					
					if(((row - 1) >= 0) && (board[row-1][col].getValue() == val)){ 
						//if there's a tile, check if combination above is possible
							validMovesLeft = true;
						
					}
					
					if(((row + 1) < 4) && (board[row+1][col].getValue() == val)){ 
						//if there's a tile, check if combination below is possible
							validMovesLeft = true;
						
					}
					
					if(((col - 1) >= 0) && (board[row][col-1].getValue() == val)){ 
						//if there's a tile, check if combination to the right is possible
							validMovesLeft = true;
						
					}
					
					if(((col + 1) < 4) && (board[row][col+1].getValue() == val)){ 
						//if there's a tile, check if combination to the right is possible
							validMovesLeft = true;
						
					}
					
				}//for
			}//for
			
			if(validMovesLeft == false){
			
				return -1; //has lost
			}
		}

		return 0; //hasn't won or lost
	}



	/**
	 * Generates a random number 0 or 1. If 0 is generated, the num is set to 2. If 1, then 4.
	 * Random coordinates for x and y (between 0 and 3 inclusive) are generated for the tile with 
	 * the random num to appear in. If the space is not empty, a new set of coordinates is generated.
	 * The random tile appears by changing the value, recoloring, and setting isEmpty to false.
	 */
	public void randomGenerator(){
		
		//generating the random num
		int num = (int)(2 * Math.random());
		
		if(num == 0){
			num = 2;
		}
		
		else{ //num == 1
			num = 4;
		}
		
		
		int row;
		int col;
		
		do{
			row = (int)(Math.random() * (4));
					
			col = (int)(Math.random() * (4));
		}
		
		while(board[row][col].isEmpty() == false);
		
		
		
		board[row][col].setValue(num);
		board[row][col].recolorTile();
		board[row][col].setEmpty(false);
		
	}
	
	/**
	 * 2 for each loops iterate through the board and print out tile values with a tab inbetwen.
	 * At the end of each row, a new line is started.
	 */
	public void printBoard(){
	
		System.out.println();
		
		for(Tile[] x : board){
			
			for(Tile y : x){
				
				System.out.print(y.getValue() + "\t");
			}
			
			System.out.println("\n");
		}
	
	}
	
	/**
	 * 2 for each loops iterate through the board at set the value of alreadyAdded to false for each tile.
	 */
	public void resetAlreadyAdded(){
		
		for(Tile[] x : board){
			
			for(Tile y : x){
				
				y.setAlreadyAdded(false);

			}
		}
	}
	
	/**
	 * Sets background color to private data
	 * Sets font size and style to private data
	 * Fills board rectangle
	 * Draws a tile at each space in the board using 2 for each loops.
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(BG_COLOR);
		g.setFont(STR_FONT);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		int intX = 0;
		int intY = 0;
		
		for(Tile[] x : board){
			intX=0;
			for(Tile y : x){
				drawTile(g, y, intX, intY);
	            	intX++;
			}
			intY++;
		}
	}
	  
	/**
	 * Draws a tile using x and y coordinates (where the top left corner is 0,0). Sets the color based on
	 * the tile's color private data (an array representing RGB values). Then fills a rectangle with dimensions 
	 * from the offsetCoors method and private data. Resets color to black or white in preparation for coloring the value of the tile.
	 * If the tile is not empty, displays the value of the tile in the center of the tile.
	 * @param g
	 * @param tile Takes the tile object that is being displayed.
	 * @param x Takes the x coordinate value of the tile (NOT ARRAY INDEX) where 0,0 is top left of the board.
	 * @param y Takes the y coordinate value of the tile (NOT ARRAY INDEX) where 0,0 is top left of the board.
	 */
	public void drawTile(Graphics g, Tile tile, int x, int y){
	
		int xOffset = offsetCoors(x);
		int yOffset = offsetCoors(y);
		
		g.setColor(new Color(tile.getColor()[0], tile.getColor()[1], tile.getColor()[2]));
		g.fillRect(xOffset, yOffset, SIDE, SIDE);
		
		if(tile.getValue() <= 16){
			g.setColor(new Color(0,0,0)); //black text for tiles with lower value (lighter colored tile)
		}
		else{
			g.setColor(new Color(255, 255, 255)); //white text for tiles with higher value (darker colored tile)
		}
		
		
		
		if(tile.isEmpty() == false){
			
			int textWidth = g.getFontMetrics().stringWidth(Integer.toString(tile.getValue()));
			
			g.drawString(Integer.toString(tile.getValue()), xOffset + (SIDE/2) - (textWidth/2), yOffset + (SIDE/2) + (MARGIN/2));
			
		}
	}  
	
	/**
	 * Calculates the coordinate of a tile in pixels (where 0,0 is top left) using the board coordinate.
	 * @param pos The board coordinate of the tile (0, 1, 2, or 3)
	 * @return The pixel coordinates
	 */
	private static int offsetCoors(int pos){
		return pos * (MARGIN + SIDE) + MARGIN;
	}
}

