package main;
//Shania Khatri

public class Tile {

	//Private Data
	private int value;
	private int[] color;
	private boolean empty;
	private boolean alreadyAdded;
	
	//Constructor
	public Tile(){
		value = 0;
		color = new int[]{179, 179, 179}; //array with RGB vals
		empty = true;
		alreadyAdded = false;
	}
	
	//Getters
	public int getValue(){
		return value;
	}
	
	public int[] getColor(){
		return color;
	}
	
	public boolean isEmpty(){
		return empty;
	}
	
	public boolean alreadyAdded(){
		return alreadyAdded;
	}
	
	//Setters
	public void setValue(int val){
		value = val;
	}
	
	public void setColor(int red, int green, int blue){
		color[0] = red;
		color[1] = green;
		color[2] = blue;
	}
	
	public void setEmpty(boolean empt){
		empty = empt;
	}
	
	public void setAlreadyAdded(boolean add){
		alreadyAdded = add;
	}
	
	/**
	 * Adds values of tiles when tiles are added/combined
	 * @param other 2nd tile in adding
	 * @return Aggregate value
	 */
	public int addTile(Tile other){
		return (getValue() + other.getValue());
	}
	
	/**
	 * Moves a tile by setting tile 1's values to tile 2's values (effectively, tile 1's spot now contains tile 2).
	 * @param other is Tile 2
	 */
	public void shiftTile(Tile other){
		setValue(other.getValue());
		setColor(other.getColor()[0], other.getColor()[1], other.getColor()[2]);
		setEmpty(other.isEmpty());
		
	}
	
	/**
	 * Uses values of tiles to determine color with RGB values. Colors get darker as tile value increases.
	 */
	public void recolorTile(){
		
		switch(getValue()){
			
			case 2:
				setColor(255, 255,255);
				break;
				
			case 4:
				setColor(204, 255, 255);
				break;
				
			case 8:
				setColor(128, 255, 255);
				break;
				
			case 16:
				setColor(51, 204, 255);
				break;
				
			case 32:
				setColor(0, 153, 255);
				break;
				
			case 64:
				setColor(0, 102, 204);
				break;
				
			case 128:
				setColor(51, 51, 255);
				break;
				
			case 256:
				setColor(0, 0, 204);
				break;
				
			case 512:
				setColor(0, 0, 153);
				break;
				
			case 1024:
				setColor(0, 0, 102);
				break;
				
			case 2048:
				setColor(0, 0, 51);
				break;
		
			default: //aka 0 aka empty
				setColor(179, 179, 179);
				break;
		}
	}
}
