package model.items;

import images.ImageEnum;

import java.util.LinkedList;
import java.util.List;

public class ApplePieCookable extends Item { //CookableItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5921506440012280810L;

	public ApplePieCookable() {
		super(true, 0, 10, 0.5, ImageEnum.APPLEPIE);
	}

	public static List<Item> getRequiredMaterials() {
		List<Item> reqIngredients = new LinkedList<>();
		for (int i = 0; i < 3; i++)
		    reqIngredients.add(new AppleItem());
		reqIngredients.add(new WheatItem());
		return reqIngredients;
	}

	public String toString() {
		return "Apple pie (heals 10 hp)";
	}

}
