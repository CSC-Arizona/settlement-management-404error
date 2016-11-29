/**
 * 
 */
package model.Actors;

import java.util.Iterator;

import model.Items.Item;

/**
 * An action where the actor will attempt to fufil the Hunger need
 * @author Jonathon
 *
 */
public class HungerAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -780586674243835086L;

	/* (non-Javadoc)
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		Iterator<Item> i = performer.getInventory().iterator();
		// Look through the inventory for food, if there is some, eat it
		while(i.hasNext()){
			Item item = i .next();
			if(item.getIsEdible()){
				i.remove();
				PlayerControlledActor actor = (PlayerControlledActor) performer;
				actor.setHunger(actor.getHunger() - 500);
				return Action.COMPLETED;
			}
		}
		return Action.Pool;
	}

}