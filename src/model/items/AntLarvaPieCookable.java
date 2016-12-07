package model.items;

import images.ImageEnum;

import java.util.LinkedList;
import java.util.List;

public class AntLarvaPieCookable extends Item {//CookableItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6230505512703031570L;

	public AntLarvaPieCookable() {
		super(true, 0, 10, 0.5, ImageEnum.APPLEPIE);
	}

	public static List<Item> getRequiredMaterials() {
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
