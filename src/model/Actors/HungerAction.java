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
public class HungerAction implements Action {

	/* (non-Javadoc)
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public boolean execute(Actor performer) {
		Iterator<Item> i = performer.getInventory().iterator();
		while(i.hasNext()){
			Item item = i .next();
			if(item.getIsEdible()){
				i.remove();
				PlayerControlledActor actor = (PlayerControlledActor) performer;
				actor.setHunger(actor.getHunger() - 500);
				return true;
			}
		}
		return false;
	}

}
