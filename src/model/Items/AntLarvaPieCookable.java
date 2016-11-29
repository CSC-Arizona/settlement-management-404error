package model.Items;

import java.util.LinkedList;
import java.util.List;

public class AntLarvaPieCookable extends CookableItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6230505512703031570L;

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

	@Override
	public String toString() {
		return "Ant larva pie (heals 10 hp)";
	}
}
