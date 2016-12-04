package model.actors;

import java.util.HashMap;

import model.furniture.Furniture;
import model.game.Game;

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
				performer2.setFatigue(performer2.getFatigue() - 50);
				if(performer2.getFatigue() <= 0){
					performer2.setFatigue(0);
					return Action.COMPLETED;
				} else {
					return Action.MADE_PROGRESS;
				}
			} else {
				new MoveAction(nearestBed).execute(performer);
				return Action.MADE_PROGRESS;
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
					if(!f.getID().equals("bed"))
						continue;
					double distance = performer.getPosition().distance(mapFurniture.get(f));
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
