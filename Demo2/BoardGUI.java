import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class BoardGUI extends JFrame{
	
	/**
	 * instance variables for the picture displayed, the nextTurn button, the roll button, the exit button, the pass button, the player's name("nameLabel")label
	 * the player's money("moneyLabel")label, the property's rent("rentLable")label, the cost of property()label, property display("propertyLabel")label,
	*/
	private ImageIcon picture = new ImageIcon("Board.png");
	private JLabel boardImage = new JLabel(picture);
	private JTextField input = new JTextField(20);
	private JButton buy = new JButton ("Buy");
	private JButton exit = new JButton ("Exit");
	private JButton roll = new JButton ("Roll");
	private JButton pass = new JButton ("Pass");
	private JButton nextTrun = new JButton ("Next turn");
	private JLabel nameLabel = new JLabel ();
	private JLabel moneyLabel = new JLabel(); 
	private JLabel rentLabel = new JLabel();
	private JLabel propertyLabel = new JLabel ();


    public BoardGUI(ActionListener abc){
    	
    	/**
    	 * it set the size of the GUI and don't allow it to be resize, allows the GUI to be focused
    	*/
		super("Monopoly Game");
    	super.setFocusable(true);
		super.requestFocusInWindow();
		super.setSize(1200,1100);
		super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel screen = new JPanel();
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		/**
		 * it set where the the board picture to be placed
		 * and adds it to the JPanel called Screen
		*/
		boardImage.setBounds(0,0,900,900);
		boardImage.setVisible(true);
		screen.add(boardImage);
		
		/**
		 * it adds all the buttons created into the JPanel called Screen
		*/
		screen.add(nextTurnButton(abc));
		screen.add(buyButton(abc));
		screen.add(rollButton(abc));
		screen.add(exitButton(abc));
		screen.add(passButton(abc));

		JPanel informationPanel = new JPanel ();
		
		/**
		 * it sets all the JLabel created into the JPanel called informationPanel
		*/
		informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
		informationPanel.add(nameJLabel());
		informationPanel.add(rentJLabel());
		informationPanel.add(moneyJLabel());
		informationPanel.add(propertyJLabel());
		
		/**
		 * it sets Screen JPanel and informationPanel into  JPanel called container
		*/
		JPanel container = new JPanel ();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(screen);
		container.add(informationPanel);
		super.getContentPane().add(container);
		super.setVisible(true);
	}
    
    /**
	 * it creates the buttons made and calls for ActionListener for each individual button
	*/
    public JButton buyButton(ActionListener abc) {
		buy.setActionCommand("BUY");
		buy.addActionListener(abc);
		buy.setAlignmentX(Component.CENTER_ALIGNMENT);
		buy.setVisible(false);
		return buy;
    }
    
    public JButton exitButton(ActionListener abc) {
		exit.setActionCommand("EXIT");
		exit.addActionListener(abc);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		return exit;
    }
    
    public JButton rollButton (ActionListener abc) {
		roll.setActionCommand("ROLL DICE");
		roll.addActionListener(abc);
		roll.setAlignmentX(Component.CENTER_ALIGNMENT);
		roll.setVisible(false);
		return roll;
    }
    
    public JButton passButton (ActionListener abc) {
		pass.setActionCommand("PASS");
		pass.addActionListener(abc);
		pass.setAlignmentX(Component.CENTER_ALIGNMENT);
		pass.setVisible(false);
		return pass;
    }
    
    public JButton nextTurnButton (ActionListener abc) {
    	nextTrun.setActionCommand("NEXT TURN");
    	nextTrun.addActionListener(abc);
    	nextTrun.setAlignmentX(Component.CENTER_ALIGNMENT);
		return nextTrun;
    }
    
    /**
	 * it creates the JLables sets text for each JLabel initially
	*/
    public JLabel rentJLabel () {
    	rentLabel.setText("Rent for property: ");
    	return rentLabel;
    }
    
    public JLabel nameJLabel () {
    	nameLabel.setText("Player's name: ");
    	return nameLabel;
    }
    
    public JLabel moneyJLabel () {
    	moneyLabel.setText("Money: ");
    	return moneyLabel;
    }
    
    public JLabel propertyJLabel () {
    	propertyLabel.setText("Popertry name");
    	return propertyLabel;
    }

	public String getText() {
		return input.getText();
	}
	
	/**
	 * it creates sets visibility for each button
	*/
	public void setBuyButtonVisibility(boolean visible) {
		buy.setVisible(visible);
	}


	public void setRollButtonVisibility(boolean visible) {
		roll.setVisible(visible);
	}
	
	public void setPassButtonVisibility (boolean visible) {
		pass.setVisible(visible);
	}
	
	public void setNextButtonVisibility (boolean visible) {
		nextTrun.setVisible(visible);
	}
	
	/**
	 * it set the JLable for player piece of information to the updated information
	*/
	public void setNameLabel(String newName) {
		nameLabel.setText("Player: " + newName);
	}


	public void setMoneyLabel(int newMoney) {
		moneyLabel.setText("Money: " + newMoney);
	}


	public void setRentLabel(String newRent) {
		rentLabel.setText("Rent: " + newRent);
	}


	public void setPropertyLabel(String newProperty) {
		propertyLabel.setText("Propertry Name: " + newProperty);
	}
	
	/**
	 * the method checks if the button has been pressed
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
	 * the method checks if the buy button has been pressed
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


	public JButton getPass() {
		return pass;
	}

	

}
