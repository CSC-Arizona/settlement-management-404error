package model.Items;

import java.util.List;

public abstract class CookableItem extends Item {

	public CookableItem(int healthPts) {
		super(true, 2, healthPts, 2);
	}
	
	abstract public List<Item> getRequiredIngredients();

}
