package game.view;

import game.model.Map;

public interface MapHandler {
	public void changeCurrentMapTo(Map map);

	public void saveMap(String file);
}
