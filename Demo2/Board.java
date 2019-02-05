import java.util.*;

public class Board{
	
	//Inialize game board list
	private Player [] gameBoard = new Player [28];
	//Inialize and set arraylists 
	private ArrayList <Integer> rent = new ArrayList<Integer> (Arrays.asList(0, 5, 5, 200, 100, 10, 15, 0, 20, 20, 25, 100, 30, 35, 100, 40, 40, 45, 100, 100, 120, 300, 150, 170, 100, 200, 200, 220));
	private ArrayList <Integer> value = new ArrayList <Integer> (Arrays.asList(0, 60, 70, 200, 200, 100, 150, 0, 170, 170, 190, 200, 210, 220, 0, 240, 240, 260, 200, 280, 300, 300, 320, 340, 200, 0, 350, 400));
	private ArrayList <String> propertyNames = new ArrayList <String> (Arrays.asList("Go" , " Meditranian Ave.", "Baltic Ave.", "INCOME TAX", "Railroad 1", "Oriental Ave.", "Vermont Ave", 
	"Just visiting jail", "St. Charels", "States Ave", "Virginia ave", "Railroad 2", "St. James", "NYC ave", "Free Parking", "Kentucky ave", "Indiana ave", "Illinos ave", "Railroad 3", "Atlantic ave", 
	"Ventnor ave", "Jail", "Pacific ave", "Carolina ave", "Railroad 4", "Luxary Tax", "Park place", "BoardWalk"));
	//instance varibales
	private Random newEvent = new Random();
	
	
	public void newGameBoard(){
		for (int x = 0; x < gameBoard.length; x++){
			gameBoard[x] = null;
		}
	}
	
	public int getRent(int property){
		int x = rent.get(property);
		return x;
	}
	
	public String getPropertyName (int position){
		String name = propertyNames.get(position);
		return name;
	}
	
	public int getValue (int position){
		int amount = value.get(position);
		return amount;
	}
	
	public Player getPlayer (int position){
		return gameBoard[position];	
	}
	
	public boolean newTile(int position, Player subject){ 
		boolean buyAble;
		if (position == 0 || position == 7 ||  position == 14|| position == 21 || position == 3 || position == 25 ||position == 11 ||position ==25){
			if (position == 3 || position == 25 || position == 21 || position == 14){
				speicalTile(position, subject);
			}
			else if(position == 11 || position == 25){
				int value = newEvent.nextInt(5) +1;
				communityCard(value,subject);
			}
			buyAble = false;
		}
		
		else if (gameBoard[position] == null){
			buyAble = true;
		}
		else{
			subject.takeMoney(rent.get(position));
			gameBoard[position].addMoney(rent.get(position));
			buyAble = false;
		}
		return buyAble;
		 
	}
	
	public void speicalTile(int position, Player subject){
		if (position == 14){
			subject.addMoney(rent.get(position));
		}
		else{
			subject.takeMoney(rent.get(position));
		}
	}
	
	public void buy(int position, Player subject){
		gameBoard[position] = subject;
		subject.takeMoney(value.get(position));
		
	}
	
	public void communityCard(int x,Player subject){
		if(x ==1){
			System.out.println("move 3 tile backward");
			subject.move(-3);
			System.out.println("your new position is "+subject.getPosition());
		}
		if(x ==2){
			System.out.println("go to jail");
			subject.setPosition(21);
			System.out.println("your new position is "+subject.getPosition());
		}
		if(x ==3){
			System.out.println("move 2 tile forward");
			subject.move(2);
			System.out.println("your new position is "+subject.getPosition());
		}
		if(x ==4){
			System.out.println("move to GO");
			subject.setPosition(0);
			System.out.println("your new position is "+subject.getPosition());
		}
		if(x ==5){
			System.out.println("the bank gave you 200");
			subject.addMoney(200);
		}
	}

}