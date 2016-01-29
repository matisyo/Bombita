package game.view;


import javax.swing.ImageIcon;

import game.model.Block;
import game.model.Position;


public class ViewLadrillo extends Viewer implements Drawable {

	private Block block;

	public ViewLadrillo(Block block, Position OriginalPos){

		this.block = block;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/ladrillos.png");
		this.img = i.getImage();
	}

}
