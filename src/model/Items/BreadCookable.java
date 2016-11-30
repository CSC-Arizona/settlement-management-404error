package model.Items;

import java.util.LinkedList;
import java.util.List;

public class BreadCookable extends CookableItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914974809369066714L;

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

	@Override
	public String toString() {
		return "Bread (heals 10 hp)";
	}
}
