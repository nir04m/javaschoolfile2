import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class BoardGUI extends JFrame{
    /**
     *instance variables for the picture displayed, the nextTurn button, the roll button, the exit button, the pass button, the player's name("nameLabel")label
	 * the player's money("moneyLabel")label, the property's rent("rentLable")label, the cost of property()label, property display("propertyLabel")label,
    */
	private ImageIcon picture = new ImageIcon("src/Board.png");
	private ImageIcon diceImage = new ImageIcon ("src/DiceImages/DiceBackground.png");
	private ImageIcon dice1 = new ImageIcon("src/DiceImages/Dice1.png");
	private ImageIcon dice2 = new ImageIcon("src/DiceImages/Dice2.png");
	private ImageIcon dice3 = new ImageIcon("src/DiceImages/Dice3.png");
	private ImageIcon dice4 = new ImageIcon("src/DiceImages/Dice4.png");
	private ImageIcon dice5 = new ImageIcon("src/DiceImages/Dice5.png");
	private ImageIcon dice6 = new ImageIcon("src/DiceImages/Dice6.png");
	private ImageIcon tableTop = new ImageIcon ("src/Let's_See.jpg");
	
	private JButton buy = new JButton ("Buy");
	private JButton exit = new JButton ("Exit game");
	private JButton roll = new JButton ("Roll");
	private JButton pass = new JButton ("Pass");
	private JButton nextTurn = new JButton ("Next turn");
	
	private JLabel boardImage = new JLabel(picture);
	private JLabel tableLabel = new JLabel (tableTop);
	private JLabel diceLabel = new JLabel (diceImage);
	private JLabel propertyNameLabel = new JLabel("Property name: ");
	private JLabel valueLabel = new JLabel("Value: ");
	private JLabel rentLabel = new JLabel("Rent: ");
	private JLabel ownerLabel= new JLabel("Owner: ");
	private JLabel playerNameLabel = new JLabel("Player name: ");
	private JLabel moneyLabel = new JLabel("Money: ");
	
	private JPanel dicePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel gameBoard = new JPanel();
	private JPanel informationPanel = new JPanel();
	private JPanel container = new JPanel ();
	private JPanel board = new JPanel ();
	
	
	private static final int SCREEN_SIZE_WIDTH = 1200;
	private static final int SCREEN_SIZE_HEIGHT = 1100;
	private static final int BUTTON_SIZE_X = 220;
	private static final int BUTTON_SIZE_Y = 100;
	
	private JLayeredPane boardPane = new JLayeredPane();
	
	/**
	 * Constructor Creates the JFrame and adds components to the JFrame
	 * @param abc: the action listener needed for the JButtons
	 */
    public BoardGUI(ActionListener abc){
		super("Monopoly Game");
    	super.setFocusable(true);
		super.requestFocusInWindow();
		super.setSize(SCREEN_SIZE_WIDTH,SCREEN_SIZE_HEIGHT);
		super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameBoard.setLayout(new BoxLayout(gameBoard, BoxLayout.Y_AXIS));
		gameBoard.setPreferredSize(new Dimension(900, 950));
		gameBoard.add(boardPanel());
		gameBoard.add(gameButtons(abc));
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(gameBoard);
		container.add(playerPanel());
		container.setBackground(Color.lightGray);
		super.getContentPane().add(container);
		super.setVisible(true);
	}
	
    /**
     * Creates the JPanel for the buttons
     * adds all buttons to the panel
     * @param abc: the action listener needed for the JButtons
     * @return buttonPanel: JPanel with all the buttons added to it
     */
    public JPanel gameButtons(ActionListener abc) {
		buttonPanel.setLayout(new FlowLayout()); 
		buttonPanel.add(nextTurnButton(abc));
		buttonPanel.add(buyButton(abc));
		buttonPanel.add(rollButton(abc));
		buttonPanel.add(passButton(abc));
		buttonPanel.add(exitButton(abc));
		buttonPanel.setBackground(Color.lightGray);
		return buttonPanel;
    }
    
    /**
     * Creates the JPanel for the board image
     * Sets the bounds for the image
     * Adds the image to a JPanel
     * @return board: the jpanel with the board image
     */
    public JPanel boardPanel() {
		boardImage.setBounds(0,0,900,900);
		boardImage.setVisible(true);
		boardPane.setPreferredSize(new Dimension(900,900));
		diceLabel.setVisible(true);
		diceLabel.setBounds(400, 200, 100, 100);
		boardPane.add(boardImage, new Integer(0));
		boardPane.add(diceLabel, new Integer (1));
		board.add(boardPane);
		board.setPreferredSize(new Dimension (900, 900));
		board.setBackground(Color.lightGray);
    	return board;
    }
    
    /**
     * Creates the panel for the players current turn information
     * Adds JLabels as needed to display the necessary player attributes
     * @return: the JPanel with all the player attributes as necessary
     */
    public JPanel playerPanel () {
		informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
		informationPanel.add(playerNameLabel);
		informationPanel.add(propertyNameLabel);
		informationPanel.add(valueLabel);
		informationPanel.add(rentLabel);
		informationPanel.add(ownerLabel);
		informationPanel.add(moneyLabel);
		//informationPanel.add(diceLabel);
		informationPanel.setPreferredSize(new Dimension(300, 400));
		informationPanel.setBackground(Color.lightGray);
		return informationPanel;
    }
    
    /**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Buy button
	*/
    public JButton buyButton(ActionListener abc) {
		buy.setActionCommand("BUY");
		buy.addActionListener(abc);
		buy.setAlignmentX(Component.CENTER_ALIGNMENT);
		buy.setBackground(Color.pink);
		buy.setPreferredSize(new Dimension (BUTTON_SIZE_X, BUTTON_SIZE_Y));
		buy.setVisible(false);
		return buy;
    }

    /**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Exit button
	*/
    public JButton exitButton(ActionListener abc) {
		exit.setActionCommand("EXIT");
		exit.addActionListener(abc);
		exit.setBackground(Color.red);
		exit.setPreferredSize(new Dimension (BUTTON_SIZE_X, BUTTON_SIZE_Y));
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		return exit;
    }

    /**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Roll button
	*/
    public JButton rollButton (ActionListener abc) {
		roll.setActionCommand("ROLL DICE");
		roll.addActionListener(abc);
		roll.setBackground(Color.white);
		roll.setAlignmentX(Component.CENTER_ALIGNMENT);
		roll.setPreferredSize(new Dimension (BUTTON_SIZE_X, BUTTON_SIZE_Y));
		roll.setVisible(false);
		return roll;
    }

    /**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Pass button
	*/
    public JButton passButton (ActionListener abc) {
		pass.setActionCommand("PASS");
		pass.addActionListener(abc);
		pass.setBackground(Color.yellow);
		pass.setAlignmentX(Component.CENTER_ALIGNMENT);
		pass.setPreferredSize(new Dimension (BUTTON_SIZE_X, BUTTON_SIZE_Y));
		pass.setVisible(false);
		return pass;
    }

    /**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Next Turn button
	*/
    public JButton nextTurnButton (ActionListener abc) {
    	nextTurn.setActionCommand("NEXT TURN");
    	nextTurn.addActionListener(abc);
    	nextTurn.setBackground(Color.cyan);
    	nextTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
    	nextTurn.setPreferredSize(new Dimension (BUTTON_SIZE_X, BUTTON_SIZE_Y));
		return nextTurn;
    }

    
    
    /**
	 * Sets the visibility of the "Buy" button
	 * @param visible: a boolean for if to set the button to visible or not
	*/

	public void setBuyButtonVisibility(boolean visible) {
		buy.setVisible(visible);
	}

    /**
	 * Sets the visibility of the "Roll" button
	 * @param visible: a boolean for if to set the button to visible or not
	*/
	public void setRollButtonVisibility(boolean visible) {
		roll.setVisible(visible);
	}
	
    /**
	 * Sets the visibility of the "Pass" button
	 * @param visible: a boolean for if to set the button to visible or not
	*/
	public void setPassButtonVisibility (boolean visible) {
		pass.setVisible(visible);
	}
	
    /**
	 * Sets the visibility of the "Next turn" button
	 * @param visible: a boolean for if to set the button to visible or not
	*/
	public void setNextButtonVisibility (boolean visible) {
		nextTurn.setVisible(visible);
	}

    /**
	 * Sets the JLabel to display the current property name the player is on
	 * @param newPropertyName: the name of the property the player is on
	*/

	public void setPropertyNameLabel(String newPropertyName) {
		propertyNameLabel.setText("Property name: " + newPropertyName);
	}
	
	/**
	 * Set the JLabel for the price to buy the property
	 * @param newPropertyValue: the price of the property the player is currently on
	 */
	public void setValueLabel(int newPropertyValue) {
		valueLabel.setText("Property value: " + newPropertyValue);
	}
	
	/**
	 * Sets the JLabel to display the current rent for the property
	 * @param propertyRent: the rent for the property
	 */
	public void setRentLabel(int propertyRent) {
		rentLabel.setText("Rent: " + propertyRent);
	}
	
	/**
	 * Sets the JLabel to which player owns the property
	 * @param proppertyOwner: name of the player who owns the property
	 */
	public void setOwnerLabel(String proppertyOwner) {
		ownerLabel.setText("Property owner : " + proppertyOwner);
	}
		
	/**
	 * Sets the JLabel to the current player playing's name
	 * @param playerName: name of player playing currently
	 */
	public void setPlayerNameLabel(String playerName) {
		playerNameLabel.setText("Player: " + playerName);
		playerNameLabel.setFont(new Font("Serif", Font.BOLD, 30));
	}
	
	/**
	 * Sets the JLabel or the amount of money for each player
	 * @param playerMoney: Players current money count
	 */
	public void setMoneyLabel(int playerMoney) {
		moneyLabel.setText("Money: " + playerMoney);
	}
	

	
	/**
	 * Sets the JLabel to the value the player rolled
	 * @param rolledDice: the value the player rolled
	 */
	public void setDiceLabel (int rolledValue) {
		switch (rolledValue){
			case 1: diceLabel.setIcon(dice1);
					break;
			
			case 2: diceLabel.setIcon(dice2);
					break;
			
			case 3: diceLabel.setIcon(dice3);
					break;
			
			case 4: diceLabel.setIcon(dice4);
					break;
			
			case 5: diceLabel.setIcon(dice5);
					break;
			
			case 6: diceLabel.setIcon(dice6);
					break;
		}
	}
	
	
    /**
	 * Checks if the roll button has been pressed
	 * @return buttonPressed: returns true if the roll button is pressed
	*/
	public boolean getRollButtonPress () {
		boolean buttonPressed;
		if (buy.getModel().isPressed()) {
			buttonPressed = true;
		}
		else {
			buttonPressed = false;
		}
		return buttonPressed;
	}

    /**
	 * Checks if the buy button has been pressed
	 * @return buttonPressed: returns true if the roll button is pressed
	*/
	public boolean buyButtonPressed() {
		boolean pressed;
		if (buy.getModel().isPressed() == true) {
			pressed = true;
		}
		else {
			pressed = false;
		}
		return pressed;
	}
}
