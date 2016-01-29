package game.model;

import game.model.actions.ActionDoNothing;
import game.model.actions.ActionMoveDown;
import game.model.actions.ActionMoveLeft;
import game.model.actions.ActionMoveRight;
import game.model.actions.ActionMoveUp;
import game.model.actions.ActionPlantBomb;
import game.model.characters.LopezReggaeAlado;
import game.model.characters.Player;
import game.model.direction.*;
import junit.framework.TestCase;

public class testAction extends TestCase {
	public void testCharacterMovesRight(){	
		Map map = new Map("data/tests/map_empty.xml");
		
		Position initialPosition = new Position(0,0); //initial position
		Character character = new Player().create(initialPosition);
		character.setSpeed(0);
		ActionMoveRight action = new ActionMoveRight();
		character.setAction(action);
		
		character.event(map);
		Position currentPosition = character.getPosition(); // character's current position
		Position correctPosition = new Position(1,0); //This should be the Character's correct position, it's its initial position + movement
		correctPosition.add(initialPosition);
		
		assertTrue(currentPosition.equals(correctPosition));
		
	}
	
	public void testCharacterMovesLeft(){
		Map map = new Map("data/tests/map_empty.xml");
		
		Position initialPosition = new Position(5,5);
		Character character = new Player().create(initialPosition);
		character.setSpeed(0);
		ActionMoveLeft action = new ActionMoveLeft();
		character.setAction(action);
		
		character.event(map);
		Position currentPosition = character.getPosition(); 
		DirectionLeft dir = new DirectionLeft();
		Position correctPosition = dir.getNextPos(initialPosition);
		
		assertTrue(currentPosition.equals(correctPosition));
		
	}
	
	public void testCharacterMovesUp(){
		Map map = new Map("data/tests/map_empty.xml");
		
		Position initialPosition = new Position(5,5); //initial position
		Character character = new Player().create(initialPosition);
		character.setSpeed(0);
		ActionMoveUp action = new ActionMoveUp();
		character.setAction(action);
		
		character.event(map);
		Position currentPosition = character.getPosition();
		DirectionUp dir = new DirectionUp();
		Position correctPosition = dir.getNextPos(initialPosition);
		
		assertTrue(currentPosition.equals(correctPosition));
		
	}
	
	public void testCharacterMovesDown(){
		Map map = new Map("data/tests/map_empty.xml");
		
		Position initialPosition = new Position(0,0);
		Character character = new Player().create(initialPosition);
		character.setSpeed(0);
		ActionMoveDown action = new ActionMoveDown();
		character.setAction(action);
		
		character.event(map);
		Position currentPosition = character.getPosition();
		DirectionDown dir = new DirectionDown();
		Position correctPosition = dir.getNextPos(initialPosition);
		
		assertTrue(currentPosition.equals(correctPosition));
		
	}
	
	public void testCharacterDoesNothing(){	
		Map map = new Map("data/tests/map_empty.xml");
		
		Position initialPosition = new Position(0,0); 
		Character character = new Player().create(initialPosition);
		character.setSpeed(0);
		ActionDoNothing action = new ActionDoNothing();
		character.setAction(action);
		
		character.event(map);
		Position currentPosition = character.getPosition(); 
		Position correctPosition = new Position(0,0); 
		correctPosition.add(initialPosition);
		
		assertTrue(currentPosition.equals(correctPosition));
		
	}
	
	public void testCharacterActionPlantBomb(){	
		Map map = new Map("data/tests/map_empty.xml");
		
		int initElements = map.positionGetElements(new Position(0,0)).size();
		
		Character character = new Player().create(new Position (0,0));
		character.setSpeed(0);
		ActionPlantBomb action = new ActionPlantBomb();
		character.setAction(action);
		
		character.event(map);
		int currentElements = map.positionGetElements(character.getPosition()).size();
		
		assertEquals(initElements, currentElements - 1 );
		
	}
	
	public void testCharacterStrategyCannotMoveToBlock(){	
		Map map = new Map("data/tests/map_with_one_block_big.xml");
		
		Position initialPosition = new Position(0,0); 
		Character character = new Player().create(initialPosition);
		character.setSpeed(0);
		ActionMoveRight action = new ActionMoveRight();
		character.setAction(action);

		map.addElement(character.getPosition(), character);
		Position currentPosition = character.getPosition();
		character.event(map);
		
		
		assertTrue(currentPosition.equals(initialPosition));
		
	}
	
	public void testCharacterAladoCanMoveToBlock(){	
		Map map = new Map("data/tests/map_with_one_block_big.xml");
		
		Position initialPosition = new Position(0,0);
		Character character = new LopezReggaeAlado().create(initialPosition);
		character.setSpeed(0);
		ActionMoveRight action = new ActionMoveRight();
		character.setAction(action);

		character.event(map);
		Position currentPosition = character.getPosition(); 
		Position correctPosition = new Position(1,0); 
		correctPosition.add(initialPosition);
		
		assertTrue(correctPosition.equals(currentPosition));
		
	}	
}
