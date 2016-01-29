package game.model;

import game.model.actions.ActionDoNothing;
import game.model.direction.Direction;


public class CharacterStrategy{
	
	private Action lastAction = new ActionDoNothing();
	
	public void movement(Map map, Movable movable, Direction direction){
		Position newPosition = direction.getNextPos(movable.getPosition());
		
		if (map.positionIsValid(newPosition))
			if (!map.positionIsSolid(newPosition)) {
				map.addElement(newPosition,(CollidableElement) movable);
				map.removeElement(movable.getPosition(),(CollidableElement)movable);
				movable.setPosition(newPosition);
			}
	}
		
	
	public void doAction(Map map, Movable movable, Action action) {
		action.doAction(map, movable);
		if(action.getDirection() != null)
			this.lastAction = action;
	}
	
	public Action lastAction(){
		return this.lastAction;
	}
	
	public Direction lastDirection(){
		return (this.lastAction.getDirection());
	}


}
