/**
 * 
 */
package model.actors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.game.Game;

/**
 * @author Jonathon Davis
 *
 */
public class EnemyIdleAction extends Action {
	private static final long serialVersionUID = -7631696645491230540L;
	private static Random rand = new Random();
	private MoveAction move;

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.actors.Action#execute(model.actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		// defend the hive
		Actor near = findNearestPlayerActor(performer);
		if (near != null && performer.getPosition().distance(near.getPosition()) < 20
				&& Game.getMap().getBuildingBlock(near.getPosition()).getID().equals("Ant tunnel")) {
			new AttackAction(near).execute(performer);
			return Action.MADE_PROGRESS;
		}

		if (move == null)
			move = new MoveAction(EnemyActor.antTunnels.get(rand.nextInt(EnemyActor.antTunnels.size())));

		if (move != null)
			return move.execute(performer);
		else
			return Action.MADE_PROGRESS;
	}

	private Actor findNearestPlayerActor(Actor performer) {
		Actor nearestActor = null;
		double closest = Integer.MAX_VALUE;
		Iterator<PlayerControlledActor> iter = PlayerControlledActor.allActors.iterator();

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
