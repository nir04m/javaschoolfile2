/**
*Authours: 
*/

import java.util.Scanner;
import java.util.Random;

public class ProjectMain{
	
	//Creating an object called game of the class board.
	private Board game = new Board();

	//Creating objects for each player of the class player.
	private Player p1 = new Player();
	private Player p2 = new Player();
	private Player p3 = new Player();
	private Player p4 = new Player();

	//Inializing Java utility classes
	private Scanner input = new Scanner(System.in);
	private Random dice = new Random();

	//instance variables set to private to avoid leaks.
	private String userInput = null;
	private boolean key;
	private boolean endGame = false;
	private boolean endTurn;

	/**
 	* startGame is called when the game begins. It invokes the setter methods that set the names of the players. 
 	* It also contains ths condition that ends the game.
 	*/
	public void startGame (){
		p1.setName();
		p2.setName();
		p3.setName();
		p4.setName();
		game.newGameBoard();
		int x = 0;
		while (!endGame){
			rollDice(p1);
			rollDice(p2);
			rollDice(p3);
			rollDice(p4);
			
			if (p1.getMoney()>=10000 || p2.getMoney()>=10000 || p3.getMoney()>=10000 || p4.getMoney()>=10000){
				endGame = true;
			}
			x++;
			System.out.println("Finished turn: " +x);
		}
		System.out.println(" ");
		gameFinished();
	}
	
	/**
	 * gameFinished is called when the game is ended. It checks for the player who wins the game and displays a congratulationary message.
	 */
	public void gameFinished (){
		if (p1.getMoney()>=10000){
			System.out.println (p1.getName() + " has won the game!!");
		}
		else if (p2.getMoney()>=10000){
			System.out.println (p2.getName() + " has won the game!!");
		}
		else if (p3.getMoney()>=10000){
			System.out.println (p3.getName() + " has won the game!!");
		}
		else if (p4.getMoney()>=10000){
			System.out.println (p4.getName() + " has won the game!!");
		}
	}
		
	/**
	 * rollDice randomizes the value of the dice if a player chooses to roll.
	 * It also invokes the method move which moves the player according to the value displayed on the dice.
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */
	public void rollDice(Player subject){
		key = false;
		endTurn = false;
		
		while (!key){
			System.out.println("");
			System.out.println (subject.getName() + "'s turn.");
			System.out.println("Your current position is: " + game.getPropertyName(subject.getPosition()));
			System.out.println("You currently have: $" + subject.getMoney());
			System.out.print("Press 'e' to exit the game. Press 'r' to roll dice: ");
			userInput = input.nextLine();
			userInput = userInput.toUpperCase();
			if(userInput.equals("E")){
					System.exit(0);
			}

			else if (userInput.equals("R")){
				key = true;
			}
		}
		int value = dice.nextInt(6) +1;
		subject.move(value);
		System.out.println("You have rolled a " + value);
		System.out.println("Your new position is: " + game.getPropertyName(subject.getPosition()));
		game.newTile(subject.getPosition(), subject);
		System.out.println("You now have: $" + subject.getMoney());
		while(!endTurn){
			System.out.print("Would you like to end turn (y/n): ");
			userInput = input.nextLine();
			userInput = userInput.toUpperCase();
			if (userInput.equals("Y")){
				endTurn = true;
			}
		}
	}
	
	/**
	 * Main method that stars the game.
	 */
	public static void main(String[] args){
		ProjectMain a = new ProjectMain();
		a.startGame();
	}

}
