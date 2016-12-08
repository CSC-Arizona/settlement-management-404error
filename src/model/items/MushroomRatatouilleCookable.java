package model.items;

import images.ImageEnum;

import java.util.LinkedList;
import java.util.List;

public class MushroomRatatouilleCookable extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5470812726511462446L;

	public MushroomRatatouilleCookable() {

		super(true, 0, 100, 10.5, ImageEnum.APPLEPIE);

	}

	public static List<Item> getRequiredMaterials() {
		List<Item> reqIngredients = new LinkedList<>();
		for (int i = 0; i < 3; i++)
			reqIngredients.add(new MushroomFruitItem());
		reqIngredients.add(new WheatItem());
		return reqIngredients;
	}

	@Override
	public String toString() {
		return "Mushroom ratatouille";
	}

}
