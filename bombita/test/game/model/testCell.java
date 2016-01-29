package game.model;

import game.model.Cell;
import game.model.Character;
import game.model.Explosion;
import game.model.Map;
import game.model.Position;
import game.model.blocks.Ladrillo;
import game.model.characters.LopezReggae;
import game.model.characters.Player;
import game.model.items.HabanoChala;
import game.model.items.Timer;
import junit.framework.TestCase;

public class testCell extends TestCase {
	
	public void testCellIsEmpty() {
		Cell cell = new Cell(new Position(0,0));
		
		assertTrue(cell.isEmpty());
	}	
	
	public void testCellIsNotEmpty() {
		Cell cell = new Cell(new Position(0,0));
		cell.addElement(new Ladrillo().create());
		
		assertFalse(cell.isEmpty());
	}
	
	public void testCellWithBlockIsSolid() {
		Cell cell = new Cell(new Position(0,0));
		cell.addElement(new Ladrillo().create());
		
		assertTrue(cell.isSolid());
	}
	
	public void testCellWithExplosionIsNotSolid() {
		Cell cell = new Cell(new Position(0,0));
		cell.addElement(new Explosion(new Position(0,0), 1000));
		
		assertFalse(cell.isSolid());
	}
	
	public void testCellWithItemIsNotSolid() {
		Cell cell = new Cell(new Position(0,0));
		cell.addElement(new HabanoChala());
		
		assertFalse(cell.isSolid());
	}	
	
	public void testCellWithItemAndCharacterIsNotSolid() {
		Cell cell = new Cell(new Position(0,0));
		cell.addElement(new HabanoChala());
		cell.addElement(new Player().create(new Position(0,0)));
		
		assertFalse(cell.isSolid());
	}
	
	public void testCellEmptyIsNotSolid() {
		Cell cell = new Cell(new Position(0,0));
		
		assertFalse(cell.isSolid());
	}
	
	public void testAddTwoElements() {
		Cell cell = new Cell(new Position(0,0));
		cell.addElement(new HabanoChala());
		cell.addElement(new Player().create(new Position(0,0)));
		
		assertEquals(cell.getElements().size(), 2);
	}
	
	public void testEventsBetweenPlayerAndItemTimer() {
		Map map = new Map("data/tests/map_empty.xml");
		
		Character player = new Player().create(new Position(0, 0));
		
		map.addElement(new Position(0,0),player);
		map.addElement(new Position(0,0), new Timer());
		
		map.runEvents(new Events());
		
		
		assertEquals(((Bomb)player.getWeapon()).getDelay(), 2550);
	}	
	

	public void testEventsBetweenPlayers() {
		Map map = new Map("data/tests/map_empty.xml");

		Character player1 = new Player().create(new Position(0,0));
		Character player2 = new Player().create(new Position(0,0));
		
		map.addElement(new Position(0,0), player1);
		map.addElement(new Position(0,0), player2);
		
		map.runEvents(new Events());
		
		assertTrue(player1.isActive());
	}
	
	public void testEventsBetweenPlayerAndEnemy() {
		Map map = new Map("data/tests/map_empty.xml");
		
		Character player1 = new Player().create(new Position(0,0));
		Character lopezReggae = new LopezReggae().create(new Position(0,0));
		
		map.addElement(new Position(0,0), player1);
		map.addElement(new Position(0,0), lopezReggae);	
		
		map.runEvents(new Events());
		
		assertTrue(player1.isDestroyed());
	}
	
	public void testEventsBetweenEnemies() {
		Map map = new Map("data/tests/map_with_one_block.xml");
		
		Character lopezReggae1 = new LopezReggae().create(new Position(0,0));
		int initialHealth = lopezReggae1.getHealth();
		Character lopezReggae2 = new LopezReggae().create(new Position(0,0));
		
		map.addElement(new Position(0,0), lopezReggae1);
		map.addElement(new Position(0,0), lopezReggae2);		
		
		map.runEvents(new Events());
		
		assertEquals(lopezReggae1.getHealth(), initialHealth);
		assertEquals(lopezReggae2.getHealth(), initialHealth);
	}

	public void testEventsBetweenPlayerAndItemRemoved() {
		Map map = new Map("data/tests/map_empty.xml");
		
		Character player = new Player().create(new Position(0, 0));
		
		map.addElement(new Position(0,0), player);
		map.addElement(new Position(0,0), new HabanoChala());
		
		map.runEvents(new Events());
		
		
		assertEquals(map.positionGetElements(new Position(0,0)).size(), 1);
	}
	
	
}
