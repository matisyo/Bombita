package game.model;

import java.util.ArrayList;
import java.util.Iterator;

import game.model.Block;
import game.model.Bomb;
import game.model.Cell;
import game.model.CollidableElement;
import game.model.Map;
import game.model.Position;
import game.model.bombs.BombToleTole;
import junit.framework.TestCase;

public class testIntegrations extends TestCase{
	public void testCharacterMove(){
		//Es un mapa con una bomba en el medio y bloques cerca.
		Map map1 = new Map ("data/tests/map_bomb_test.xml");
		Bomb toletole1 = new BombToleTole().create();
		Position bombPosition = new Position(3,3);
		map1.addElement(bombPosition, toletole1);
		toletole1.setPosition(bombPosition);
		toletole1.explode(map1);
		
		ArrayList <Position> destroyedBlockPositions = new ArrayList <Position>();
		ArrayList <Position> remainingBlockPositions = new ArrayList <Position>();
		//Blocks that aren't destroyed during the explosion
		remainingBlockPositions.add(new Position (3,0));
		remainingBlockPositions.add(new Position (3,6));
		remainingBlockPositions.add(new Position (6,3));
		remainingBlockPositions.add(new Position (0,3));
		//Destroyed ones
		destroyedBlockPositions.add(new Position (3,1));
		destroyedBlockPositions.add(new Position (3,5));
		destroyedBlockPositions.add(new Position (1,3));
		destroyedBlockPositions.add(new Position (5,3));
		
		//Run collisions
		Iterator<Cell> mapItr = map1.iterator();
		while(mapItr.hasNext()) {
			((Cell)mapItr.next()).events(map1);
		}
		
		Iterator<Position> itr = remainingBlockPositions.iterator();
		while (itr.hasNext()){
			assertFalse(map1.positionIsEmpty((Position)itr.next()));
		}
		itr = destroyedBlockPositions.iterator();
		while (itr.hasNext()){
			Position pos = ((Position)itr.next());
			ArrayList<CollidableElement> listOfElements = map1.positionGetElements(pos);
			Block block = (Block)listOfElements.get(0);
			assertTrue (block.isDestroyed());
		}
	}
	
	public void testLevel(){
		
		
	}
	

}
	