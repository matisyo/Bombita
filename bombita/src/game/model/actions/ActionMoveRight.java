package game.model.actions;

import game.model.Action;
import game.model.ActionUser;
import game.model.Map;
import game.model.Movable;
import game.model.direction.Direction;
import game.model.direction.DirectionRight;

public class ActionMoveRight implements Action {

	@Override
	public void doAction(Map map, ActionUser actionUser) {
		DirectionRight dir = new DirectionRight();
		((Movable)actionUser).getStrategy().movement(map, (Movable)actionUser, dir);
	}
	
	@Override
	public Direction getDirection() {
		return (new DirectionRight());
	}

}
