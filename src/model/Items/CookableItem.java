package model.Items;

import java.util.List;

import model.Menus.PrintableItemsList;

public abstract class CookableItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899243135140664528L;
	private PrintableItemsList ril;

	public CookableItem(int healthPts) {
		super(true, 2, healthPts, 2);
	}
	
	public String reqMaterialsToString() {
		if (this.ril == null) {
			ril = new PrintableItemsList();
			for (Item i : this.getRequiredIngredients())
				ril.addItem(i);
		}
		return ril.toString();
	}
	
	abstract public List<Item> getRequiredIngredients();

}
