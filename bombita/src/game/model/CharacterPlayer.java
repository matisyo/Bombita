package game.model;

public class CharacterPlayer extends Character{

	public CharacterPlayer(Position position, int speed, boolean enemy,	int health, int contactDamage, CharacterStrategy strategy, Weapon weapon) {
		super(position, speed, enemy, health, contactDamage, strategy, weapon);
	}

	@Override
	public void collideWith(Item item) {
		item.use(this);
	}
}
