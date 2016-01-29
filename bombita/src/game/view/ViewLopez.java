package game.view;


import game.model.Position;
import game.model.Character;

import javax.swing.ImageIcon;

public class ViewLopez extends Viewer {

	private Character character;

	public ViewLopez(Character OriginalCharacter, Position OriginalPos){
		
		this.character = OriginalCharacter;
		this.pos = OriginalCharacter.getPosition();
		ImageIcon i = new ImageIcon("data/Pictures/Lopez.png");
		this.img = i.getImage();
	}
}
