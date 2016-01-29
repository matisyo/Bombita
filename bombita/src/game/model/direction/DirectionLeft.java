package game.model.direction;

import game.model.Position;

public class DirectionLeft implements Direction {

	@Override
	public Position getNextPos(Position position) {
		Position newPosition = new Position(-1,0);
		newPosition.add(position);
		return newPosition;
		
		
	}

}
