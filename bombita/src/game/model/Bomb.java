package game.model;

import org.dom4j.Element;


public class Bomb implements CollidableElement, Weapon {

	protected int delay; // The number of milliseconds to wait for the bomb to explode
	protected long timer;
	protected int expansiveWave;
	protected int destructionPower;

	protected Position position; //Its protected bcs Proyectile.explode() uses it.

	private boolean active;
	
	public Bomb(int delay, int expansiveWave, int destructionPower) {
		this.active = true;
		this.timer = System.currentTimeMillis();
		this.delay = delay;
		this.expansiveWave = expansiveWave;
		this.destructionPower = destructionPower;
	}
	
	/**************************** GETTERS AND SETTERS **********************************************/
	
	public boolean isSolid() {
		return true;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	
	
	public boolean isActive() {
		return this.active;
	}	
	
	public void setPosition(Position pos){
		this.position = pos;	
	}
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getExpansiveWave() {
		return expansiveWave;
	}

	public void setExpansiveWave(int expansiveWave) {
		this.expansiveWave = expansiveWave;
	}

	public int getDestructionPower() {
		return destructionPower;
	}

	public void setDestructionPower(int destructionPower) {
		this.destructionPower = destructionPower;
	}

	/**************************** WEAPON **********************************************/
	
	public void prepareWeapon(Map map, Position position, Action lastCharacterAction){
		this.position = position;		
	}
	
	
	public Bomb cloneWeapon(){
		Bomb clonedBomb = new Bomb(this.delay, this.expansiveWave, this.destructionPower);
		return clonedBomb;
	}
	
	/**************************************************************************/
	
	public void explode(Map map){
		createExplosions(map);
	}
	
	public void createExplosions(Map map){
		map.addElement(this.position, new Explosion (this.position, this.destructionPower));
		this.createExplosions(map, this.position.getX(), this.position.getY(), 1, 0);
		this.createExplosions(map, this.position.getX(), this.position.getY(), 0, 1);
		this.createExplosions(map, this.position.getX(), this.position.getY(), -1, 0);
		this.createExplosions(map, this.position.getX(), this.position.getY(), 0, -1);
	}
	
	private void createExplosions(Map map, int x, int y, int timesX, int timesY){
		int i = 1;
		boolean occupiedCell;
		while (i < this.expansiveWave){
			Position explosionPosition = new Position(x + (timesX * i), y + (timesY * i));
			
			if (map.positionIsValid(explosionPosition)){
				Explosion explotion = new Explosion(explosionPosition, this.destructionPower);
				map.addElement(explosionPosition, explotion);
				occupiedCell = map.positionIsSolid(explosionPosition);
			} else {
				occupiedCell = true;
			}
		
			if (occupiedCell)
				break;
			
			i++;
		}
	}

	public void reduceDelay(int percentage) {
		this.delay = (this.delay * (100 - percentage)) / 100;
	}
	
	/**************************** COLLISIONS **********************************************/
	
	@Override
	public void collideWith(Character character) {
		// we do nothing
	}

	@Override
	public void collideWith(Item item) {
		// we do nothing
	}

	@Override
	public void collideWith(Bomb bomb) {
		// we do nothing
	}

	@Override
	public void collideWith(Explosion explosion) {
		explosion.collideWith(this);
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
	public void collideWith(Proyectile proyectile) {
		proyectile.setToExplode();
	}
	
	public void setToExplode() {
		this.setDelay(0);
	}

	@Override
	public void event(Map map) {
		if (System.currentTimeMillis() >= (this.timer + this.delay)) {
			this.explode(map);
			this.setActive(false);
		}
	}

	@Override
	public void collideWithAnother(CollidableElement element) {
		element.collideWith(this);
	}

	/**************************** LOAD SAVE AND SERIALIZE **********************************************/
	@Override
	public void serialize(Element parent) {
		Element bomb = parent.addElement("bomb");
		
		bomb.addAttribute("delay", Integer.toString(this.delay));
		bomb.addAttribute("expansivewave", Integer.toString(this.expansiveWave));
		bomb.addAttribute("destructionpower", Integer.toString(this.destructionPower));	
	}
	
	public static CollidableElement unserialize(Element element, Map map, Position position) {
		int delay = 0, expansiveWave = 0, destructionPower = 0;
		
		if (element.attributeValue("delay") != null)
			delay = Integer.parseInt(element.attributeValue("delay"));
		
		if (element.attributeValue("expansivewave") != null)
			expansiveWave = Integer.parseInt(element.attributeValue("expansivewave"));

		if (element.attributeValue("destructionpower") != null)
			destructionPower = Integer.parseInt(element.attributeValue("destructionpower"));    		
		
		Bomb bomb = new Bomb(delay, expansiveWave, destructionPower);
		bomb.setPosition(position);
		
		return bomb;
	}	
	
}
