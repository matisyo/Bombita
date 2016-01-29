package game.model.characters;

import game.model.Character;
import game.model.CharacterPlayer;
import game.model.CharacterStrategy;
import game.model.Position;
import game.model.bombs.BombMolotov;

public class Player implements CharacterFactory {

	@Override
	public Character create(Position position) {
		Character c = new CharacterPlayer(position, 100, false, 10, 5, new CharacterStrategy(), new BombMolotov().create());
		
		return c;
	}

}
