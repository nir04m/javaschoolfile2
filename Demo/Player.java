/**
*Authours: 
*/

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
	 * Gets the name of the player since name is initialized to private
	 */
	public String getName(){
		return name;
	}

    /**
     * Takes in the name of the player by asking for their name to be inputed
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
	 * addMoney adds a certain amount of money to the player's total money.
	 * @param: plusMoney which takes in the specific amount of money added to the player's money.
	 */
	public void addMoney(int plusMoney){
		money += plusMoney;
	}

	/**
	 * takeMoney subtracts a certain amount of money from the player's total money.
	 * @param: minusMoney which takes in the specific amount of money removed from the player's total money.
	 */
	public void takeMoney(int minusMoney){
		money -= minusMoney;
	}

	/**
	 * getMoney returns the current amount of money in possession of the player.
	 * return: money: returns the amount of money.
	 */
	public int getMoney(){
		return money;
	}
	
}
