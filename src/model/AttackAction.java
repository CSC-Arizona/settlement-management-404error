package model;

/**
 * @author Jonathon Davis
 *
 */
public class AttackAction implements Action {
	private Actor target;
	
	public AttackAction(Actor target){
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public boolean execute(Actor performer) {
		// test to see that the two actors are adjacent (no fighting across the map)
		int x = Math.abs(performer.getPosition().x - target.getPosition().x);
		int y = Math.abs(performer.getPosition().y - target.getPosition().y);
		
		//if adjacent fight, else move towards target
		if((x == 1 || x == 0) && (y == 1 || y == 0)){
			//TODO: add actual combat system
			target.setHealth(target.getHealth()-1);
			if(target.getHealth() <= 0)
				return true;
			else
				return false;
		} else {
			return new MoveAction(target.getPosition()).execute(performer);
		}
	}
	

}
