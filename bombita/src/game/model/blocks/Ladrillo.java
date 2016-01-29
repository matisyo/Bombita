package game.model.blocks;

import game.model.Block;

public class Ladrillo implements BlockFactory{
	public Block create(){
		Block b = new Block(5, 5,false);
		
		return b;
	}
}
