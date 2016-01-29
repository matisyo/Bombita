package game.view;


import game.model.Exit;
import game.model.Position;


import javax.swing.ImageIcon;


public class ViewExit extends Viewer{
	private Exit exit;

	public ViewExit(Exit stairs, Position OriginalPos){
		this.exit = stairs;
		this.pos = OriginalPos;
		ImageIcon i = new ImageIcon("data/Pictures/Escalera.png");
		this.img = i.getImage();
	}
}
