package model.furniture;

import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.items.Item;
import model.menus.PrintableItemsList;

/**
 * The constructor is passed the list of items required to build a room. As actors obtain and 
 * add required materials to the pile, the items are removed from the list of necessary items.
 * Once the list is empty, the room can be built.
 * 
 * @author Katherine Walters
 *
 */
public class ConstructionMaterialPile extends Furniture {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1307346131434546224L;
	// list of items that are still required to complete the Construction material pile
	private volatile List<Item> reqMaterials;
	PrintableItemsList pil;
	
	public ConstructionMaterialPile(List<Item> list) {
		super(100, 100, "Construction material pile", null);
		pil = new PrintableItemsList();
		reqMaterials = list;
		if (list != null) {
		    for (Item i : list)
		    	pil.addItem(i);
		}
	}
	
	public boolean isCompleted() {
		return reqMaterials.isEmpty();
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return reqMaterials;
	}

	@Override
	public String toString() {
		return "Construction materials still required: " + pil.toString();
	}
	
	/*
	 * throw Item "toAdd" on the pile, which means it's removed from the list of items still required.
	 * Returns true if the item was needed and added to the pile, and false if it wasn't needed.
	 * (non-Javadoc)
	 * @see model.furniture.Furniture#addItem(model.items.Item)
	 */
	public boolean addItem(Item toAdd) {
		for (Item i : reqMaterials) {
			if (i.getClass().equals(toAdd.getClass())) {
				reqMaterials.remove(i);
				pil.removeItem(i);
				return true;
			}	
		}
		return false;
	}
}
