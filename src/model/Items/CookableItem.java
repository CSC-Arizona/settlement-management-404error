package model.Items;

import java.util.List;

import model.Menus.RequiredItemsList;

public abstract class CookableItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899243135140664528L;
	private RequiredItemsList ril;

	public CookableItem(int healthPts) {
		super(true, 2, healthPts, 2);
	}
	
	public String reqMaterialsToString() {
		if (this.ril == null) {
			ril = new RequiredItemsList();
			for (Item i : this.getRequiredIngredients())
				ril.addItem(i);
		}
		return ril.toString();
	}
	
	abstract public List<Item> getRequiredIngredients();

}
