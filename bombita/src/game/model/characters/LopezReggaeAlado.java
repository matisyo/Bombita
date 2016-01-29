package game.model.characters;

import game.model.Character;
import game.model.CharacterLopezReggaeAlado;
import game.model.Position;
import game.model.AladoStrategy;
import game.model.bombs.BombMolotov;

public class LopezReggaeAlado implements CharacterFactory {
	public Character create(Position position){
		Character c = new CharacterLopezReggaeAlado(position, 100, true, 5, 10, new AladoStrategy(), new BombMolotov().create());
		
		return c;
	}
}
