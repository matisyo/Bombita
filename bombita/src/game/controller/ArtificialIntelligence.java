package game.controller;


import java.util.ArrayList;
import java.util.Random;

import game.model.Weapon;
import java.util.Iterator;
import game.model.Action;
import game.model.CollidableElement;
import game.model.Explosion;
import game.model.Map;
import game.model.Position;
import game.model.Character;
import game.model.actions.*;


public class ArtificialIntelligence {
	
	private Map map;
	private Character character;
	
	public ArtificialIntelligence(Map map, Character character){
		this.map = map;
		this.character = character;
		
	}
	
	public Action getAction(){
		Position charPos = this.character.getPosition();		
		Action action= new ActionMoveLeft();
		
		//checks all the cells near the character
		for(int i = (charPos.getX() - 4);i < (charPos.getX() + 4); i++){
			for(int j = (charPos.getY() - 4); j < (charPos.getY() + 4); j++){
				Position pos = new Position(i,j);
				if((i < this.map.getHeight()) && (i >= 0) && (j >= 0 && (j < this.map.getWidth()))){
					ArrayList<CollidableElement> elements = this.map.positionGetElements(pos);
					Iterator<CollidableElement> itElem = elements.iterator();
					boolean hasBomb = false;
					
					while(itElem.hasNext()){
						//if there is a bomb or explosion near, it goes away from it
						CollidableElement elem = itElem.next();
						if((elem instanceof Weapon) || (elem instanceof Explosion)){
							if(pos.getX() > charPos.getX()) action = new ActionMoveDown(); //danger is to the right, moves down
							else if(pos.getX() < charPos.getX()) action = new ActionMoveUp(); //danger is to the left, moves up
							else if(pos.getY() > charPos.getY()) action = new ActionMoveLeft(); //danger is down, moves left
							else if(pos.getY() < charPos.getY()) action = new ActionMoveRight(); //danger is up, moves right
							else if(pos.equals(charPos.getX())) action = new ActionMoveDown();
							hasBomb = true;
							break;
						}						
					}
					
					// if there isn�t any bombs or explosions near, it just does a random action
					if(!hasBomb){
						ArrayList <Action> actionList = new ArrayList <Action>();
						
						actionList.add(new ActionMoveDown());
						actionList.add(new ActionMoveLeft());
						actionList.add(new ActionMoveRight());
						actionList.add(new ActionMoveUp());
						
						
						Random rand = new Random();
						int randomNumb = rand.nextInt(100);

						// Percent of chances it will plant a bomb
						if (randomNumb < 3)
							action = new ActionPlantBomb(); 
						// 30% chance of not moving
						else if ((randomNumb > 3) && (randomNumb < 90))
							action = new ActionDoNothing();
						
						else
							action = actionList.get(randomNumb % 3);
					}
									
				}
			}
		}
				
		return action;
	}
}
