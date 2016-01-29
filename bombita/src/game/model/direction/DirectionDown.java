package game.model.direction;

import game.model.Position;

public class DirectionDown implements Direction {

	@Override
	public Position getNextPos(Position position) {
		Position newPosition = new Position(0,1);
		newPosition.add(position);
		return newPosition;		
		
	}

}
