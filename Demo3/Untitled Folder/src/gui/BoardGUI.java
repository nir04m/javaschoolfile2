package gui;
import java.util.ArrayList;
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
	private ImageIcon diceBlank = new ImageIcon ("src/DiceImages/DiceBackground.png");
	private ImageIcon dice1 = new ImageIcon("src/DiceImages/Dice1.png");
	private ImageIcon dice2 = new ImageIcon("src/DiceImages/Dice2.png");
	private ImageIcon dice3 = new ImageIcon("src/DiceImages/Dice3.png");
	private ImageIcon dice4 = new ImageIcon("src/DiceImages/Dice4.png");
	private ImageIcon dice5 = new ImageIcon("src/DiceImages/Dice5.png");
	private ImageIcon dice6 = new ImageIcon("src/DiceImages/Dice6.png");
	private ImageIcon chanceImage = new ImageIcon ("src/Chance.png");
	private ImageIcon tableTop = new ImageIcon ("src/Let's_See.jpg");
	
	private JButton buy = new JButton ("Buy");
	private JButton exit = new JButton ("Exit game");
	private JButton roll = new JButton ();
	private JButton pass = new JButton ("Pass");
	private JButton nextTurn = new JButton ("Next turn");
	private JButton communityCard = new JButton ();
	
	private JLabel boardImage = new JLabel(picture);
	private JLabel tableLabel = new JLabel (tableTop);
	//private JLabel diceLabel = new JLabel (diceImage);
	private JLabel propertyNameLabel = new JLabel("Property name: ");
	private JLabel valueLabel = new JLabel("Value: ");
	private JLabel rentLabel = new JLabel("Rent: ");
	private JLabel ownerLabel= new JLabel("Owner: ");
	private JLabel playerNameLabel = new JLabel("Player name: ");
	private JLabel moneyLabel = new JLabel("Money: ");
	private JLabel chanceLabel = new JLabel();
	
	private JPanel gridPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel gameBoard = new JPanel();
	private JPanel informationPanel = new JPanel();
	private JPanel container = new JPanel ();
	private JPanel board = new JPanel ();
	private JPanel test = new JPanel();
	
	private  GridBagConstraints c = new GridBagConstraints();
	
	private static final int SCREEN_SIZE_WIDTH = 1200;
	private static final int SCREEN_SIZE_HEIGHT = 1100;
	private static final int BUTTON_SIZE_X = 220;
	private static final int BUTTON_SIZE_Y = 100;
	
	private JLayeredPane boardPane = new JLayeredPane();
	private JLayeredPane testPane = new JLayeredPane();
	
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	/**
	 * Constructor Creates the JFrame and adds components to the JFrame
	 * @param abc: the action listener needed for the JButtons
	 */
    public BoardGUI(ActionListener abc){
		super("Monopoly Game");
    	super.setFocusable(true);
		super.requestFocusInWindow();
		super.setSize(SCREEN_SIZE_WIDTH,SCREEN_SIZE_HEIGHT);
		super.setResizable(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		gameBoard.setLayout(new BoxLayout(gameBoard, BoxLayout.Y_AXIS));
		gameBoard.setPreferredSize(new Dimension(900, 900));
//		//gameBoard.add(boardPanel(abc));
//		testPane.setPreferredSize(new Dimension(900,900));
//		testPane.add(boardPanel(abc), new Integer (3));
//		testPane.add(gridStuff(abc), new Integer (2));
//		test.add(testPane);
		gameBoard.add(boardPanel(abc));
		gameBoard.add(gameButtons(abc));
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(gameBoard);
		container.add(playerPanel());
		container.setBackground(Color.lightGray);
		super.getContentPane().add(container);
		super.setVisible(true);
	}
	
	public void paint(Graphics canvas) {
        super.paint(canvas);

        for (Shape c : shapes) {
        	c.draw(canvas);
        }
    }
    
	public JPanel gridStuff (ActionListener abc) {
		JButton testButton = new JButton("Button 1");
		testButton.addActionListener(abc);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gridPanel.add(testButton, c);
		gridPanel.setLayout(new GridBagLayout());
		return gridPanel;
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
    public JPanel boardPanel(ActionListener abc) {
		boardImage.setBounds(0,0,900,900);
		boardImage.setVisible(true);
		boardPane.setPreferredSize(new Dimension(900,900));
		roll.setBounds(400, 200, 100, 100);
		communityCard.setBounds(600, 500, 100, 100);
		//board.setLayout(new GridLayout (8,8));
		boardPane.add(boardImage, new Integer(1));
		boardPane.add(rollButton(abc, false, 0), new Integer (2));
		boardPane.add(chanceButton(abc), new Integer(2));
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
		informationPanel.add(chanceLabel);
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
    public JButton rollButton (ActionListener abc, boolean actionListener, int rolledValue) {
		roll.setActionCommand("ROLL DICE");
		if (actionListener == false) {
			roll.removeActionListener(abc);
		}
		
		else if (actionListener == true) {
			roll.addActionListener(abc);
		}
		//roll.setIcon(diceBlank);
		setDiceLabel(rolledValue);
		roll.setAlignmentX(Component.CENTER_ALIGNMENT);
		roll.setPreferredSize(new Dimension (100, 100));
		//roll.setVisible(false);
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

    public JButton chanceButton(ActionListener abc) {
    	communityCard.setActionCommand("Chance");
		communityCard.addActionListener(abc);
		communityCard.setIcon(chanceImage);
		communityCard.setPreferredSize(new Dimension (100, 100));
		communityCard.setAlignmentX(Component.CENTER_ALIGNMENT);
		communityCard.setVisible(false);
		return communityCard;
    }
    
    /**
	 * Sets the visibility of the "Buy" button
	 * @param visible: a boolean for if to set the button to visible or not
	*/

	public void setBuyButtonVisibility(boolean visible) {
		buy.setVisible(visible);
	}
	
	
	public void setCommunityCardButtonVisibility(boolean visible) {
		communityCard.setVisible(visible);
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
		propertyNameLabel.setFont(new Font("Serif", Font.BOLD, 13));
	}

	/**
	 * Set the JLabel for the price to buy the property
	 * @param newPropertyValue: the price of the property the player is currently on
	 */
	public void setValueLabel(int newPropertyValue) {
		valueLabel.setText("Property value: " + newPropertyValue);
		valueLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel to display the current rent for the property
	 * @param propertyRent: the rent for the property
	 */
	public void setRentLabel(int propertyRent) {
		rentLabel.setText("Rent: " + propertyRent);
		rentLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel to which player owns the property
	 * @param proppertyOwner: name of the player who owns the property
	 */
	public void setOwnerLabel(String proppertyOwner) {
		ownerLabel.setText("Property owner : " + proppertyOwner);
		ownerLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel to the current player playing's name
	 * @param playerName: name of player playing currently
	 */
	public void setPlayerNameLabel(String playerName) {
		playerNameLabel.setText("Player: " + playerName);
		playerNameLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel or the amount of money for each player
	 * @param playerMoney: Players current money count
	 */
	public void setMoneyLabel(int playerMoney) {
		moneyLabel.setText("Money: " + playerMoney);
		moneyLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	
	public void setChanceLabel(String card) {
		chanceLabel.setText("Community card: " + "\n" + card);
		chanceLabel.setFont(new Font("Serif", Font.BOLD, 13));
	}
	
	public void setChanceLabel() {
		chanceLabel.setText(null);
	}
	
	/**
	 * Sets the JLabel to the value the player rolled
	 * @param rolledDice: the value the player rolled
	 */
	public void setDiceLabel (int rolledValue) {
		switch (rolledValue){
			case 0:	roll.setIcon(diceBlank);
					break;
		
			case 1: roll.setIcon(dice1);
					break;
			
			case 2: roll.setIcon(dice2);
					break;
			
			case 3: roll.setIcon(dice3);
					break;
			
			case 4: roll.setIcon(dice4);
					break;
			
			case 5: roll.setIcon(dice5);
					break;
			
			case 6: roll.setIcon(dice6);
					break;
			default:
					roll.setIcon(diceBlank);
					break;
		}
	}
	
	
	
	public void displayPropertyOwned (int position, int coord) {
		if (position >= 1 && position <= 6) {
			position = 765;
		}
		
		else if(position >= 8 && position <= 13) {
			position = 160;
		}
		
		else if (position >= 15 && position <= 20) {
			position = 125;
		}
		
		else if (position >= 22 && position <= 27) {
			position = 720;
		}
		
	}
	
	public void showPropertyOwned(int player, int position, int coord) {
		switch (player) {
		case 1:
			
		case 2:
			
		case 3:
			
		case 4:
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
