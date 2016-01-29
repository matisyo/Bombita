package game.model.characters;

import game.model.Character;
import game.model.Position;

public interface CharacterFactory {
	public Character create(Position position);

}
