package game.model;

import org.dom4j.Element;

import game.model.Position;

public class Explosion implements CollidableElement {
	private Position position;
	private int destructionPower;
	private int delay;
	private long timer;

	private boolean solid;
	private boolean active;
	
	public Explosion (Position position, int destructionPower){
		this.active = true;
		this.position = position;
		this.timer = System.currentTimeMillis();
		this.destructionPower = destructionPower;
		this.delay = 500;
		this.solid = false;
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
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getDestructionPower() {
		return destructionPower;
	}

	public void setDestructionPower(int destructionPower) {
		this.destructionPower = destructionPower;
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
		bomb.setToExplode();
	}
	
	@Override
	public void collideWith(Proyectile proyectile) {
		//We do nothing		
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
//		block.collideWith(this);
	}

	@Override
	public void event(Map map) {
		if (System.currentTimeMillis() >= (this.timer + this.delay))
			this.setActive(false);
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}
	
	@Override
	public void collideWithAnother(CollidableElement element) {
		element.collideWith(this);
	}
	
	public Explosion cloneExplosion(){
		return (new Explosion(this.position, this.destructionPower));
	}

	@Override
	public void serialize(Element parent) {
		Element explosion = parent.addElement("explosion");
		explosion.addAttribute("destructionPower", Integer.toString(destructionPower));
		
	}

	public static CollidableElement unserialize(Element element, Map map, Position position) {
		int destructionPower = 0;
		
		if (element.attributeValue("destructionPower") != null)
			destructionPower = Integer.parseInt(element.attributeValue("destructionPower"));
		
		Explosion explosion = new Explosion(position, destructionPower);
		
		return explosion;	
	}

}
