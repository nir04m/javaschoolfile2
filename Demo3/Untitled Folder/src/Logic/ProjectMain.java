package Logic;
import java.util.ArrayList;
import java.util.Random;

import gui.BoardGUI;
import gui.Circle;
import gui.NameFrameGUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

public class ProjectMain implements ActionListener, MouseListener{
	private int iterate = 0;
	//Creating an object called game of the class board.
	private Board game = new Board();
	//Creating objects for each player of the class player.
	private ArrayList <Player> player = new <Player> ArrayList();	
	private Player p1 = new Player();
	private Player p2 = new Player();
	private Player p3 = new Player();
	private Player p4 = new Player();
	private Random dice = new Random();
	private BoardGUI gui = new BoardGUI(this);
	//instance variables set to private to avoid leaks.
	int currentPlayer = 0;
	private int valueRolled = 0;
	private boolean rollDie = false;
	private Player person;
	private NameFrameGUI plName= new NameFrameGUI(this);
	private int screenX = 0;
	private int screenY = 0;
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
		person = player.get(x);
		guiDisplay(person);
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
			player.add(p1);
			player.add(p2);
			player.add(p3);
			player.add(p4);
			plName.setVisible(false);
			gui.setVisible(true);
		}
		
		if (event.getActionCommand().equals("NEXT TURN")) {
			gui.setChanceLabel();
			if (iterate < 5) {
				gui.setNextButtonVisibility(false);
				gui.rollButton(this, true, valueRolled);
				rollDie = false;
				nextPlayer(iterate);
			}
			gameFinished();
		}
		if (event.getActionCommand().equals("BUY")){
			buyProperty();
			gui.setBuyButtonVisibility(false);
			gui.setPassButtonVisibility(false);
			gui.setNextButtonVisibility(true);
		}
		//when a player click or presses the roll button it iterates whose turn and calls the nextPlayer method  
		if (event.getActionCommand().equals("ROLL DICE")){
			rolledDie(person);
		}	
		if (event.getActionCommand().equals("EXIT")){
			System.exit(0);
			System.out.println("a");
		}
		if (event.getActionCommand().equals("PASS")){
			gui.setBuyButtonVisibility(false);
			gui.setPassButtonVisibility(false);
			//gui.setRollButtonVisibility(false);
			gui.setNextButtonVisibility(true);
		}
		
		if (event.getActionCommand().equals("Chance")) {
			gui.setChanceLabel(game.communityCard(player.get(currentPlayer)));
			guiDisplay(person);
			gui.setBuyButtonVisibility(false);
			gui.setPassButtonVisibility(false);
			gui.setNextButtonVisibility(true);
			gui.setCommunityCardButtonVisibility(false);
		}
	}

	/**
	 * rolledDie randomizes the value of the dice if a player chooses to roll.
	 * It also invokes the method move which moves the player according to the value displayed on the dice.
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */
	public void rolledDie (Player subject) {
		iterate++;
		this.valueRolled = dice.nextInt(6) +1;
		subject.move(11);
		guiDisplay(subject);
		gui.rollButton(this, false,valueRolled);
		System.out.println("Position: " + subject.getPosition());
		boolean buyAble = game.newTile(subject.getPosition(), subject);
		
		if (buyAble == true) {
			gui.setBuyButtonVisibility(true);
			gui.setPassButtonVisibility(true);

		}else if (buyAble == false) {
			boolean chance = game.communityCardCheck(subject.getPosition());
			if (chance == true) {
				gui.setCommunityCardButtonVisibility(true);
				gui.setNextButtonVisibility(false);
				guiDisplay(subject);
			}
			else {
				gui.setBuyButtonVisibility(false);
				gui.setPassButtonVisibility(false);
				gui.setNextButtonVisibility(true);
			}
		}
		buyAble = false;
		rollDie = false;
		guiDisplay(subject);
		if (iterate == 4) {
			iterate = 0;
		}
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
			Circle aCircle = new Circle(game.getPoint(subject.getPosition()), 50, game.getPlayer(subject.getPosition()).getName());
			aCircle.setSize(20);
			gui.shapes.add(aCircle);
			game.setPoint(-screenX, -screenY);
			Rectangle r = gui.getBounds();
			screenX = (r.width-1100)/2;
    		screenY = (r.height-1000)/2;
			game.setPoint(screenX, screenY);
			gui.repaint();
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
				p1.gui.setVisible(false);
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
