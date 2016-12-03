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
		if (target.getHealth() <= 0)
			return Action.COMPLETED;

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
