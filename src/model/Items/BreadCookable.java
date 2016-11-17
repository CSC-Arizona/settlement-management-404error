package model.Items;

import java.util.LinkedList;
import java.util.List;

public class BreadCookable extends CookableItem {

	public BreadCookable() {
		super(10);
	}

	@Override
	public List<Item> getRequiredIngredients() {
		List<Item> reqIngredients = new LinkedList<>();
		reqIngredients.add(new WheatItem());
		reqIngredients.add(new WheatItem());
		return reqIngredients;
	}
}
