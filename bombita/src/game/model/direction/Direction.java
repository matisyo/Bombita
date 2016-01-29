package game.model.direction;

import game.model.Position;

public interface Direction {	
	
	public Position getNextPos(Position position);
	
}
