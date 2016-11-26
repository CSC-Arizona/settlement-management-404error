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
public class SleepAction extends Action {
	
	// github.com/CSC-Arizona/settlement-management-404error
	@Override
	public int execute(Actor performer) {
		Position nearestBed = getNearestBed(performer);
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
	
	public Position getNearestBed(Actor performer) {
		HashMap<Furniture, Position> mapFurniture = Game.getMap().getFurniture();
		double closest = Integer.MAX_VALUE;
		Position nearest = null;
		if (mapFurniture != null) {
			if (mapFurniture.size() != 0) {
				for(Furniture f : mapFurniture.keySet()){
					int x = performer.getPosition().getCol(), x2 = mapFurniture.get(f).getCol(),
							y = performer.getPosition().getRow(), y2 = mapFurniture.get(f).getRow();
					double distance = Math.sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y));
					if(distance < closest){
						closest = distance;
						nearest = mapFurniture.get(f);
					}
				}
				return nearest;
			}
		}
		return null;
	}
}
