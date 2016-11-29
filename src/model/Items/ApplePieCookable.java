package model.Items;

import java.util.LinkedList;
import java.util.List;

public class ApplePieCookable extends CookableItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5921506440012280810L;

	public ApplePieCookable() {
		super(10);
	}

	@Override
	public List<Item> getRequiredIngredients() {
		List<Item> reqIngredients = new LinkedList<>();
		for (int i = 0; i < 3; i++)
		    reqIngredients.add(new AppleItem());
		reqIngredients.add(new WheatItem());
		return reqIngredients;
	}

	@Override
	public String toString() {
		return "Apple pie (heals 10 hp)";
	}

}
