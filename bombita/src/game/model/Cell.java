package game.model;

import game.model.items.HabanoChala;
import game.model.items.Timer;
import game.model.items.ToleTole;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Element;


public class Cell implements SerializableElement{
	private Position position;
	private ArrayList <CollidableElement> elements;

	
	public Cell(Position position) {
		this.position = position;
		this.elements = new ArrayList<CollidableElement>();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}	
	
	public boolean isSolid() {
		Iterator <CollidableElement> itr = elements.iterator();
		
		while (itr.hasNext())
			if (itr.next().isSolid())
				return true;
		
		return false;
	}
	
	public void addElement(CollidableElement element) {
		this.elements.add(element);
	}
	
	public void removeElement(CollidableElement element) {
		this.elements.remove(element);
	}
	
	public ArrayList <CollidableElement> getElements() {
		return this.elements;
	}
	
	public boolean isEmpty(){
		return (elements.isEmpty());
	}

	public void events(Map map) {
		Iterator <CollidableElement> itr = new ArrayList<CollidableElement>(this.elements).iterator();
	
		while (itr.hasNext()) {
			
			CollidableElement element = itr.next();
			element.event(map);

			for (int i = 0; i < this.elements.size(); i++){
				
				CollidableElement elementToCollide = this.elements.get(i);
				
				if (element != elementToCollide) 
					element.collideWithAnother(elementToCollide);
			}
				
			if (!element.isActive())
				this.elements.remove(element);
		}
	}

	@Override
	public void serialize(Element parent) {
		if (!this.isEmpty()) {
			Element cell = parent.addElement("cell");
			cell.addAttribute("x", Integer.toString(this.getPosition().getX()));
			cell.addAttribute("y", Integer.toString(this.getPosition().getY()));
			
			Iterator <CollidableElement> itr = elements.iterator();
			
			while (itr.hasNext())
				itr.next().serialize(cell);
		}
	}
	
	public void unserialize(Element elementCell, Map map, Position position) {
		
    	for (Iterator<Element> j = elementCell.elementIterator(); j.hasNext();) {
    		Element element = (Element) j.next();
    			
    		if (element.getName().equals("block"))
	    		this.addElement(Block.unserialize(element, map, position));
    		else if (element.getName().equals("habanochala"))
	    		this.addElement(HabanoChala.unserialize(element, map, position));
    		else if (element.getName().equals("toletole"))
    			this.addElement(ToleTole.unserialize(element, map, position));    		
    		else if (element.getName().equals("timer"))
	    		this.addElement(Timer.unserialize(element, map, position));
    		else if (element.getName().equals("exit"))
	    		this.addElement(Exit.unserialize(element, map, position));
    		else if (element.getName().equals("character"))
	    		this.addElement(Character.unserialize(element, map, position));
    		else if (element.getName().equals("explosion"))
	    		this.addElement(Explosion.unserialize(element, map, position));
    		else if (element.getName().equals("bomb"))
	    		this.addElement(Bomb.unserialize(element, map, position));
    		else if (element.getName().equals("proyectile"))
	    		this.addElement(Proyectile.unserialize(element, map, position));    		
    	
    	}
	}

}

