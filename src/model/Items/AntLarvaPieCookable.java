package model.Items;

import java.util.LinkedList;
import java.util.List;

public class AntLarvaPieCookable extends CookableItem {
	
	public AntLarvaPieCookable() {
		super(10);
	}

	@Override
	public List<Item> getRequiredIngredients() {
		List<Item> reqIngredients = new LinkedList<>();
		for (int i = 0; i < 3; i++)
		    reqIngredients.add(new AntLarvaItem());
		reqIngredients.add(new WheatItem());
		return reqIngredients;
	}
}
