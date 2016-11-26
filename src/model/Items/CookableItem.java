package model.Items;

import java.util.List;

public abstract class CookableItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899243135140664528L;

	public CookableItem(int healthPts) {
		super(true, 2, healthPts, 2);
	}
	
	abstract public List<Item> getRequiredIngredients();

}
