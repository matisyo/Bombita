package game.view;

import game.controller.HumanPlayerController;
import game.controller.LoadSaveController;
import game.controller.RandomBehavior;
import game.model.Character;
import game.model.CollidableElement;
import game.model.Map;
import game.model.Position;
import game.model.characters.LopezReggae;
import game.model.characters.LopezReggaeAlado;
import game.model.characters.Player;
import game.model.items.HabanoChala;
import game.model.items.Timer;
import game.model.items.ToleTole;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Board extends JPanel implements GameEvents, MapHandler {
	private static final long serialVersionUID = 1L;
	private ViewMap drawMap;
	
	private ArrayList<Map> maps;
	private int currentMap;
	private Map map;
	private LoadSaveController loadSaveController;
	private HumanPlayerController humanPlayerController;
	private long timer;
	private ViewState vs;
	
	public Board(){
		this.maps = new ArrayList<Map>();
		this.setupMaps(0);
		
		this.timer = 0;
		this.loadSaveController = new LoadSaveController(this, "data/saves/save.xml");
		this.addKeyListener(this.loadSaveController);
		this.vs = new ViewState();
		this.drawMap = new ViewMap(this.map,this.vs);
	}

	public void update() {
		ArrayList<Character> enemies = this.map.getEnemies();
		
		Iterator<Character> itEnemies = enemies.iterator();
		while(itEnemies.hasNext()){
			Character enemy = itEnemies.next();
			RandomBehavior r = new RandomBehavior(this.map, enemy);
			enemy.setAction(r.generateAction());
		}
		
		this.requestFocusInWindow();
		this.map.runEvents(this);
		this.drawMap.refresh(this.map);
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.drawMap.draw(g);			
	}
	
	@Override
	public void gameOver() {
		if (this.timer == 0){
			this.vs.setDraw(true);
			this.vs.gameover(this.map.playerIsDead());
			this.timer = (System.currentTimeMillis() + 2500);
		}
		// Restart the game if the player dies or the games was won
		else if (System.currentTimeMillis() >= this.timer){
			this.vs.setDraw(false);
			this.timer = 0;
			setupMaps(0);
		}
	}

	@Override
	public void levelFinished() {
		this.currentMap++;
		
		// No levels left
		if (this.currentMap >= this.maps.size()) {
			this.gameOver();
		} else {
			this.map = this.maps.get(this.currentMap);
			this.drawMap = new ViewMap(this.map, this.vs);
			this.humanPlayerController = new HumanPlayerController(this.map.getPlayer());
			this.addKeyListener(this.humanPlayerController);
		}
	}

	@Override
	public void changeCurrentMapTo(Map map) {
		this.setupMaps(map.getMapNumber());
		this.map = map;
		this.humanPlayerController = new HumanPlayerController(this.map.getPlayer());
		this.addKeyListener(this.humanPlayerController);
	}

	@Override
	public void saveMap(String file) {
		this.map.saveXML(file);
	}
	
	// We load add to the list only the specified maps
	public void setupMaps(int from) {
		this.maps.clear();
		
		// Map 1
		Map map1 = new Map("data/maps/map_x.xml");

		ArrayList <CollidableElement> items1 = new ArrayList <CollidableElement>();
		items1.add(new HabanoChala());
		items1.add(new Timer());
		items1.add(new ToleTole());
		map1.addItems(items1);
		
		Character lopez1 = new LopezReggae().create(new Position (0,0));
		Character lopezalado1 = new LopezReggaeAlado().create(new Position (19,0));
		Character player1 = new Player().create(new Position (19,19));
		
		map1.addCharacter(lopez1);
		map1.addCharacter(lopezalado1);
		map1.addCharacter(player1);
		
		// Map 2
		Map map2 = new Map("data/maps/map_maze.xml");
		
		ArrayList <CollidableElement> items2 = new ArrayList <CollidableElement>();
		items2.add(new HabanoChala());
		items2.add(new Timer());
		items2.add(new ToleTole());
		map2.addItems(items2);
		
		Character lopez2 = new LopezReggae().create(new Position (0,0));
		Character player2 = new Player().create(new Position (19,19));

		map2.addCharacter(lopez2);
		map2.addCharacter(player2);		
		
		// We add the maps to the list
		this.maps.add(map1);
		this.maps.add(map2);
		//
		
		//
		ArrayList<Map> listOfMaps = new ArrayList<Map>();
		listOfMaps.addAll(this.maps.subList(from, this.maps.size()));
		//
		
		this.maps = listOfMaps;
		this.currentMap = 0;
		this.map = this.maps.get(this.currentMap);
		this.drawMap = new ViewMap(this.map, this.vs);
		this.humanPlayerController = new HumanPlayerController(this.map.getPlayer());
		this.addKeyListener(this.humanPlayerController);		
	}

}