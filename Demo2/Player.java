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

	public String getName(){
		return name;
	}

	public void setName(){
		System.out.print("Enter your name: ");
		Scanner input = new Scanner(System.in);
		name = input.next();
	}

	public void setMoney (int newMoney){
		money = newMoney;
	}

	public void addMoney(int plusMoney){
		money += plusMoney;
	}

	public void takeMoney(int minusMoney){
		money -= minusMoney;
	}

	public int getMoney(){
		return money;
	}
	
	public void setPosition(int amount){
		this.position = amount;
	}

}
