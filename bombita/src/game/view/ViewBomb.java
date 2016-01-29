package game.view;

import javax.swing.ImageIcon;

import game.model.Bomb;
import game.model.Position;

public class ViewBomb extends Viewer implements Drawable {
	private Bomb bomb;
	
	public ViewBomb(Bomb OriginalBomb, Position OriginalPos){
		this.bomb = OriginalBomb;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/bomba.png");
		this.img = i.getImage();
	}

}
