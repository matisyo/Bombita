package game.view;

import javax.swing.ImageIcon;

import game.model.Block;
import game.model.Position;

public class ViewAcero extends Viewer implements Drawable {
	private Block block;
	
	public ViewAcero(Block block, Position OriginalPos){
		this.block = block;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/metal1.png");
		this.img = i.getImage();
	}

}
