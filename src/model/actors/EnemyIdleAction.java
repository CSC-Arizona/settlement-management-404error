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

		// if move == null find random move location
		if (move == null) {
			ArrayList<Position> valid = new ArrayList<>();
			for (int x = performer.getPosition().getCol() - 10; x < performer.getPosition().getCol() + 10; x++) {
				for (int y = performer.getPosition().getRow() - 10; y < performer.getPosition().getRow() + 10; y++) {
					if (y >= Game.getMap().getTotalHeight() || y < 0)
						continue;
					Position pos = new Position(y, Math.floorMod(x, Game.getMap().getTotalWidth()));
					if (Game.validActorLocation(pos.getRow(), pos.getCol())
							&& Game.getMap().getBuildingBlock(pos).getID().equals("Ant tunnel")
							&& !pos.equals(performer.getPosition())) {
						valid.add(pos);
					}
				}
			}
			if (valid.size() > 0)
				move = new MoveAction(valid.get(rand.nextInt(valid.size())));
		}
		if (move != null)
			return move.execute(performer);
		else
			return Action.MADE_PROGRESS;
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
