/**
 * 
 */
package model.actors;

import java.util.Iterator;

/**
 * The action used by the opposition to hunt and kill the play controlled
 * characters
 * 
 * @author Jonathon Davis
 *
 */
public class EnemyHuntAction extends Action {

	private static final long serialVersionUID = 7268347888796294194L;
	private Actor prey;
	private AttackAction attack;

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.actors.Action#execute(model.actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		Actor prey2 = findNearestPlayerActor(performer);
		if (prey2 != null) {
			if (attack == null || !prey.equals(prey2)){
				prey = prey2;
				attack = new AttackAction(prey);
			}
			return attack.execute(performer);
		} else {
			return Action.Pool;
		}
	}

	private Actor findNearestPlayerActor(Actor performer) {
		Actor nearestActor = null;
		double closest = Integer.MAX_VALUE;
		Iterator<Actor> iter = PlayerControlledActor.allActors.iterator();

		while (iter.hasNext()) {
			Actor p = iter.next();
			double distance = performer.getPosition().distance(p.getPosition());
			if (distance < closest && p.getHealth() > 0) {
				closest = distance;
				nearestActor = p;
			}
		}
		return nearestActor;
	}

}
