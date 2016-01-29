package game.model.blocks;

import game.model.Block;

public class Acero implements BlockFactory{
	public Block create(){
		Block b = new Block(1000, 1000, true);
		
		return b;
	}
}



