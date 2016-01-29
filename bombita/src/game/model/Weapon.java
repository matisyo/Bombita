package game.model;

public interface Weapon extends CollidableElement{
	
	public Weapon cloneWeapon();
	public void prepareWeapon(Map map, Position position, Action lastCharacterAction);
	public void reduceDelay(int percentage);
}
