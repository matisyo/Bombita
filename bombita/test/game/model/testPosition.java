package game.model;

import junit.framework.TestCase;

public class testPosition extends TestCase {
	public void testEquals(){
		Position position = new Position(0,1);
		Position position2 = new Position(0,1);
		
		assertTrue(position.equals(position2));
		
	}
	
	public void testDifferent(){
		Position position = new Position(0,13212);
		Position position2 = new Position(0,1);
		
		assertFalse(position.equals(position2));
		
	}
	
	public void testAddPositions(){
		Position position1 = new Position(5,5);
		Position position2= new Position(2,9);
		
		position1.add(position2);
		Position correctPosition = new Position(7,14); // this should be the correct position after adding
		
		assertTrue(correctPosition.equals(position1));
		
	}
}
