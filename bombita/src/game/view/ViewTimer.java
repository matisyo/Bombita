package game.view;

import java.awt.Image;

import game.model.Item;
import game.model.Position;
import game.model.items.Timer;

import javax.swing.ImageIcon;


public class ViewTimer extends Viewer{
private Item item;

	public ViewTimer(Timer item, Position OriginalPos){
		this.item = item;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/Cloock.png");
		this.img = i.getImage();
	}
}
