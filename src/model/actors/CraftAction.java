package model.actors;

import java.util.Iterator;
import java.util.List;

import model.items.Item;

/**
 * 
 * @author Maxwell Faridian
 * @author Jonathon Davis
 *
 */
public class CraftAction extends Action {
	
	private static final long serialVersionUID = -5666712529302552421L;
	private Item toBeCrafted;
	private List<Item> requiredMaterials;
	
	public CraftAction(Item craftableItem, List<Item> requiredMaterials) {
		toBeCrafted = craftableItem;
		this.requiredMaterials = requiredMaterials;
	}


	//If actor doesn't have resources, send him to store room to get them
	@Override
	public int execute(Actor performer) {
		if(requiredMaterials != null) {
			Iterator<Item> remaining = requiredMaterials.iterator();
			boolean changed = false;
			while(remaining.hasNext()){
				Item required = remaining.next();
				if(performer.getInventory().contains(required)){
					performer.getInventory().removeItem(required);
					remaining.remove();
					changed = true;
				}
			}
			if(requiredMaterials.size() > 0){
				if(changed)
					return Action.MADE_PROGRESS;
				else
					return Action.DELAY;
			}
			
		}
		//Add new crafted item to performer's inventory
		performer.getInventory().addItem(toBeCrafted);
		
		return Action.COMPLETED;
	}

}
