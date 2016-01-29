package game.view;


import javax.swing.ImageIcon;

import game.model.Block;
import game.model.Position;


public class ViewCemento extends Viewer implements Drawable {

	private Block block;
	
	public ViewCemento(Block block, Position OriginalPos){
		
		this.block = block;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/cemento.png");
		this.img = i.getImage();
	}
}
