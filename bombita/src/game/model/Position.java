package game.model;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){
		String output = "X:";
		output += this.x;
		output += " Y: ";
		output += this.y;
		return output;
		
	}
	public void setPosition(Position position){
		this.x = position.getX();
		this.y = position .getY();		
	}
	
	public boolean equals(Position position) {
		return ((this.x == position.getX()) && (this.y == position.getY()));
	}
	
	public void add(Position position) {
		this.x = this.x + position.getX();
		this.y = this.y + position.getY();
	}
}
