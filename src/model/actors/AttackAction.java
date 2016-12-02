package model.actors;

/**
 * @author Jonathon Davis
 *
 */
public class AttackAction extends Action {

	private static final long serialVersionUID = -2606344516527539211L;
	private Actor target;

	public AttackAction(Actor target) {
		this.target = target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// test to see that the two actors are adjacent (no fighting across the
		// map)
		int x = Math.abs(performer.getPosition().getCol() - target.getPosition().getCol());
		int y = Math.abs(performer.getPosition().getRow() - target.getPosition().getRow());
		if (target.getHealth() <= 0)
			return Action.COMPLETED;

		// if adjacent fight, else move towards target
		if ((x == 1 || x == 0) && (y == 1 || y == 0)) {
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
			int action = new MoveAction(target.getPosition()).execute(performer);
			if (action == Action.COMPLETED)
				return Action.MADE_PROGRESS;
			else
				return action;
		}
	}

}
