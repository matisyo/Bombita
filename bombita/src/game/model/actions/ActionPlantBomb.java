package game.model.actions;

import game.model.Action;
import game.model.ActionUser;
import game.model.Attacker;
import game.model.Map;
import game.model.direction.Direction;

public class ActionPlantBomb implements Action {

	@Override
	public void doAction(Map map, ActionUser actionUser){
		((Attacker) actionUser).plantBomb(map);
	}

	@Override
	public Direction getDirection() {
		return null;
	}


}
