package game.model.actions;

import game.model.Action;
import game.model.ActionUser;
import game.model.Map;
import game.model.Movable;
import game.model.direction.Direction;
import game.model.direction.DirectionDown;
import game.model.direction.DirectionUp;

public class ActionMoveUp implements Action {

	@Override
	public void doAction(Map map, ActionUser actionUser) {
		DirectionUp dir = new DirectionUp();
		((Movable)actionUser).getStrategy().movement(map, (Movable)actionUser, dir);
	}
	
	@Override
	public Direction getDirection() {
		return (new DirectionUp());
	}

}
