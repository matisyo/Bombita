package game.model;

import java.util.Iterator;

import org.dom4j.Element;

import game.model.actions.*;
import game.model.characters.Cecilio;
import game.model.characters.LopezReggae;
import game.model.characters.LopezReggaeAlado;
import game.model.characters.Player;
import game.model.direction.*;

public class Character implements CollidableElement, Movable, Attacker {
	
	private Position position;
	private int speed;
	private boolean enemy;
	private int health;
	private int contactDamage;
	private CharacterStrategy strategy;
	private Weapon weapon;
	private Action action;
	private long timerSpeed;
	private Direction priorDirection; //previous action the character did  


	private boolean active;	
	
	public Character(Position position, int speed, boolean enemy, int health, int contactDamage, CharacterStrategy strategy, Weapon weapon) {
		this.active = true;
		this.position = position;
		this.speed = speed;
		this.enemy = enemy;
		this.health = health;
		this.contactDamage = contactDamage;
		this.strategy = strategy;
		this.weapon = weapon;
		this.action = new ActionDoNothing();
		this.timerSpeed = System.currentTimeMillis();
		this.priorDirection = new DirectionLeft(); //Sets the standard direction.
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

	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}	
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isEnemy() {
		return enemy;
	}
	
	public void isEnemy(boolean enemy) {
		this.enemy = enemy;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}	
	
	public int getContactDamage() {
		return contactDamage;
	}

	public void setContactDamage(int contactDamage) {
		this.contactDamage = contactDamage;
	}

	public CharacterStrategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(CharacterStrategy strategy) {
		this.strategy = strategy;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void setAction(Action action){
		this.action = action;
	}	
	
	public void plantBomb(Map map){
		Position pos = new Position(this.position.getX(), this.position.getY());
		if (!map.positionIsSolid(pos)){
			Weapon weaponToUse = this.weapon.cloneWeapon();
			weaponToUse.prepareWeapon(map, this.position, this.strategy.lastAction());
			map.addElement(this.position, weaponToUse);	
		}
	}
	
	public boolean isDestroyed() {
		return (this.health <= 0);
	}	
	
	@Override
	public void collideWith(Character character) {		
		if ((!this.isEnemy()) && (character.isEnemy()))
			this.health = this.health - character.getContactDamage();

		if (this.isDestroyed())
			this.setActive(false);
	}

	@Override
	public void collideWith(Item item) {
		// Only the player can use items
	}

	@Override
	public void collideWith(Bomb bomb) {
		// We do nothing
	}
	
	public void collideWith(Proyectile proyectile){
		if (proyectile.hasMoved())
			//This IF statement fixes character suicide. When a character launches a proyectile,
			//both character and proyectile are in the same cell, but it wont explode because
			//the proyectile hasnt moved yet.
			proyectile.setToExplode();
	}

	@Override
	public void collideWith(Explosion explosion) {
		// We damage the character with the power of the explosion
		
		this.health = this.health - explosion.getDestructionPower();

		if (this.isDestroyed())
			this.setActive(false);
		
	}

	@Override
	public void collideWith(Exit exit) {
		if (!(this.isEnemy()))
			exit.pickUp();
	}

	@Override
	public void collideWith(Block block) {
		// We do nothing
	}
	
	@Override
	public void event(Map map) {
		
		if (System.currentTimeMillis() >= (this.timerSpeed + this.speed)) {
			this.strategy.doAction(map, this, this.action);
			Direction dir = this.strategy.lastDirection();
			if(dir != null)
				this.priorDirection = dir; //after doing the Action, sets the priorMoveAction			
			this.action = new ActionDoNothing();
			this.timerSpeed = System.currentTimeMillis();
		}
	}

	@Override
	public void collideWithAnother(CollidableElement element) {
		element.collideWith(this);
	}

	@Override
	public void serialize(Element parent) {
		Element character = parent.addElement("character");
		
		if (this instanceof CharacterPlayer)
			character.addAttribute("type", "player");
		else if (this instanceof CharacterCecilio)
			character.addAttribute("type", "cecilio");
		else if (this instanceof CharacterLopezReggae)
			character.addAttribute("type", "lopezreggae");
		else if (this instanceof CharacterLopezReggaeAlado)
			character.addAttribute("type", "lopezreggaealado");
		
		character.addAttribute("speed", Integer.toString(this.speed));
		character.addAttribute("health", Integer.toString(this.health));
		character.addAttribute("contactDamage", Integer.toString(this.contactDamage));
		
		this.weapon.serialize(character);
	
	}
	
	public static CollidableElement unserialize(Element element, Map map, Position position) {
		Character character;
		
		if (element.attributeValue("type").equals("player"))
			character = new Player().create(position);
		else if (element.attributeValue("type").equals("cecilio"))
			character = new Cecilio().create(position);
		else if (element.attributeValue("type").equals("lopezreggae"))
			character = new LopezReggae().create(position);
		else // (element.attributeValue("type").equals("lopezreggaealado"))
			character = new LopezReggaeAlado().create(position);    			
			
		if (element.attributeValue("speed") != null)
			character.setSpeed(Integer.parseInt(element.attributeValue("speed")));
		
		if (element.attributeValue("health") != null)
			character.setHealth(Integer.parseInt(element.attributeValue("health")));
		
		if (element.attributeValue("contactDamage") != null)
			character.setContactDamage(Integer.parseInt(element.attributeValue("contactDamage")));
		
		for (Iterator<Element> k = element.elementIterator(); k.hasNext();) {
			Element elementCharacter = (Element) k.next();
			if (elementCharacter.getName().equals("bomb"))
	    		character.setWeapon((Weapon) Bomb.unserialize(elementCharacter, map, position));
			else if (elementCharacter.getName().equals("proyectile"))
	    		character.setWeapon((Weapon) Proyectile.unserialize(elementCharacter, map, position));
		}
		
		map.addCharacter(character);
		
		return character;
	}	
}
