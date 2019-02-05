import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class ProjectMain implements ActionListener, MouseListener{
	//Creating an object called game of the class board.
	private Board game = new Board();
	//Creating objects for each player of the class player.
	private Player p1 = new Player();
	private Player p2 = new Player();
	private Player p3 = new Player();
	private Player p4 = new Player();
	private Random dice = new Random();
	private BoardGUI gui = new BoardGUI(this);
	//instance variables set to private to avoid leaks.
	private int iterate = 0;
	private boolean rollDie = false;
	private Player person;
	private NameFrameGUI plName= new NameFrameGUI(this);

	/**
	 * startGame is called when the game begins. It invokes the setter methods that set the names of the players.
	 */
	public void startGame (){	
		game.newGameBoard();
	}

    /**
	 *@param: x is a value set to know whose turn is currently displayed on the boardGUI 
	 *person is of type player and sets the player information to display on the boardGUI
	*/

	public void nextPlayer (int x) {
		if (x == 1) {
			person = p1;
		}else if (x == 2) {
			person = p2;
		}else if (x == 3) {
			person = p3;
		}else if (x == 4) {
			person = p4;
		}
		
		guiDisplay(person);
		//rollDie == true; is to check if the player turn has ended and display the current player information before changing to the next player
		if (rollDie == true) {
			rolledDie(person);

		}
	}



	/**
	 * gameFinished is called when the game is ended. It checks for the player who wins the game and displays a congratulationary message.
	 */
	public void gameFinished (){
		if (p1.getMoney()>=10000){
			System.out.println (p1.getName() + " has won the game!!");
			System.exit(0);
		}
		else if (p2.getMoney()>=10000){
			System.out.println (p2.getName() + " has won the game!!");
			System.exit(0);
		}
		else if (p3.getMoney()>=10000){
			System.out.println (p3.getName() + " has won the game!!");
			System.exit(0);
		}
		else if (p4.getMoney()>=10000){
			System.out.println (p4.getName() + " has won the game!!");
			System.exit(0);
		}
	}
    
    /**
	 * this method is the action event listener it also sets what buttons should be visible and should not be visible to reduce error of a player pressing a button twice
	 * when not needed.
	 * @param event: for when a JButton is activated
	*/

	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("ENTER")){
			p1.setName(plName.entry1.getText());
			p2.setName(plName.entry2.getText());
			p3.setName(plName.entry3.getText());
			p4.setName(plName.entry4.getText());
			plName.setVisible(false);
			gui.setVisible(true);

		}
		
		if (event.getActionCommand().equals("NEXT TURN")) {
			if (iterate < 5) {
				iterate++;
				gui.setRollButtonVisibility(true);
				gui.setNextButtonVisibility(false);
				rollDie = false;
				nextPlayer(iterate);
			}else {
				iterate = 0;
			}
			gameFinished();
		}
		if (event.getActionCommand().equals("BUY")){
			buyProperty();
			gui.setBuyButtonVisibility(false);
			gui.setPassButtonVisibility(false);
			gui.setRollButtonVisibility(false);
			gui.setNextButtonVisibility(true);
		}
		//when a player click or presses the roll button it iterates whose turn and calls the nextPlayer method  
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
				gui.setBuyButtonVisibility(false);
				gui.setPassButtonVisibility(false);
				gui.setRollButtonVisibility(false);
				gui.setNextButtonVisibility(true);
			}
		}
		if (event.getActionCommand().equals("EXIT")){
			System.exit(0);
			System.out.println("a");
		}
		if (event.getActionCommand().equals("PASS")){
			gui.setBuyButtonVisibility(false);
			gui.setPassButtonVisibility(false);
			gui.setRollButtonVisibility(false);
			gui.setNextButtonVisibility(true);
		}
	}

	/**
	 * rolledDie randomizes the value of the dice if a player chooses to roll.
	 * It also invokes the method move which moves the player according to the value displayed on the dice.
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */
	public void rolledDie (Player subject) {
		int value = dice.nextInt(6) +1;
		subject.move(value);
		guiDisplay(subject);
		gui.setDiceLabel(value);
		System.out.println("Position: " + subject.getPosition());
		boolean buyAble = game.newTile(subject.getPosition(), subject);
		
		if (buyAble == true) {
			gui.setBuyButtonVisibility(true);
			gui.setPassButtonVisibility(true);
			gui.setRollButtonVisibility(false);

		}else if (buyAble == false) {
			gui.setBuyButtonVisibility(false);
			gui.setPassButtonVisibility(false);
			gui.setRollButtonVisibility(false);
			gui.setNextButtonVisibility(true);
		}
		buyAble = false;
		rollDie = false;
		guiDisplay(subject);
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
		gui.setMoneyLabel(playerMoney);
	}
	
    /**
	 * it displays the player information i.e the balance,current position, the amount rolled, the amount the value the property cost, the rent of that property
	 * @param: subject of type player is the name of the current player playing their turn.  
	*/
	public void guiDisplay (Player subject){
		gui.setPropertyNameLabel(game.getPropertyName(subject.getPosition()));
		gui.setValueLabel(game.getValue(subject.getPosition()));
		gui.setRentLabel(game.getRent(subject.getPosition()));
		gui.setMoneyLabel(subject.getMoney());
		gui.setPlayerNameLabel(subject.getName());
		
		if (game.getPlayer(subject.getPosition()) == null){
			gui.setOwnerLabel("None");
		}else{
			gui.setOwnerLabel(game.getPlayer(subject.getPosition()).getName());
		}
	}


	/**
	 * Main method that stars the game.
	 */
	public static void main(String[] args) {
		ProjectMain p1 = new ProjectMain();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				p1.plName.setVisible(true);
				//p1.gui.setVisible(false);

				 }
		 	});
		p1.startGame();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
