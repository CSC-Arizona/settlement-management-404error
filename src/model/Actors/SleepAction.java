package model.Actors;

import java.util.HashMap;

import model.Furniture.Furniture;
import model.Game.Game;

/**
 * @author Ethan Ward
 * 
 * 
 *
 */
public class SleepAction extends Action {
	
	private static final long serialVersionUID = -7720187199323765207L;

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
		return Action.Pool;
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