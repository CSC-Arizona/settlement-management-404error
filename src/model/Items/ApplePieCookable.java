package model.Items;

import java.util.LinkedList;
import java.util.List;

public class ApplePieCookable extends CookableItem {

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

}