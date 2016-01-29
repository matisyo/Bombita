package game.model;

import java.util.ArrayList;
import java.util.Iterator;

import game.model.Bomb;
import game.model.Map;
import game.model.Position;
import game.model.bombs.BombToleTole;
import junit.framework.TestCase;

public class testBomb extends TestCase {
	
	public void testReduceDelay() {
		Bomb bomb = new Bomb(1000, 1, 1);
		bomb.reduceDelay(15);
		
		assertEquals(bomb.getDelay(), 850);
	}
	
	public void testExplode(){
		Map map1 = new Map("data/tests/map_empty.xml");
		Bomb toletole1 = new BombToleTole().create();
		Position bombPosition = new Position(3,3);
		map1.addElement(bombPosition, toletole1);
		toletole1.setPosition(bombPosition);
		toletole1.explode(map1);
		
		ArrayList <Position> explosionPositions = new ArrayList <Position>();
		//X positions
		explosionPositions.add(new Position(0,3));
		explosionPositions.add(new Position(1,3));
		explosionPositions.add(new Position(2,3));
		explosionPositions.add(new Position(3,3));
		explosionPositions.add(new Position(4,3));
		explosionPositions.add(new Position(5,3));
		explosionPositions.add(new Position(6,3));
		explosionPositions.add(new Position(7,3));
		//Y positions
		explosionPositions.add(new Position(3,0));
		explosionPositions.add(new Position(3,1));
		explosionPositions.add(new Position(3,2));
		explosionPositions.add(new Position(3,4));
		explosionPositions.add(new Position(3,5));
		explosionPositions.add(new Position(3,6));
		explosionPositions.add(new Position(3,7));
		
		Iterator <Position> itr = explosionPositions.iterator();
		
		boolean ok = false;
		while (itr.hasNext()){
			ok = !(map1.positionIsEmpty((Position)itr.next()));
			
			if (!ok)
				break;
		}
		assertTrue(ok);	
		
	}

}
