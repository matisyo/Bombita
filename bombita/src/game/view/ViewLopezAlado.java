package game.view;

import game.model.Position;
import game.model.Character;

import javax.swing.ImageIcon;

public class ViewLopezAlado extends Viewer {

	private Character character;

	public ViewLopezAlado(Character OriginalCharacter, Position OriginalPos){
		
		this.character = OriginalCharacter;
		this.pos = OriginalCharacter.getPosition();
		ImageIcon i = new ImageIcon("data/Pictures/LopezAlado.png");
		this.img = i.getImage();
	}
}
