package game.model;

public interface CollidableElement extends SerializableElement {

	public boolean isSolid();
	
	public boolean isActive();
	
	public void event(Map map);
	
	public void collideWithAnother(CollidableElement element);
	
	public void collideWith(Character character);
	public void collideWith(Item item);
	public void collideWith(Bomb bomb);
	public void collideWith(Explosion explosion);
	public void collideWith(Exit exit);
	public void collideWith(Block block);	
	public void collideWith(Proyectile proyectile);
}
