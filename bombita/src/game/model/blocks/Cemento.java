package game.model.blocks;

import game.model.Block;

public class Cemento implements BlockFactory {
	public Block create(){
		Block b = new Block(10, 10, false);
		
		return b;
	}
}
