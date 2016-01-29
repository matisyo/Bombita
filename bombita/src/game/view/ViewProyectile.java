package game.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import game.model.Position;
import game.model.Proyectile;
import game.model.actions.ActionMoveDown;
import game.model.actions.ActionMoveRight;
import game.model.actions.ActionMoveUp;

public class ViewProyectile extends Viewer implements Drawable {
	private Proyectile proyectile;
	
	public ViewProyectile(Proyectile Originalproyectile, Position OriginalPos){
		this.proyectile = Originalproyectile;
		this.pos = OriginalPos;
		ImageIcon i;
		if (this.proyectile.getAction() instanceof ActionMoveUp)
			 i = new ImageIcon("data/Pictures/misilup.png");
		else if (this.proyectile.getAction() instanceof ActionMoveDown)
			 i = new ImageIcon("data/Pictures/misildown.png");
		else if (this.proyectile.getAction() instanceof ActionMoveRight)
			 i = new ImageIcon("data/Pictures/misilright.png");
		else
			 i = new ImageIcon("data/Pictures/misilleft.png");
		
		this.img = i.getImage();
	}

}


	