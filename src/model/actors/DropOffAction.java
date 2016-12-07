/**
 * 
 */
package model.actors;

import model.game.Game;

/**
 * @author Jonathon Davis
 *
 */
public class DropOffAction extends Action {
	private MoveAction move;

	/* (non-Javadoc)
	 * @see model.actors.Action#execute(model.actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		if(move == null)
			move = new MoveAction(Game.getMap().ship);
		int action = move.execute(performer);
		if(action == Action.COMPLETED){
			performer.getInventory().removeItem(0);
			return action;
		}
		return Action.MADE_PROGRESS;
	}

}
