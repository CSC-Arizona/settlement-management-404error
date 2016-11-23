package model.Actors;

import model.Map;

/**
 * @author Ethan Ward
 * 
 * 
 *
 */
public class SleepAction implements Action {

	
	@Override
	public int execute(Actor performer) {
		Position nearestBed = performer.getNearestBed();
		performer.setFatigue(0);
		if (nearestBed != null) {
			performer.addToActionQueue(new MoveAction(nearestBed));
		}
		return Action.COMPLETED;
	}

}
