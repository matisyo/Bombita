package game.view;


import game.model.Position;
import game.model.Character;

import javax.swing.ImageIcon;

public class ViewCecilio extends Viewer {

	private Character character;

	public ViewCecilio(Character OriginalCharacter, Position OriginalPos){
		
		this.character = OriginalCharacter;
		this.pos = OriginalCharacter.getPosition();
		ImageIcon i = new ImageIcon("data/Pictures/Cecilio.png");
		this.img = i.getImage();
	}
}
