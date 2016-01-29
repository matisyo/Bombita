package game.model;

import game.model.direction.Direction;


public interface Action {
	
	public void doAction(Map map, ActionUser actionUser);
	public Direction getDirection();
}
