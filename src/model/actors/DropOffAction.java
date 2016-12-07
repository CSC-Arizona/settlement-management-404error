/**
 * 
 */
package model.actors;

import model.game.Game;
import model.items.Item;

/**
 * @author Jonathon Davis
 *
 */
public class DropOffAction extends Action {
	private static final long serialVersionUID = -7726169581134590920L;
	private MoveAction move;
	private Item item;

	public DropOffAction(Item item) {
		this.item = item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.actors.Action#execute(model.actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		if (move == null)
			move = new MoveAction(Game.getMap().ship);
		int action = move.execute(performer);
		if (action == Action.COMPLETED) {
			performer.getInventory().removeItem(item);
			PlayerControlledActor.remaingParts--;
			Game.getMap().setSpaceShipLights(PlayerControlledActor.remaingParts);
			return action;
		}
		return Action.MADE_PROGRESS;
	}
}
