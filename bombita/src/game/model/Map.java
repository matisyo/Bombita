package game.model;

import exceptions.ExceptionAllBlocksAcero;
import game.view.GameEvents;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class Map{
	
	private ArrayList <ArrayList<Cell>> cells;
	private int width;
	private int height;
	private ArrayList <Cell>occupiedCells;
	private ArrayList <Character> characters;
	private Exit exit;
	private int mapNumber;
	
	public Map(String file){
		SAXReader reader = new SAXReader();
		
        Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
        Element map = document.getRootElement();
        this.characters = new ArrayList<Character>();
        this.mapNumber = Integer.parseInt(map.attributeValue("mapnumber"));
        this.width = Integer.parseInt(map.attributeValue("width"));
        this.height = Integer.parseInt(map.attributeValue("height"));
        this.cells = this.createEmptyMap();
		this.exit = new Exit();        
        
        if (map.attributeValue("type").equals("new"))
        	this.loadFromNewMap(map);
        else if (map.attributeValue("type").equals("saved"))
        	this.loadFromSavedMap(map);
        	
	}
	
	/**************************** GETTERS AND SETTERS **********************************************/
	
	public int getMapNumber() {
		return this.mapNumber;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	private Cell getCell(Position position) {
		return (Cell) (this.cells.get(position.getX())).get(position.getY());
	}
	
	public ArrayList<Character> getEnemies() {
		ArrayList<Character> enemies = new ArrayList<Character>();
		
		Iterator<Character> characters = this.characters.iterator();
		
		while (characters.hasNext()) {
			Character c = characters.next();
			if ((c.isEnemy()) && (c.isActive()))
				enemies.add(c);
		}
		
		return enemies;
	}
	
	public Character getPlayer() {
		Iterator<Character> characters = this.characters.iterator();
		
		while (characters.hasNext()) {
			Character c = characters.next();
			if (!c.isEnemy())
				return c;
		}
		
		return null;
	}	
	
	// If number of players is zero, returns true.
	public boolean playerIsDead() {
			if (this.getPlayer() == null)
				return true;
			
			return (!this.getPlayer().isActive());
	}

	public boolean enemiesAreDead() {
			return (this.getEnemies().size() == 0);
	}
		
	public boolean positionIsSolid(Position position){
			return this.getCell(position).isSolid();
	}
		
	public boolean positionIsEmpty(Position position){
			return (this.getCell(position).isEmpty());
	}
		
	public ArrayList<CollidableElement> positionGetElements(Position position){
			return (this.getCell(position).getElements());
	}


	public boolean positionIsValid(Position position) {
			return ((position.getX() >= 0) && (position.getY() >= 0) && (position.getX() < this.width) && (position.getY() < this.height));
	}

	public void setExit(Exit exit) {
		this.exit = exit;
	}
	
	
	/*******************************************************************************************/

	// Returns an empty matrix with this.height rows and this.width columns.
	private ArrayList<ArrayList<Cell>> createEmptyMap(){
		
		ArrayList<ArrayList<Cell>> matrix = new ArrayList<ArrayList<Cell>>();
		
		for (int x = 0; x < this.width; x++){
			ArrayList<Cell> column = new ArrayList<Cell>();
			
			for (int y = 0; y < this.height; y++)
				column.add(new Cell(new Position(x, y)));
			
			matrix.add(column);
		}
		
		return matrix;
	}
	
	
	/************************************** ADD, REMOVE, REPLACE ************************************************/
	
	private void replaceCell (Position position, Cell newCell){
		
		ArrayList<Cell> column = this.cells.get(position.getX());
		column.set(position.getY(), newCell); //Replaces the objt at the specified position with new_cell.		
	}
	
	private void addExit() {
		if (this.occupiedCells.size() == 0)
			return;
		Random generator = new Random();
		
		boolean canPutExit = false;
		int exitPosition = 0;
		Cell cell = null;
		
		Iterator<Cell> it = this.occupiedCells.iterator();
		boolean allAcero = true;
		while(it.hasNext()){
			if(!((Block)(it.next()).getElements().get(0)).isIndestructible()){
				allAcero = false;
				break;
		
			}
		}
		
		if(allAcero) throw new ExceptionAllBlocksAcero();
		while(!canPutExit){
			exitPosition = generator.nextInt(this.occupiedCells.size());
			cell = this.occupiedCells.get(exitPosition);
			Block block = (Block) cell.getElements().get(0);
			canPutExit = !block.isIndestructible();
		}
		
		cell.addElement(this.exit);
		
		// We remove the cell with the exit so we cannot add items later.
		this.occupiedCells.remove(exitPosition);
	}
	
	//Adds an item to a random block. If all blocks are full it does nothing.
	public void addItems(ArrayList <CollidableElement> items){
		ArrayList <Cell> cellsToAddItems = new ArrayList<Cell>(); 
		cellsToAddItems.addAll(this.occupiedCells);
		
		Random generator = new Random();
		int range = cellsToAddItems.size();
		Iterator <CollidableElement> itr = items.iterator();
		
		while ((cellsToAddItems.size() > 0) && (itr.hasNext())){
			int cellIndex = generator.nextInt(range);
			Cell randomCell = (Cell) cellsToAddItems.get(cellIndex);
			
			randomCell.addElement((CollidableElement)itr.next());
			cellsToAddItems.remove(cellIndex);
			range--;
		}
	}
	
	public void addCharacter(Character character) {
		this.characters.add(character);
		this.getCell(character.getPosition()).addElement(character);
	}
	
	public void addElement(Position position, CollidableElement element){
		this.getCell(position).addElement(element);
	}
	
	
	
	public void removeElement(Position position, CollidableElement element){
		this.getCell(position).removeElement(element);
	}
	
	/*********************************************************************************/
	
	public void runEvents(GameEvents gameEvents){
		Iterator<Cell> itr = this.iterator();
		
		while(itr.hasNext()) {
			itr.next().events(this);

			if (this.playerIsDead()) {
				gameEvents.gameOver();
				break;
			}
			if (this.enemiesAreDead())
				this.exit.setSolid(false);
			
			if (this.exit.wasPickedUp()){
				gameEvents.levelFinished();
				break;
			}
		}
	}
	
	

	/******************************* LOAD SAVE AND SERIALIZE ***************************************************/
	private void loadFromNewMap(Element parent) {
		this.loadFromSavedMap(parent);
		this.addExit();
	}
	
	private void loadFromSavedMap(Element parent) {
		this.cells = this.createEmptyMap();
		this.occupiedCells = new ArrayList<Cell>();
		
        for (Iterator<Element> i = parent.elementIterator(); i.hasNext();) {
            Element elementCell = (Element) i.next();
            
        	int x = Integer.parseInt(elementCell.attributeValue("x"));
        	int y = Integer.parseInt(elementCell.attributeValue("y"));
        	
        	Position pos = new Position(x, y);
        	Cell cell = new Cell(pos);
        	cell.unserialize(elementCell, this, pos);
        	
        	this.occupiedCells.add(cell);
        }

		for (int index = 0; index < this.occupiedCells.size(); index++){
			Cell newCell = (Cell) this.occupiedCells.get(index);
			this.replaceCell(newCell.getPosition(), newCell);
		}
	}
	
	// Saves the map in a XML file
	public void saveXML(String file) {
		Document document = DocumentHelper.createDocument();
		
		this.serialize(document);
		
		XMLWriter writer;
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint(); 
			writer = new XMLWriter(new FileWriter(file), format);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void serialize(Document document) {
	
		Element map = document.addElement("map");
		map.addAttribute("type", "saved"); // In this case, we save the game
		map.addAttribute("mapnumber", Integer.toString(this.mapNumber)); // The number of the map, if it is cero, it is the first map
		map.addAttribute("width", Integer.toString(this.width));
		map.addAttribute("height", Integer.toString(this.height));
		
		Iterator<Cell> itr = this.iterator();
		
		while(itr.hasNext())
			itr.next().serialize(map);
		
	}
/**************************************************************************************************************/	

	
/*************************** MAP ITERATOR **************************************/
	public Iterator<Cell> iterator(){
		Iterator<Cell> itr = new MapIterator(this.cells);
		return itr;
	}

	private class MapIterator implements Iterator<Cell> {

		ArrayList<ArrayList<Cell>> matrix;
		Iterator<ArrayList<Cell>> xItr;
		Iterator<Cell> yItr;
		
		public MapIterator (ArrayList<ArrayList<Cell>> matrix){
			
			this.matrix = matrix;
			Iterator<ArrayList<Cell>> xItr = matrix.iterator();
			ArrayList<Cell> firstColumn = xItr.next();
			Iterator<Cell> yItr = firstColumn.iterator();
			this.xItr = xItr;
			this.yItr = yItr;
		}
		
		@Override
		public boolean hasNext() {
			if (this.yItr.hasNext())
				return true;
			else
				if (xItr.hasNext())
					return true;
			return false;
		}

		@Override
		public Cell next() {
			if (this.yItr.hasNext())
				return yItr.next();
			else
				if (this.xItr.hasNext()){
					//Updates the yItr attribute with a new column of the matrix.
					ArrayList<Cell> newColumn = xItr.next();
					Iterator<Cell> yItr = newColumn.iterator();
					this.yItr = yItr;
					return this.yItr.next();
				}
			return null;
					
		}

		@Override
		public void remove() {
			this.yItr.remove();
		}

	}

}
	
