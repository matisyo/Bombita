package game.model;

import java.util.ArrayList;
import java.util.Iterator;

import game.model.Cell;
import game.model.CollidableElement;
import game.model.Map;
import game.model.Position;
import game.model.blocks.Cemento;
import game.model.items.HabanoChala;

import junit.framework.TestCase;

public class testMap extends TestCase {
	
	public void testMapIteratorNumberOfElements(){	
		Map map1 = new Map("data/tests/map_with_one_block_sized_10.xml");
		
		Iterator<Cell> itr = map1.iterator();
		int i = 0;
		while(itr.hasNext()){
			itr.next();
			i++;
		}
		
		assertEquals(i, 100);
	}
	
	public void testMapEmpty(){	
		Map map1 = new Map("data/tests/map_empty.xml");
		
		boolean ok = true;
		
		Iterator<Cell> itr = map1.iterator();
		while(itr.hasNext()){
			if (!((Cell) itr.next()).isEmpty()) {
				ok = false;
				break;
			}
		}
		
		assertTrue(ok);
	}

	public void testMapNotEmpty(){
		Map map1 = new Map("data/tests/map_with_one_block.xml");
		
		assertFalse(map1.positionIsEmpty(new Position(0,0)));
	}
	
	public void testMapAddItem(){
		Map map1 = new Map("data/tests/map_with_one_block.xml");
		ArrayList<CollidableElement> items = new ArrayList<CollidableElement>();
		HabanoChala cig = new HabanoChala();
		items.add(cig);
		map1.addItems(items);
		//The cell has to have two elements, one block and one item.
		assertEquals(map1.positionGetElements(new Position(0,0)).size(), 2);
	}
	
	public void testMapAddItemII(){
		//A map with one block on the 0,0 position but sized 10x10
		Map map1 = new Map("data/tests/map_with_one_block_sized_10.xml");
		ArrayList<CollidableElement> items = new ArrayList<CollidableElement>();
		HabanoChala cig = new HabanoChala();
		items.add(cig);
		map1.addItems(items);
		//The cell has to have two elements, one block and one item.
		assertEquals(map1.positionGetElements(new Position(0,0)).size(), 2);
	}
	
	public void testMapAddItemIII(){
		Map map1 = new Map("data/tests/map_with_two_cells.xml");
		
		ArrayList<CollidableElement> items = new ArrayList<CollidableElement>();
		HabanoChala cig1 = new HabanoChala();
		HabanoChala cig2 = new HabanoChala();
		items.add(cig1);
		items.add(cig2);
		map1.addItems(items);

		//The cell has to have two elements, one block and one item.
		boolean correct = ((map1.positionGetElements(new Position(0,0)).size() == 2) && (map1.positionGetElements(new Position(1,0)).size() == 2));
		assertTrue(correct);
	}
	
	public void testMapAddItemIV(){
		Map map1 = new Map("data/tests/map_with_one_block.xml");
		ArrayList<CollidableElement> items = new ArrayList<CollidableElement>();
		HabanoChala cig1 = new HabanoChala();
		HabanoChala cig2 = new HabanoChala();
		items.add(cig1);
		items.add(cig2);
		map1.addItems(items);
		//The cell has to have two elements, one block and one item.
		assertEquals(map1.positionGetElements(new Position(0,0)).size(), 2);
	}
	
	public void testPositionIsInValidI() {
		Map map1 = new Map("data/tests/map_with_one_block.xml");
		
		assertFalse(map1.positionIsValid(new Position(-10, -10)));
	}

	public void testPositionIsInValidII() {
		Map map1 = new Map("data/tests/map_with_one_block.xml");
		
		assertFalse(map1.positionIsValid(new Position(1000, 1000)));
	}
	
	public void testPositionIsInValid() {
		Map map1 = new Map("data/tests/map_with_one_block.xml");
		
		assertTrue(map1.positionIsValid(new Position(0, 0)));
	}
	
	public void testBlockIsCemento() {	
		Map map = new Map("data/tests/map_with_one_block_big.xml");
		
		ArrayList<CollidableElement> elements = map.positionGetElements(new Position(1, 0));
		
		assertEquals(((Block) elements.get(0)).getOriginalHealth(), (new Cemento().create()).getOriginalHealth());
	}
	
	public void testCellIsEmpty() {	
		Map map = new Map("data/tests/map_with_one_block_big.xml");
		
		assertTrue(map.positionIsEmpty(new Position(0, 0)));
	}	
}
