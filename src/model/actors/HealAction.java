/**
 * 
 */
package model.actors;

import java.util.HashMap;

import model.furniture.Furniture;
import model.game.Game;

/**
 * @author Jonathon Davis
 *
 */
public class HealAction extends Action {
	private static final long serialVersionUID = 3940458941003972499L;

	@Override
	public int execute(Actor performer) {
		Position nearestBed = getNearestBed(performer);
		PlayerControlledActor performer2 = (PlayerControlledActor)performer;
		if (nearestBed != null) {
			performer.addToActionQueue(new MoveAction(nearestBed));

			// if in same position as bed, sleep, otherwise move
			if (performer.getPosition().equals(nearestBed)) {
				performer2.setHealth(performer2.getHealth() + 10);
				if(performer2.getHealth() >= 10){
					performer2.setHealth(10);
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
					if(!f.getID().equals("healing bed"))
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
