package game.model;

import org.dom4j.Element;

import game.model.actions.ActionMoveDown;
import game.model.actions.ActionMoveRight;
import game.model.actions.ActionMoveUp;
import game.model.bombs.BombProyectile;

public class Proyectile extends Bomb implements Movable, Weapon, CollidableElement{
	
	private Action action;
	private int speed;
	@SuppressWarnings("unused")
	private Map map; //No entiendo esta warning, si this.map lo uso en prepareWeapon! no lo usas, solo lo inicializas u.u
	private ProyectileStrategy strategy;
	private long timerSpeed;
	private boolean hasMoved;
	
	
	public Proyectile(int delay, int expansiveWave, int destructionPower, int speed) {
		super(delay, expansiveWave, destructionPower);
		this.strategy = new ProyectileStrategy();
		this.speed = speed;
		this.hasMoved = false;
		this.timerSpeed = System.currentTimeMillis();
	}
	
	public Position getPosition(){
		return this.position;
	}	

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
		
	}
	
	public Action getAction() {
		return this.action;
	}

	
	public boolean hasMoved(){
		return this.hasMoved;
		
	}

	@Override
	public CharacterStrategy getStrategy() {
		return this.strategy;
	}
	
	public Proyectile cloneWeapon(){
		Proyectile proyectile = new BombProyectile().create();
		return proyectile;
	}
	
	public void prepareWeapon(Map map, Position position, Action lastCharacterAction){
		this.action = lastCharacterAction;
		this.position = position;
		this.map = map;
	}

	
	public void event(Map map) {
		
		if (System.currentTimeMillis() >= (this.timer + this.delay)) {
			//checks the delay time and explodes.
			this.explode(map);
			this.setActive(false);
		}
			else if (System.currentTimeMillis() >= (this.timerSpeed + this.speed)) {
				
				this.strategy.doAction(map, this, this.action);
				this.timerSpeed = System.currentTimeMillis();
				this.hasMoved=true;
		}
	}
	
	public void collideWith(Block block) {
		block.collideWith(this);
	}
	
	public void collideWith(Character character) {
		character.collideWith(this);
	}
	
	public void collideWith(Explosion explosion){
		//We do nothing.
	}
	
	public void collideWith(Proyectile proyectile) {
		//We do nothing
	}
	
	@Override
	public void serialize(Element parent) {
		Element proyectile = parent.addElement("proyectile");
		
		proyectile.addAttribute("delay", Integer.toString(this.delay));
		proyectile.addAttribute("expansivewave", Integer.toString(this.expansiveWave));
		proyectile.addAttribute("destructionpower", Integer.toString(this.destructionPower));
		proyectile.addAttribute("speed", Integer.toString(this.speed));
		
		String action = "";
		
		if (this.action instanceof ActionMoveUp)
			action = "moveup";
		else if (this.action instanceof ActionMoveDown)
			action = "movedown";
		else if (this.action instanceof ActionMoveRight)
			action = "moveright";
		else
			action = "moveleft";	
			
		proyectile.addAttribute("action", action);
	}

	public static CollidableElement unserialize(Element element, Map map, Position position) {
		int delay = 0, expansiveWave = 0, destructionPower = 0, speed = 0;
		
		if (element.attributeValue("delay") != null)
			delay = Integer.parseInt(element.attributeValue("delay"));

		if (element.attributeValue("expansivewave") != null)
			expansiveWave = Integer.parseInt(element.attributeValue("expansivewave"));

		if (element.attributeValue("destructionpower") != null)
			destructionPower = Integer.parseInt(element.attributeValue("destructionpower"));

		if (element.attributeValue("speed") != null)
			speed = Integer.parseInt(element.attributeValue("speed"));
		
		Weapon proyectile = new Proyectile(delay, expansiveWave, destructionPower, speed);
		
		return proyectile;
	}
	
}
