package model.actors;

import model.furniture.Furniture;
import model.game.Game;
import model.items.DragonEggItem;

//Author: Maxwell Faridian
//This class defines an Incubate Action, where an actor just sits and waits, because they are incubating
public class IncubateAction extends Action {
	private static final long serialVersionUID = -6706123216153923355L;
	
	private int incubationPeriod;
	//TODO: Change value back
	//private static final int INCUBATION_TIME = 500;
	private static final int INCUBATION_TIME = 150;
	
	@Override
	public int execute(Actor performer) {
		incubationPeriod++;
		if(incubationPeriod >= INCUBATION_TIME){
			performer.setAlive(true, false);
			((PlayerControlledActor) performer).setFatigue(0);
			((PlayerControlledActor) performer).setHunger(0);
			Furniture incubationChamber = Game.getMap().getBuildingBlock(performer.getPosition()).getFurniture();
			incubationChamber.removeItem(new DragonEggItem());
			return Action.COMPLETED;
		}
		return Action.MADE_PROGRESS;
	}

}
