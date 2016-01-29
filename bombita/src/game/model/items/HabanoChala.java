package game.model.items;

import org.dom4j.Element;

import game.model.Block;
import game.model.Bomb;
import game.model.Character;
import game.model.CollidableElement;
import game.model.Exit;
import game.model.Explosion;
import game.model.Item;
import game.model.Map;
import game.model.Position;
import game.model.Proyectile;

public class HabanoChala implements CollidableElement, Item {
	private int speedPercentage;

	private boolean active;
	
	public HabanoChala(){
		this.setActive(true);
		this.speedPercentage = 15;
	}

	public boolean isSolid() {
		return false;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	
	
	public boolean isActive() {
		return this.active;
	}
	
	public int getSpeedPercentage() {
		return speedPercentage;
	}

	public void setSpeedPercentage(int speedPercentage) {
		this.speedPercentage = speedPercentage;
	}

	public void use(Character character) {
		int speed = (character.getSpeed() * (100 - this.speedPercentage)) / 100;
		character.setSpeed(speed);
		
		this.setActive(false);
	}

	@Override
	public void collideWith(Character character) {
//		character.collideWith((Item) this);
	}

	@Override
	public void collideWith(Item item) {
		// We do nothing	
	}

	@Override
	public void collideWith(Bomb bomb) {
		// We do nothing	
	}

	@Override
	public void collideWith(Explosion explosion) {
		// We do nothing
	}

	@Override
	public void event(Map map) {
		// We do nothing	
	}

	@Override
	public void collideWith(Exit exit) {
		// We do nothing
	}

	@Override
	public void collideWith(Block block) {
		// We do nothing
	}

	@Override
	public void collideWithAnother(CollidableElement element) {
		element.collideWith((Item) this);
	}

	@Override
	public void collideWith(Proyectile proyectile) {
		// We do nothing
	}

	@Override
	public void serialize(Element parent) {
		parent.addElement("habanochala");
	}
	
	public static CollidableElement unserialize(Element element, Map map, Position position) {
		return new HabanoChala();	
	}
	
}
