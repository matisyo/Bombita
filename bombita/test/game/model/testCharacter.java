package game.model;
import junit.framework.TestCase;
import game.model.Character;
import game.model.Exit;
import game.model.Explosion;
import game.model.Item;
import game.model.Map;
import game.model.Position;
import game.model.bombs.BombMolotov;
import game.model.characters.LopezReggae;
import game.model.characters.Player;
import game.model.items.HabanoChala;
import game.model.items.Timer;
import game.model.items.ToleTole;

public class testCharacter extends TestCase {
	
	public void testIsNotDestroyed(){
		Character lopez = new LopezReggae().create(new Position (0,0));
		lopez.setHealth(1);
		
		assertFalse(lopez.isDestroyed());
	} 

	public void testIsDestroyed(){
		Character lopez = new LopezReggae().create(new Position (0,0));
		lopez.setHealth(0);
		
		assertTrue(lopez.isDestroyed());
	}
	
	public void testIsDestroyedNegative(){
		Character lopez = new LopezReggae().create(new Position (0,0));
		lopez.setHealth(-10);
		
		assertTrue(lopez.isDestroyed());
	}
	
	public void testCollideWithExplosion(){
		Character lopez = new LopezReggae().create(new Position (0,0));
		Explosion explosion = new Explosion(new Position (0,0), 1);
		lopez.collideWith(explosion);
		
		assertEquals(lopez.getHealth(), 9);
	}
	
	public void testCollideWithBigExplosionAndThenIsInactive(){
		Character lopez = new LopezReggae().create(new Position (0,0));
		Explosion explosion = new Explosion(new Position (0,0), 1000);
		lopez.collideWith(explosion);
		
		assertFalse(lopez.isActive());
	}
	
	public void testCollideWithBigExplosion(){
		Character lopez = new LopezReggae().create(new Position (0,0));
		Explosion explosion = new Explosion(new Position (0,0), 1000);
		lopez.collideWith(explosion);
		
		assertTrue(lopez.isDestroyed());
	}
		
	public void testCollideTwoPlayers(){
		Character player1 = new Player().create(new Position (0,0));
		Character player2 = new Player().create(new Position (0,0));
		
		int originalHealth = player1.getHealth();
		player1.collideWith(player2);
		
		assertEquals(player1.getHealth(), originalHealth);
	}
	
	public void testCollidePlayerWithEnemy(){
		Character player = new Player().create(new Position (0,0));
		Character lopez = new LopezReggae().create(new Position (0,0));
		
		int originalHealth = player.getHealth();
		player.collideWith(lopez);
		
		assertNotSame(player.getHealth(), originalHealth);
	}
	
	public void testCollideWithHabanoChala(){
		Character player = new Player().create(new Position (0,0));
		Item habanoChala = new HabanoChala();
		
		player.collideWith(habanoChala);
		
		assertEquals(player.getSpeed(), 85);
	}
	
	public void testCollideWithTimer() {
		Character player = new Player().create(new Position(0, 0));
		Item timer = new Timer();
		
		player.setWeapon(new BombMolotov().create());
		player.collideWith(timer);
		
		assertEquals(((Bomb)player.getWeapon()).getDelay(), 2550);
	}
	
	public void testCollideWithToleTole() {
		Character player = new Player().create(new Position(0, 0));
		Item toleTole = new ToleTole();
		
		player.collideWith(toleTole);
		
		assertEquals(((Bomb)player.getWeapon()).getDestructionPower(), 1000);
	}
	
	public void testCollideWithExit() {
		Character player = new Player().create(new Position(0, 0));
		Exit exit = new Exit();
		
		player.collideWith(exit);
		
		assertTrue(exit.wasPickedUp());
	}	
	
	public void testcCharacterPlantBomb(){
		Map map = new Map("data/tests/xml_plant_test.xml");
		Position pos = new Position(1, 2);
		Character player = new Player().create(pos);
	
		player.plantBomb(map);
		assertFalse(map.positionIsEmpty(pos));
	}
	
	public void testcCharacterPlantTwoBombs(){
		Map map = new Map("data/tests/xml_plant_test.xml");
		Position pos = new Position(1, 2);
		Character player = new Player().create(pos);
	
		player.plantBomb(map);
		player.plantBomb(map);
		assertEquals(map.positionGetElements(pos).size(), 1);
	}
	
	
}