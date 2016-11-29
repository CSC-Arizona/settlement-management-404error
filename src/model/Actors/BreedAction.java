package model.Actors;

import java.util.HashMap;

import model.Furniture.Furniture;
import model.Game.Game;
import model.Items.DragonEggItem;
import model.Items.Item;

//Author: Maxwell Faridian
//This class will have 2 actors move to the incubation room. Once there, if there is room available in one of the incubation
//chambers, the actors will "create" a new egg item and place it in an empty incubation chamber
public class BreedAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2168888324400311918L;

	private Actor mate; // Need to make sure other actor is in incubation room
						// with performer
	private Position incubationChamberPos;

	public BreedAction(Actor mate) {
		this.mate = mate;
		incubationChamberPos = getEmptyIncubationChamber();
	}

	// This method finds an empty incubation chamber that the actor(s) can move
	// towards
	private Position getEmptyIncubationChamber() {
		HashMap<Furniture, Position> mapFurniture = Game.getMap().getFurniture();
		if (mapFurniture != null) {
			for (Furniture f : mapFurniture.keySet()) {
				if (f.getID().equals("incubation chamber")) {
					if (f.getRemainingWeightCapacity() == 3) {
						return mapFurniture.get(f);
					}
				}
			}
		}
		return null;
	}

	@Override
	public int execute(Actor performer) {
		if (incubationChamberPos != null) {
			if (mate.getPosition().equals(incubationChamberPos)) {
				// test to see that the two actors are in the same position
				int x = Math.abs(performer.getPosition().getCol() - mate.getPosition().getCol());
				int y = Math.abs(performer.getPosition().getRow() - mate.getPosition().getRow());
				// if in same position, breed, else have performer move towards mate
				if ((x == 0) && (y == 0)) {
					Furniture incubationChamber = Game.getMap().getBuildingBlock(incubationChamberPos).getFurniture();
					Item egg = new DragonEggItem();
					if (incubationChamber != null) {
						if (incubationChamber.getRemainingWeightCapacity() == egg.getWeight()) {
							incubationChamber.addItem(egg); // Egg is in
															// incubation
															// chamber,
															// remaining
															// weightCapacity
															// should be 0 now
						}
					}
					//TODO: Create new player controlled actor? //new Actor(100, incubationChamberPos);
					return Action.COMPLETED; 

					// Move performer towards incubation chamber
				} else {
					int action = new MoveAction(incubationChamberPos).execute(performer);
					if (action == Action.COMPLETED)
						return Action.MADE_PROGRESS;
					else
						return action;
				}
			}
			// Move mate towards incubation chamber
			else {
				int action = new MoveAction(incubationChamberPos).execute(mate);
				if (action == Action.COMPLETED)
					return Action.MADE_PROGRESS;
				else
					return action;
			}
		}
		return 0;
	}

	// TODO: Implement hatching scenario (remove egg from chamber, create new
	// actor), implement wait time for egg to hatch when egg is placed in chamber

}
