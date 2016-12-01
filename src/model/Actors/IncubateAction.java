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
	
	private int incubationPeriod;
	
	public IncubateAction(int ticker) {
		incubationPeriod = ticker;
		System.out.println("New Incubate Action " + ticker);
	}

	@Override
	public int execute(Actor performer) {
		//Check to see if done incubating
		//TODO: Change back to higher value
		
		//This next line is never printing
		System.out.println(incubationPeriod);
		if(incubationPeriod == 49) {
			performer.setAlive(true, false);
			Map map = Game.getMap();
			//map.addPlayerToMap(performer);
			
			//Remove egg from incubation chamber
			Furniture incubationChamber = Game.getMap().getBuildingBlock(performer.getPosition()).getFurniture();
			if(incubationChamber.getRemainingWeightCapacity() == 0) {
				incubationChamber.removeItem(new DragonEggItem());
			}
			System.out.println("Actor should be on map now");
		}

		return Action.COMPLETED;
	}

}
