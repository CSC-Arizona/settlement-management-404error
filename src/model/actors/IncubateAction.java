package model.actors;

import model.furniture.Furniture;
import model.game.Game;
import model.items.DragonEggItem;
import model.map.Map;

//Author: Maxwell Faridian
//This class defines an Incubate Action, where an actor just sits and waits, because they are incubating
public class IncubateAction extends Action {
	private static final long serialVersionUID = -6706123216153923355L;
	
	private int incubationPeriod;
	//TODO: Change value back
	//private static final int INCUBATION_TIME = 500;
	private static final int INCUBATION_TIME = 100;
	
	@Override
	public int execute(Actor performer) {
		incubationPeriod++;
		if(incubationPeriod >= INCUBATION_TIME){
			performer.setAlive(true, false);
			Furniture incubationChamber = Game.getMap().getBuildingBlock(performer.getPosition()).getFurniture();
			incubationChamber.removeItem(new DragonEggItem());
			//Add pca to map
			
			//TODO: WHat does removing this line do?
			//Game.getMap().addPlayerToMap(performer);
			//Actor.allActors.add(performer);
			return Action.COMPLETED;
		}
		return Action.MADE_PROGRESS;
	}

}
