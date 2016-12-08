package model.items;

import images.ImageEnum;

import java.util.LinkedList;
import java.util.List;

public class BreadCookable extends Item {//CookableItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914974809369066714L;

	public BreadCookable() {
		super(true, 0, 7, 0.5, ImageEnum.BREAD);
	}

	public static List<Item> getRequiredMaterials() {
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
