package game.view;

import game.model.Block;
import game.model.Bomb;
import game.model.Character;
import game.model.Cell;
import game.model.CharacterCecilio;
import game.model.CharacterLopezReggae;
import game.model.CharacterLopezReggaeAlado;
import game.model.CharacterPlayer;
import game.model.CollidableElement;
import game.model.Exit;
import game.model.Explosion;
import game.model.Map;
import game.model.Position;
import game.model.Proyectile;
import game.model.blocks.Acero;
import game.model.blocks.Cemento;
import game.model.blocks.Ladrillo;
import game.model.items.HabanoChala;
import game.model.items.Timer;
import game.model.items.ToleTole;

import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewMap implements Drawable {

	private Image background;

	private ArrayList <Drawable> thingsToDraw ;
	private ArrayList <Drawable> blocksToDraw ;
	private ArrayList <Drawable> LopezAladoToDraw;
	private Map map;
	private ViewState vs;

	public ViewMap(Map recivedMap, ViewState recivedvs){
		
		ImageIcon i = new ImageIcon("data/Pictures/FondoMenu.png");
		this.background = i.getImage();
		
	
		this.thingsToDraw = new ArrayList<Drawable>();
		this.blocksToDraw = new ArrayList<Drawable>();
		this.LopezAladoToDraw = new ArrayList<Drawable>();
		this.map = recivedMap;
		this.vs = recivedvs;
	}

	public synchronized void refresh(Map map){

		ImageIcon i = new ImageIcon("data/Pictures/fondo.png");
		this.background = i.getImage();
		Iterator<Cell> itrmap = map.iterator();
		this.thingsToDraw.clear();
		while(itrmap.hasNext()){
			Object cell = itrmap.next();
			Position pos = ((Cell) cell).getPosition();
			ArrayList<CollidableElement> elements = ((Cell) cell).getElements();
			Iterator<CollidableElement> itrelem = elements.iterator();
			while(itrelem.hasNext()){

				CollidableElement elem = itrelem.next();
				if (elem instanceof Block ){
					this.blocksToDraw.add(this.chooseBlock((Block)elem, pos));
				}else if (elem instanceof HabanoChala ){
					this.thingsToDraw.add(new ViewHabanoChala((HabanoChala)elem, pos));
				}else if (elem instanceof Timer ){
					this.thingsToDraw.add(new ViewTimer((Timer)elem, pos));
				}else if (elem instanceof ToleTole ){
					this.thingsToDraw.add(new ViewToleTole((ToleTole)elem, pos));
				}else if (elem instanceof Exit ){
					this.thingsToDraw.add(new ViewExit((Exit)elem, pos));
				}else if (elem instanceof CharacterPlayer){
					this.thingsToDraw.add(new ViewPlayer((CharacterPlayer)elem, pos));
				}else if (elem instanceof CharacterCecilio){
					this.thingsToDraw.add(new ViewCecilio((CharacterCecilio)elem, pos));
				}else if (elem instanceof CharacterLopezReggae){
					this.thingsToDraw.add(new ViewLopez((CharacterLopezReggae)elem, pos));
				}else if (elem instanceof CharacterLopezReggaeAlado){
					this.LopezAladoToDraw.add(new ViewLopezAlado((CharacterLopezReggaeAlado)elem, pos));
				}else if (elem instanceof Bomb){
					this.thingsToDraw.add(chooseBomb((Bomb)elem, pos));
				}else if (elem instanceof Explosion){
					this.thingsToDraw.add(new ViewExplosion((Explosion)elem, pos));
				}
			}
		
		}
		this.thingsToDraw.addAll(this.blocksToDraw);
		this.blocksToDraw.clear();
		this.thingsToDraw.addAll(this.LopezAladoToDraw);
		this.LopezAladoToDraw.clear();
	}

	private Drawable chooseBomb(Bomb bomb, Position pos){
		if (bomb instanceof Proyectile){
			return (new ViewProyectile((Proyectile)bomb,pos));
		}
		return (new ViewBomb(bomb,pos));
	}
	private Drawable chooseBlock(Block block, Position pos){
		if(block.getOriginalHealth() == new Acero().create().getOriginalHealth() ){

			return (new ViewAcero(block,pos));
		}
		else if(block.getOriginalHealth() == new Ladrillo().create().getOriginalHealth()){
			return (new ViewLadrillo(block,pos)) ;
		}
		else if(block.getOriginalHealth() == new Cemento().create().getOriginalHealth() ){
			return (new ViewCemento(block,pos)) ;
		}
		return null;
	}
	
	@Override
	public synchronized void draw(Graphics g) {
		g.drawImage(this.background, 0, 0, null);
		
		Iterator<Drawable> itr = this.thingsToDraw.iterator();
		while(itr.hasNext()){
			((Drawable)itr.next()).draw(g);
		}
		if(this.vs.hasToDraw())
			this.vs.draw(g);
	}
}
