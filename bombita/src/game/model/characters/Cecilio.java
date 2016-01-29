package game.model.characters;

import game.model.Character;
import game.model.CharacterCecilio;
import game.model.CharacterStrategy;
import game.model.Position;
import game.model.bombs.BombMolotov;

public class Cecilio implements CharacterFactory {
	public Character create(Position position){	
		Character c = new CharacterCecilio(position, 100, true, 5, 10, new CharacterStrategy(), new BombMolotov().create());
		
		return c;
	}
}

