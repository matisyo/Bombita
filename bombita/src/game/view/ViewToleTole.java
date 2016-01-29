package game.view;

import game.model.Item;
import game.model.Position;
import game.model.items.Timer;
import game.model.items.ToleTole;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ViewToleTole extends Viewer{

	private Item item;

	public ViewToleTole(ToleTole item, Position OriginalPos){
		this.item = item;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/BomToleTole.png");
		this.img = i.getImage();
	}
}
