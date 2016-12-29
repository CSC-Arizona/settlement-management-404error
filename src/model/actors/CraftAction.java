package model.actors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.game.Log;
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
	private Inventory currInv;
	private String text;
	
	public CraftAction(Item craftableItem, List<Item> req, String text) {
		toBeCrafted = craftableItem;
		requiredMaterials = new ArrayList<>(req);
		this.text = text;
	}


	//If actor doesn't have resources, send him to store room to get them
	@Override
	public int execute(Actor performer) {
		
		if (hasAllRequiredMaterials(performer)) {
			currInv.addItem(toBeCrafted);
			if (currInv.contains(requiredMaterials.get(0)))
				currInv.removeItem(0);
			String result = "" + performer.getName() + " has crafted a " + text;
		}
		String result = "Nobody had the required materials to craft the " + text + ". Gather more materials and try again.";
		Log.add(result);
		return Action.COMPLETED;
	}
	
	private boolean hasAllRequiredMaterials(Actor performer) {
		currInv = performer.getInventory();
		for (Item i : requiredMaterials) {
			if (!currInv.contains(i))
				return false;
		}
		return true;
	}

}
