import java.util.Scanner;

public class Player {

	/**
	* instant variables to private to prevent leaks
	*/

	private String name = "";
	private int position = 0;
	private int money = 2000;

	/**
	*move gets the player's current position and check if the player has gone round the board and add 200 to the current money player has
	*@param: move: the number of tiles the player moved after rolling the dice
	*/

	public void move(int move){
		position = position + move;
		if (position >= 28){
			position = position - 27;
			money = money + 200;
			System.out.println("You passed 'GO', collect $200 ($200 added to you account)");
		}
	}
	
	/**
	 *Gets the player's position since position is initialized to private
	 * @return; returns the players position
	 */
	public int getPosition(){
		return position;
	}
    
    /**
     * Gets the name of the players
     * @return: returns the names of the player
     */
	public String getName(){
		return name;
	}

    /**
     * Requests player to input their name and Sets the name of the player 
     */ 
	public void setName(){
		System.out.print("Enter your name: ");
		Scanner input = new Scanner(System.in);
		name = input.next();
	}
	
	/**
	 *setMoney updates how much money a player has during the course of the game.
	 * @param: newMoney which takes in the amount of money a player has after every transaction.
	 */	
	public void setMoney (int newMoney){
		money = newMoney;
	}

    /**
     * Adds money to a player's total money
     * @param: plusMoney: The amount of money to be added into the player's total money.
     */ 
	public void addMoney(int plusMoney){
		money += plusMoney;
	}

    /**
     * Removes money from a player's total money
     * @param: minusMoney: The amount of money taken away from the player's total money.
     */ 
	public void takeMoney(int minusMoney){
		money -= minusMoney;
	}

    /**
     * Gets the amount of money a player has.
     */ 
	public int getMoney(){
		return money;
	}

}
