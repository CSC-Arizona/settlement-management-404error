package model.actors;

/**
 * @author Jonathon Davis
 *
 */
public class AttackAction extends Action {

	private static final long serialVersionUID = -2606344516527539211L;
	private Actor target;
	private Position previousLocation;
	private MoveAction move;

	public AttackAction(Actor target) {
		this.target = target;
		previousLocation = target.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public int execute(Actor performer) {
<<<<<<< HEAD:src/model/Actors/AttackAction.java
		// test to see that the two actors are adjacent (no fighting across the
		// map)
		
		int x = Math.abs(performer.getPosition().getCol() - target.getPosition().getCol());
		int y = Math.abs(performer.getPosition().getRow() - target.getPosition().getRow());
=======
		if (target.getHealth() <= 0)
			return Action.COMPLETED;
>>>>>>> a678d7c4addc6c1b8d57d87eda252190a69e027e:src/model/actors/AttackAction.java

		if (target.getHealth() <= 0)
			return Action.COMPLETED; // moved this from below the x,y declaration
        
		// if adjacent fight, else move towards target
		if (performer.getPosition().isAdjacent(target.getPosition())) {
			// TODO: add actual combat system
			int damage = 1 + performer.getSkills().getCombatLevel();
			target.setHealth(target.getHealth() - damage);
			performer.getSkills().addCombatXP(damage);
			if (target.getHealth() <= 0) {
				target.setHealth(0);
				return Action.COMPLETED;
			} else
				return Action.MADE_PROGRESS;
		} else {
			if (target.getPosition().equals(previousLocation) && move != null) {
				move.execute(performer);
			} else {
				move = new MoveAction(target.getPosition());
				previousLocation = target.getPosition();
				move.execute(performer);
			}
			return Action.MADE_PROGRESS;
		}
	}

}
