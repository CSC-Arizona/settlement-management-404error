/**
 * 
 */
package model;

import java.awt.Point;

/**
 * @author Jonathon Davis
 *
 */
public class MoveAction implements Action {
	private Point desiredDestination;
	
	public MoveAction(Point desiredDestination){
		this.desiredDestination = desiredDestination;
	}

	/* (non-Javadoc)
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public boolean execute(Actor performer) {
		int x;
		int y;
		
		//TODO: Add pathfinding and understand obstacles
		
		//Move in the x direction
		if(performer.getPosition().x < desiredDestination.x)
			x = performer.getPosition().x + 1;
		else if (performer.getPosition().x > desiredDestination.x)
			x = performer.getPosition().x - 1;
		else
			x = performer.getPosition().x;
		
		/*TODO: since the game is vertical the y will move
		 * down until it hits an obstacle such as ground
		 */
		if(performer.getPosition().y < desiredDestination.y)
			y = performer.getPosition().y + 1;
		else if (performer.getPosition().y > desiredDestination.y)
			y = performer.getPosition().y - 1;
		else
			y = performer.getPosition().y;
		
		Point newLocation = new Point(x,y);
		performer.setPosition(newLocation);
		
		// return whether or not the task is complete
		return performer.getPosition().equals(desiredDestination);
	}

}
