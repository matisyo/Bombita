package game.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameLoop extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Board gamePanel = new Board();
	private JButton startButton = new JButton("Start Game");
	private JLabel fpsLabel = new JLabel("FPS: 0");
	private boolean running = false;
	private boolean paused = true;
	private int frameCount = 0;

	public GameLoop() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bombita!");
		this.setIconImage(new ImageIcon("data/Pictures/icon.png").getImage());
		this.setResizable(false);
		this.setSize(640, 694);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		this.fpsLabel.setHorizontalAlignment(JLabel.RIGHT);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(this.startButton);
		p.add(this.fpsLabel);
		
		cp.add(this.gamePanel, BorderLayout.CENTER);
		cp.add(p, BorderLayout.SOUTH);

		this.startButton.addActionListener(this);
	}

	public static void main(String[] args){
		GameLoop gl = new GameLoop();
		
		gl.setLocationRelativeTo(null);
		gl.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		Object s = e.getSource();

		if (s == this.startButton) {
			this.running = !this.running;
			this.paused = !this.paused;

			if (this.running){
				this.startButton.setText("Stop");
				runGameLoop();
			} else {
				this.startButton.setText("Continue");
			}
		}
	}

	public void runGameLoop(){
		Thread loop = new Thread(){
			public void run(){
				gameLoop();
			}
		};
		loop.start();
	}

	private void gameLoop(){

		final double GAME_HERTZ = 30.0;

		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;

		final int MAX_UPDATES_BEFORE_RENDER = 5;
		double lastUpdateTime = System.nanoTime();

		double lastRenderTime = System.nanoTime();

		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS; 

		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (this.running){
			double now = System.nanoTime();
			int updateCount = 0;

			if (!this.paused){
				while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER ){
					updateGame();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES){
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				drawGame();
				lastRenderTime = now;

				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime){
					this.fpsLabel.setText("FPS: " + Integer.toString(this.frameCount));
					this.frameCount = 0;
					lastSecondTime = thisSecond;
				}

				while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES){
					Thread.yield();
					try {Thread.sleep(1);} catch(Exception e) {} 

					now = System.nanoTime();
				}
			}
		}
	}

	private void updateGame(){
		this.gamePanel.update();
	}
	
	private void drawGame(){
		this.gamePanel.repaint();
		this.frameCount++;
	}

}