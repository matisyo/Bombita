package game.controller;


import game.model.Character;
import game.model.Action;
import game.model.Map;

public class RandomBehavior {
	
	private ArtificialIntelligence AI; //this class provides an Action
	
	public RandomBehavior(Map map, Character character){
		this.AI = new ArtificialIntelligence(map, character);
	}
	
	public Action generateAction(){
		return this.AI.getAction();
	}
}
