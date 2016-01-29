package game.model.actions;

import game.model.Action;
import game.model.ActionUser;
import game.model.Map;
import game.model.direction.Direction;

public class ActionDoNothing implements Action {

	@Override
	public void doAction(Map map, ActionUser actionUser){
	}
	
	@Override
	public Direction getDirection() {
		return null;
	}
}
