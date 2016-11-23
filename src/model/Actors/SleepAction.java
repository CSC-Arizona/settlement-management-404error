package model.Actors;

import model.Map;

/**
 * @author Ethan Ward
 * 
 * 
 *
 */
public class SleepAction implements Action {

	private Map map;
	
	public SleepAction(Map map) {
		this.map = map;
	}
	
	@Override
	public int execute(Actor performer) {
		Position nearestBed = performer.getNearestBed();
		performer.setFatigue(0);
		if (nearestBed != null) {
			performer.addToActionQueue(new MoveAction(nearestBed, map));
		}
		return Action.COMPLETED;
	}

}
