package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
//note, not acutaly a circle anymore. Might want to rename class.
public class Circle extends Shape {

	private String print;

	public Circle(Point topLeft, int size, String print){
		super(topLeft, size);
		this.print = print;
	}
	
	public void draw(Graphics g){
	//Dimension d = 50;
    int fontSize = 20;

    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
     
    g.setColor(Color.red);
    
    g.drawString(print, getTopLeft().getXCoord(), getTopLeft().getYCoord());
		
	}
}
