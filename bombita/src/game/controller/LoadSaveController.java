package game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import game.model.Map;
import game.view.MapHandler;

public class LoadSaveController implements KeyListener{
	
	private MapHandler map;
	private String file;

	
	public LoadSaveController(MapHandler map, String file){
		this.map = map;
		this.file = file;
	}
	
	@Override
	public void keyPressed(KeyEvent event) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		switch (keyCode){
			case (KeyEvent.VK_F1):
				if (new File(this.file).exists())
					this.map.changeCurrentMapTo(new Map(this.file));
				break;
			case (KeyEvent.VK_F2):
				this.map.saveMap(this.file);
				break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		
	}

}
