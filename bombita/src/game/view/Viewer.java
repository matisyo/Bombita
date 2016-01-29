package game.view;

import game.model.Position;

import java.awt.Graphics;
import java.awt.Image;


public class Viewer implements Drawable {

	
protected Position pos;
protected Image img;

	public void draw(Graphics g) {
		g.drawImage(this.img, this.pos.getX() * 32, this.pos.getY() * 32, null);
	}
}
