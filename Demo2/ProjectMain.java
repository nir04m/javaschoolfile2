import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProjectMain implements ActionListener{
	//Creating an object called game of the class board.
	private Board game = new Board();
	//Creating objects for each player of the class player.
	private Player p1 = new Player();
	private Player p2 = new Player();
	private Player p3 = new Player();
	private Player p4 = new Player();
	private Random dice = new Random();
	private BoardGUI boardScreen = new BoardGUI(this);
	
	//instance variables set to private to avoid leaks.
	private int iterate = 0;
	
	//instance variable for person of type player ,rollDie of type boolean and buyPrperty of type boolean
	private boolean rollDie = false;
	private boolean buyProperty = false;
	private Player person;

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
	}
	
	/**
	 *@prem: x is a value set to know whose turn is currently displayed on the boardGUI 
	 *person is of type player and sets the player information to display on the boardGUI
	*/
	public void nextPlayer (int x) {
		if (x == 1) {
			person = p1;
		}
		
		else if (x == 2) {
			person = p2;
		}
		
		else if (x == 3) {
			person = p3;
		}
		
		else if (x == 4) {
			person = p4;
		}
		
		/**
		 * rollDie == true; is to check if the player turn has ended and display the current player information before changing to the next player 
		*/
		playerInformation (person);
		if (rollDie == true) {
			rolledDie(person);

		}
	}
	
	/**
	 * it displays the player information i.e the balance,current position, the amount rolled, the amount the value the property cost, the rent of that property
	 * @prem: subject of type player is the name of the current player playing their turn.  
	*/
	public void playerInformation(Player subject) {
		int playerMoney = subject.getMoney();
		boardScreen.setMoneyLabel(playerMoney);
		boardScreen.setNameLabel(subject.getName());
		boardScreen.setPropertyLabel(game.getPropertyName(subject.getPosition()));

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
	 * this method is the action event listener it also sets what buttons should be visible and should not be visible to reduce error of a player pressing a button twice
	 * when not needed.
	*/
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("NEXT TURN")) {
			if (iterate < 5) {
				iterate++;
				boardScreen.setRollButtonVisibility(true);
				boardScreen.setNextButtonVisibility(false);
				rollDie = false;
				nextPlayer(iterate);
			}else {
				iterate = 0;
			}

		}
		
		if (event.getActionCommand().equals("BUY")){
			buyProperty = true;
			buyProperty();
			boardScreen.setBuyButtonVisibility(false);
			boardScreen.setPassButtonVisibility(false);
			boardScreen.setRollButtonVisibility(false);
			boardScreen.setNextButtonVisibility(true);
		}
		
		/**
		 * when a player click or presses the roll button it iterates whose turn and calls the nextPlayer method  
		*/
		if (event.getActionCommand().equals("ROLL DICE")){
			if (iterate == 1) {
				rollDie = true;
				nextPlayer(1);
			}else if (iterate == 2) {
				rollDie = true;
				nextPlayer(2);
			}else if (iterate == 3) {
				rollDie = true;
				nextPlayer(3);
			}else if (iterate == 4) {
				rollDie = true;
				nextPlayer(4);
			}
			if (iterate >= 4) {
				iterate = 0;
				boardScreen.setBuyButtonVisibility(false);
				boardScreen.setPassButtonVisibility(false);
				boardScreen.setRollButtonVisibility(false);
				boardScreen.setNextButtonVisibility(true);
			}

		}

		if (event.getActionCommand().equals("EXIT")){
			System.exit(0);
			System.out.println("a");
		}

		if (event.getActionCommand().equals("PASS")){
			boardScreen.setBuyButtonVisibility(false);
			boardScreen.setPassButtonVisibility(false);
			boardScreen.setRollButtonVisibility(false);
			boardScreen.setNextButtonVisibility(true);
		}
	
	}
	
/**
 * rollDice randomizes the value of the dice if a player chooses to roll.
 * It also invokes the method move which moves the player according to the value displayed on the dice.
 * @param: subject: Of type player. Passes on all the attributes from the player class.
 */
	
	public void rolledDie (Player subject) {

		int value = dice.nextInt(6) +1;
		subject.move(value);
		boardScreen.setPropertyLabel(game.getPropertyName(subject.getPosition()));
		int playerMoney = subject.getMoney();
		boardScreen.setMoneyLabel(subject.getMoney());
		System.out.println("Position: " + subject.getPosition());
		boolean buyAble = game.newTile(subject.getPosition(), subject);
		if (buyAble == true) {
			boardScreen.setBuyButtonVisibility(true);
			boardScreen.setPassButtonVisibility(true);
			boardScreen.setRollButtonVisibility(false);

		}
		else if (buyAble == false) {
			boardScreen.setBuyButtonVisibility(false);
			boardScreen.setPassButtonVisibility(false);
			boardScreen.setRollButtonVisibility(false);
			boardScreen.setNextButtonVisibility(true);
			
		}
		buyProperty = false;
		buyAble = false;

		playerMoney = subject.getMoney();
		boardScreen.setMoneyLabel(playerMoney);
		rollDie = false;
	}
	
	/**
	 * to reduce privacy leaks it checks which player buys a property and do the needed transaction to the player balance 
	*/
	public void buyProperty() {
		game.buy(person.getPosition(), person);
		System.out.println("Money: " + person.getMoney());
		System.out.println("Money: " + person.getMoney());
		System.out.println("Player: " + person.getName());
		int playerMoney = person.getMoney();
		boardScreen.setMoneyLabel(playerMoney);
	}


/**
 * Main method that stars the game.
 */
	public static void main(String[] args) {
		ProjectMain p1 = new ProjectMain();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		 
				p1.boardScreen.setVisible(true);
				 }
		 	});
		p1.startGame();
	}

}