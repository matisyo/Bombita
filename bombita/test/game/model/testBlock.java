package game.model;
import junit.framework.TestCase;
import game.model.Block;
import game.model.Explosion;
import game.model.Position;


public class testBlock extends TestCase {

	public void testReceiveDamage(){
		Block block = new Block(100,100, false);
		Explosion explosion = new Explosion(new Position(0,0), 10);
		block.collideWith(explosion);
		
		assertEquals(block.getHealth(), 90);
	}
	
	public void testIsNotDestroyed(){
		Block block = new Block(100,100, false);
		Explosion explosion = new Explosion(new Position(0,0), 10);
		block.collideWith(explosion);
		
		assertFalse(block.isDestroyed());
	}
	
	public void testIsDestroyed(){
		Block block = new Block(100,100, false);
		Explosion explosion = new Explosion(new Position(0,0), 100);
		block.collideWith(explosion);
		
		assertTrue(block.isDestroyed());
	}
	
	public void testIsDestroyedII(){
		Block block = new Block(100,100, false);
		Explosion explosion = new Explosion(new Position(0,0), 1000);
		block.collideWith(explosion);
		
		assertTrue(block.isDestroyed());
	}
	
}


