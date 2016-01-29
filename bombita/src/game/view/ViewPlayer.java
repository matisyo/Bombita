package game.view;


import game.model.Position;
import game.model.Character;

import javax.swing.ImageIcon;

public class ViewPlayer extends Viewer {

	private Character character;

	public ViewPlayer(Character OriginalCharacter, Position OriginalPos){
		ImageIcon i;
		this.character = OriginalCharacter;
		this.pos = OriginalCharacter.getPosition();
		if (this.character.getHealth() > 100){
			i = new ImageIcon("data/Pictures/monkey.png");
		}
		else 
			i = new ImageIcon("data/Pictures/Player.png");
		this.img = i.getImage();
	}
}
