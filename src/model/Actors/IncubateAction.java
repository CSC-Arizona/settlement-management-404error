package model.Actors;

import model.Furniture.Furniture;
import model.Game.Game;
import model.Items.DragonEggItem;
import model.Map.Map;

//Author: Maxwell Faridian
//This class defines an Incubate Action, where an actor just sits and waits, because they are incubating
public class IncubateAction extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6706123216153923355L;
	
	private static int incubationPeriod;
	
	public IncubateAction(int ticker) {
		incubationPeriod = ticker;
	}

	@Override
	public int execute(Actor performer) {
		//Check to see if done incubating
		if(incubationPeriod == 999) {
			performer.setAlive(true, false);
			Map map = Game.getMap();
			map.addPlayerToMap(performer);
			
			//Remove egg from incubation chamber
			Furniture incubationChamber = Game.getMap().getBuildingBlock(performer.getPosition()).getFurniture();
			if(incubationChamber.getRemainingWeightCapacity() == 0) {
				incubationChamber.removeItem(new DragonEggItem());
			}
		}

		return Action.COMPLETED;
	}

}
