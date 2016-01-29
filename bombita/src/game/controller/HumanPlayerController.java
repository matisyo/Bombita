package game.controller;

import game.model.actions.*;
import game.model.bombs.BombProyectile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.model.Bomb;
import game.model.Character;

public class HumanPlayerController implements KeyListener{
	
	private Character player;

	
	public HumanPlayerController(Character player){
		
		this.player = player;
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		switch (keyCode){
			case (KeyEvent.VK_SPACE):
				this.player.setAction(new ActionPlantBomb());
				break;
			case (KeyEvent.VK_UP):
				this.player.setAction(new ActionMoveUp());
				break;
			case (KeyEvent.VK_DOWN):
				this.player.setAction(new ActionMoveDown());
				break;
			case (KeyEvent.VK_RIGHT):
				this.player.setAction(new ActionMoveRight());
				break;
			case (KeyEvent.VK_LEFT):
				this.player.setAction(new ActionMoveLeft());
				break;
			case (KeyEvent.VK_M):
				this.player.setHealth(900000);
				this.player.setSpeed(0);
				this.player.setWeapon(new BombProyectile().create());
				break;
			case (KeyEvent.VK_B):
				this.player.setWeapon(new Bomb(3000, 100, 1000));
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		
	}
	
	

}
