package game.model;

public interface Movable extends ActionUser{
	
	public int getSpeed();
	public void setSpeed(int speed);
	public CharacterStrategy getStrategy();
	public Position getPosition();
	public void setPosition(Position newPosition);

}
