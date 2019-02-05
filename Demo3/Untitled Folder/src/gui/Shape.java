package gui;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Shape {
	private Point topLeft = new Point(0,0);
	private int size = 50;

	public Shape(Point topLeft, int size){
		this.topLeft = topLeft;
		this.size = size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setTopLeft (Point topLeft){
		this.topLeft = topLeft;
	}

	public Point getTopLeft (){
		return topLeft;
	}
	
	public void moveDown(int amount) {
		topLeft.moveDown(amount);
	}
	
	public void moveUp(int amount) {
		topLeft.moveUp(amount);
	}
	
	public void moveLeft(int amount) {
		topLeft.moveLeft(amount);
	}
	
	public void moveRight(int amount) {
		topLeft.moveRight(amount);
	}
	
	abstract void draw(Graphics g);
}
