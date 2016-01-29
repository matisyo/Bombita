package game.model;

import org.dom4j.Element;

public class Exit implements CollidableElement {
	private boolean pickedUp;

	private boolean solid;
	private boolean active;
	
	public Exit() {
		this.active = true;
		this.solid = true;
		this.pickedUp = false;
	}
	
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	
	public boolean isSolid() {
		return this.solid;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	
	
	public boolean isActive() {
		return this.active;
	}	
	
	public void pickUp() {
		this.pickedUp = true;
	}
	
	public boolean wasPickedUp() {
		return this.pickedUp;
	}
	
	@Override
	public void event(Map map) {
		// We do nothing
	}

	@Override
	public void collideWith(Character character) {
		character.collideWith(this);
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
	public void collideWith(Exit exit) {
		// We do nothing
	}

	@Override
	public void collideWith(Block block) {
		// We do nothing
	}

	@Override
	public void collideWithAnother(CollidableElement element) {
		element.collideWith(this);
	}

	@Override
	public void collideWith(Proyectile proyectile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serialize(Element parent) {
		Element exit = parent.addElement("exit");
		
		exit.addAttribute("solid", (this.solid? "true": "false"));
	}
	
	public static CollidableElement unserialize(Element element, Map map, Position position) {
		Exit exit = new Exit();
		if (element.attributeValue("solid") != null) {
			
			if (element.attributeValue("solid").equals("true"))
				exit.setSolid(true);
			else if (element.attributeValue("solid").equals("false"))
				exit.setSolid(false);
		}
		map.setExit(exit);
		
		return exit;	
	}
}
