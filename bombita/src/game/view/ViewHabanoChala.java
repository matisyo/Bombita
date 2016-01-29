package game.view;

import game.model.Block;
import game.model.Item;
import game.model.Position;

import javax.swing.ImageIcon;

public class ViewHabanoChala extends Viewer{
private Item item;

	public ViewHabanoChala(Item item, Position OriginalPos){
		
		this.item = item;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/ChalaHabano.png");
		this.img = i.getImage();
	}
}
