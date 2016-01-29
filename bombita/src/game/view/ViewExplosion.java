package game.view;

import javax.swing.ImageIcon;

import game.model.Explosion;
import game.model.Position;

public class ViewExplosion extends Viewer implements Drawable {
	private Explosion explosion;

	public ViewExplosion(Explosion OriginalExplosion, Position OriginalPos){
		this.explosion = OriginalExplosion;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/Explosion.png");
		this.img = i.getImage();
	}

}