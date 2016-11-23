package model.Actors;

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
		Position nearestBed = performer.getNearestBed();
		performer.setFatigue(0);
		if (nearestBed != null) {
			performer.addToActionQueue(new MoveAction(nearestBed));

			// if in same position as bed, sleep, otherwise move
			if (performer.getPosition().equals(nearestBed)) {
				performer.setFatigue(0);
				return Action.COMPLETED;
			} else {
				int action = new MoveAction(nearestBed).execute(performer);
				if (action == Action.COMPLETED) {
					return Action.MADE_PROGRESS;
				}
				return action;
			}
		}
		return 0;

	}
}
