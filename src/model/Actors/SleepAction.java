package model.Actors;

import java.util.HashMap;

import model.Game;
import model.Furniture.Furniture;

/**
 * @author Ethan Ward
 * 
 * 
 *
 */
public class SleepAction implements Action {
	
	// github.com/CSC-Arizona/settlement-management-404error
	@Override
	public int execute(Actor performer) {
		Position nearestBed = getNearestBed();
		PlayerControlledActor performer2 = (PlayerControlledActor)performer;
		if (nearestBed != null) {
			performer.addToActionQueue(new MoveAction(nearestBed));

			// if in same position as bed, sleep, otherwise move
			if (performer.getPosition().equals(nearestBed)) {
				performer2.setFatigue(0);
				return Action.COMPLETED;
			} else {
				int action = new MoveAction(nearestBed).execute(performer);
				if (action == Action.COMPLETED) {
					return Action.MADE_PROGRESS;
				}
				return action;
			}
		}
		return Action.CANCELL;
	}
	
	public Position getNearestBed() {
		HashMap<Furniture, Position> mapFurniture = Game.getMap().getFurniture();
		if (mapFurniture != null) {
			if (mapFurniture.size() != 0) {
				return mapFurniture.get(mapFurniture.keySet().toArray()[0]);
			}
		}
		return null;
	}
}
