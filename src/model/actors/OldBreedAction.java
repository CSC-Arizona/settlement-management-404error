package model.actors;

import java.util.HashMap;

import model.furniture.Furniture;
import model.game.Game;
import model.items.DragonEggItem;
import model.items.Item;
import model.map.Map;

//Author: Maxwell Faridian
//This class will have 2 actors move to the incubation room. Once there, if there is room available in one of the incubation
//chambers, the actors will "create" a new egg item and place it in an empty incubation chamber
public class OldBreedAction extends Action {

	private static final long serialVersionUID = -2168888324400311918L;

	private Actor mate; // Need to make sure other actor is in incubation room
						// with performer
	private Position incubationChamberPos;
	
	private MoveAction action;

	public OldBreedAction(Actor mate) {
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
				if (mate.getPosition().distance(performer.getPosition()) == 0) {
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
					// Add new Player Controlled Actor to map
					Map map = Game.getMap();
					map.addPlayerActor(incubationChamberPos);

					return Action.COMPLETED;

					// Move performer towards incubation chamber
				} else {
					if(action == null)
						action = new MoveAction(incubationChamberPos);
					int result = action.execute(performer);
					if (result == Action.COMPLETED)
						return Action.MADE_PROGRESS;
					else
						return result;
				}
			}
			// Move mate towards incubation chamber
			else {
				System.out.print("New move action");
				int action = new MoveAction(incubationChamberPos).execute(mate);
				if (action == Action.COMPLETED)
					return Action.MADE_PROGRESS;
				else
					return action;
			}
		} else {
			// TODO: Build new Incubation room
		}
		return 0;
	}

	// TODO: Test Breed and Incubate actions

}
