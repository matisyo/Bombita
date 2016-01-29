package game.model.bombs;

import game.model.Bomb;

public class BombMolotov implements BombFactory {
	public Bomb create(){
		Bomb b = new Bomb(3000, 3, 5);
		
		return b;            
	}
}
