package game.model;

import game.model.direction.Direction;


public class AladoStrategy extends CharacterStrategy{

	@Override
	public void movement(Map map, Movable movable, Direction direction) {
		
		Position newPosition = direction.getNextPos(movable.getPosition());		
		
		if (map.positionIsValid(newPosition)) {
			map.addElement(newPosition, (CollidableElement)movable);
			map.removeElement(movable.getPosition(),(CollidableElement)movable);
			movable.setPosition(newPosition);
		}
	}
	
}
