package game.model;

import game.model.blocks.Acero;
import game.model.blocks.Cemento;
import game.model.blocks.Ladrillo;

import org.dom4j.Element;

public class Block implements CollidableElement {
	private int originalHealth;
	private int health;
	
	private boolean solid;
	private boolean active;
	private boolean indestructible;
	
	public Block(int originalHealth, int health, boolean indestructible) {
		this.active = true;
		this.solid = true;
		this.originalHealth = originalHealth;
		this.health = health;
		this.indestructible = indestructible;
	}
	
	/**************************** GETTERS AND SETTERS **********************************************/

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

	public int getOriginalHealth() {
		return originalHealth;
	}

	public void setOriginalHealth(int originalHealth) {
		this.originalHealth = originalHealth;
	}	
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isDestroyed() {
		return (this.health <= 0);
	}
	
	public boolean isIndestructible() {
		return indestructible;
	}
	
	/**************************** COLLISIONS **********************************************/

	@Override
	public void collideWith(Character character) {
		// We do nothing
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
	public void collideWith(Proyectile proyectile) {
		proyectile.setToExplode();
	}
	
	@Override
	public void collideWith(Explosion explosion) {
		// We damage the block with the power of the explosion
		if((!this.indestructible) || ((this.indestructible) && (explosion.getDestructionPower() == this.health))){
			this.health = this.health - explosion.getDestructionPower();
		}
			
		
		// Explosion deals damage only once 
		explosion.setDestructionPower(0);
		
		if (this.isDestroyed())
			this.setActive(false);
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
	
	/***********************************************************************************************/
	
	@Override
	public void event(Map map) {
		// The block does nothing
	}

	/**************************** LOAD SAVE AND SERIALIZE **********************************************/
	
	@Override
	public void serialize(Element parent) {
		Element block = parent.addElement("block");
		String type = "";
		
		if (this.originalHealth == new Acero().create().getOriginalHealth())
			type = "acero";
		else if (this.originalHealth == new Cemento().create().getOriginalHealth())
			type = "cemento";
		else if (this.originalHealth == new Ladrillo().create().getOriginalHealth())
			type = "ladrillo";
		
		block.addAttribute("type", type);
		
		if (this.health != this.originalHealth)
			block.addAttribute("health", Integer.toString(this.health));
	}
	
	public static CollidableElement unserialize(Element element, Map map, Position position) {
		String type = element.attributeValue("type");
		Block block;
		
		if (type.equals("acero"))
			block = new Acero().create();
		else if (type.equals("cemento"))
			block = new Cemento().create();
		else
			block = new Ladrillo().create();        		
		
		if (element.attributeValue("health") != null) {
			int health = Integer.parseInt(element.attributeValue("health"));
			block.setHealth(health);
		}
		
		return block;
	}

		
}
