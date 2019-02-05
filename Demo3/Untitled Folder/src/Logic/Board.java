package Logic;
import java.util.*;

import gui.Point;

public class Board{

	//Inialize game board list
	private Player [] gameBoard = new Player [28];
	//added list of points to genarate avatars. not very accreate. Might want to ajust numbers later.
	private ArrayList <Point> coord = new ArrayList<Point> (Arrays.asList(new Point(100,100), new Point(200,100), new Point(300,100), new Point(400,100), new Point(500,100), new Point(600,100), new Point(700,100), new Point(800,100),
		new Point(800,200), new Point(800,300), new Point(800,400), new Point(800,500), new Point(800,600), new Point(800,700), new Point(800,800), new Point(700,800), new Point(600,800), new Point(500,800), new Point(400,800),
		new Point(300,800), new Point(200,800), new Point(100,800), new Point(100,700), new Point(100,600), new Point(100,500), new Point(100,400), new Point(100,300), new Point(100,200)));
	//instance varibales
	private ArrayList <Integer> rent = new ArrayList<Integer> (Arrays.asList(0, 5, 5, 200, 100, 10, 15, 0, 20, 20, 25, 0, 30, 35, 100, 40, 40, 45, 200, 100, 120, 300, 150, 100, 170, 0, 200, 220));
	private ArrayList <Integer> value = new ArrayList <Integer> (Arrays.asList(0, 60, 70, 200, 200, 100, 150, 0, 170, 170, 190, 0, 210, 220, 0, 240, 240, 260, 200, 280, 300, 300, 320, 200, 340, 0, 350, 400));
	private ArrayList <String> propertyNames = new ArrayList <String> (Arrays.asList("Go" , " Meditranian Ave.", "Baltic Ave.", "INCOME TAX", "Railroad 1", "Oriental Ave.", "Vermont Ave.",
	"Just visiting jail", "St. Charels", "States Ave.", "Virginia Ave.", "Community card", "St. James", "NYC Ave.", "Free Parking", "Kentucky Ave.", "Indiana Ave.", "Illinos Ave.", "Luxary Tax", "Atlantic Ave.",
	"Ventnor Ave.", "Jail", "Pacific Ave.", "Railroad 2", "Carolina Ave.", "Community card", "Park Place", "BoardWalk"));
	private Random newEvent = new Random();
	
	/**
	 * It creates the gameboard as an array list and sets every element in the list to null.
	 */ 
	public void newGameBoard(){
		for (int x = 0; x < gameBoard.length; x++){
			gameBoard[x] = null;
		}
	}
	
	/**
	 * getRent gets the value of rent of the property landed on.
	 * @param: property: Takes in the current property that the player has landed on.
	 * @return: returns the value of the rent.
	*/
	public int getRent(int property){
		int rentValue = rent.get(property);
		return rentValue;
	}
	
	 /**
	 * getPropertyName gets the name of the property that has been landed on
	 * @param: position: Takes in the current position the player is on on the board.
	 * @return: returns the name of the property.
	 */
	public String getPropertyName (int position){
		String name = propertyNames.get(position);
		return name;
	}
	
	/**
	 * getValue gets the value of the property that has been landed on.
	 * @param: position: Takes in the current position the player is on on the board.
	 * @return: returns the value of the property as amount.
	 */
	public int getValue (int position){
		int amount = value.get(position);
		return amount;
	}
	
	/**
	 * Checks if a player owns a tile
	 * @param position: the current position of the player currently playing
	 * @return: returns the contents of the element of the array of type Player
	 */
	public Player getPlayer (int position){
		return gameBoard[position];
	}

	
	public Point getPoint(int position){
		Point p = coord.get(position);
		return p;
	}
	

	public void setPoint(int aX, int aY){
		for (int x = 0; x < coord.size(); x++){
			coord.get(x).moveRight(aX);
			coord.get(x).moveDown(aY);
		}
	}
	
	/**
	 * newTile checks the property that has been landed on and shows if the property is eligible to be bought or not.
	 * It also gives players an option to buy and lets them know when a property is already owned by another player.
	 * @param: position : Takes in the current position of the player on the board
	 * @param: subject: Of type player. Passes on all the attributes from the player class. 
	 */ 
	public boolean newTile(int position, Player subject){ 
		boolean buyAble;
		if (position == 0 || position == 7 ||  position == 14|| position == 21 || position == 3 || position == 25 ||position == 11 ||position ==25){
			if (position == 3 || position == 25 || position == 21 || position == 14){
				speicalTile(position, subject);
			}
			buyAble = false;
		}
		
		else if (gameBoard[position] == null){
			buyAble = true;
		}
		else{
			subject.takeMoney(rent.get(position));
			gameBoard[position].addMoney(rent.get(position));
			buyAble = false;
		}
		return buyAble;
		 
	}
	
	public boolean communityCardCheck(int position) {
		boolean community;
		if(position == 11 || position == 25) {
			community = true;
			
		}
		else {
			community = false;
		}
		return community;
	}

	
	/**
	 * specialTile checks if a player lands on free parking and adds the value of rent ($100) to the players account.
	 * It also checks if the player landed on any of INCOME TAX, JAIL or LUXURY TAX and deducts their respective rent value.
	 * @param: position : Takes in the current position of the player on the board
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */ 
	public void speicalTile(int position, Player subject){
		if (position == 14){
			subject.addMoney(rent.get(position));
		}
		else{
			subject.takeMoney(rent.get(position));
		}
	}
	
	/**
	 * Gives the player a choice to either buy a property or forego it when they land on it.
	 * @param: position : Takes in the current position of the player on the board
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */	
	public void buy(int position, Player subject){
		gameBoard[position] = subject;
		subject.takeMoney(value.get(position));

	}
	

	
	public String communityCard(Player subject){
		int x = newEvent.nextInt(5) +1;
		String card = null;
		switch (x) {
			case 1:
				card = "3 tiles backward";
				subject.move(-3);
				System.out.println("your new position is "+subject.getPosition());
				break;
			
			case 2:
				card = "Go to jail";
				subject.setPosition(21);
				System.out.println("your new position is "+subject.getPosition());
				break;
		
			case 3:
				card = "2 tile forward";
				subject.move(2);
				System.out.println("your new position is "+subject.getPosition());
				break;
		
			case 4:
				card = "Move to GO";
				subject.setPosition(0);
				System.out.println("your new position is "+subject.getPosition());
				break;
				
			case 5:
				card = "Bank gave you 200";
				subject.addMoney(200);
				break;
		}
		return card;
		
	}

}
