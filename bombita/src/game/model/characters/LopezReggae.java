package game.model.characters;

import game.model.Character;
import game.model.CharacterLopezReggae;
import game.model.CharacterStrategy;
import game.model.Position;
import game.model.bombs.BombProyectile;

public class LopezReggae implements CharacterFactory {
	public Character create(Position position){
		Character c = new CharacterLopezReggae(position, 100, true, 10, 10, new CharacterStrategy(), new BombProyectile().create());
		
		return c;
	}
}
