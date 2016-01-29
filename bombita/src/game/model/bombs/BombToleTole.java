package game.model.bombs;

import game.model.Bomb;

public class BombToleTole implements BombFactory {
	public Bomb create(){
		Bomb b = new Bomb(2000, 6, 1000);
		
		return b;            
	}
}
