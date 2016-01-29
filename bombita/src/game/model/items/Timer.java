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

public class Timer implements CollidableElement, Item {

	private boolean active;
	
	public Timer() {
		this.active = true;
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
	
	@Override
	public void use(Character character) {
		character.getWeapon().reduceDelay(15);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serialize(Element parent) {
		parent.addElement("timer");
	}
	
	public static CollidableElement unserialize(Element element, Map map, Position position) {
		return new Timer();	
	}

}
