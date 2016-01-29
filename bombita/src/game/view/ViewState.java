package game.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ViewState {
	private Image i;
	private boolean drawState;
	
	public ViewState(){
		ImageIcon image = new ImageIcon("");
		this.i = image.getImage();
		this.drawState = false;
	}
	
	public void win(){
		ImageIcon image = new ImageIcon("data/Pictures/win.png");
		this.i = image.getImage();
	}
	public void gameover(boolean lose){
		if (!lose)
			this.win();
		else{
			ImageIcon image = new ImageIcon("data/Pictures/gameover.png");
			this.i = image.getImage();
		}
	}

	public void draw(Graphics g) {
		g.drawImage(this.i, 0, 0, null);
	}
	public boolean hasToDraw(){
		return this.drawState;
	}
	public void setDraw(boolean option){
		this.drawState = option;
	}
}
