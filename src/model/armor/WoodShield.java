package model.armor;

import java.util.LinkedList;
import java.util.List;

import model.items.Item;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Wood Shield armor
//Wood Shield: 3 wood

public class WoodShield extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2764667790125492636L;
	private final static int attackMod = 4;
	private final static int defenseMod = 10;
	private static List<Item> wsList;
	
	public WoodShield() {
		super(false, attackMod, 0, 9.0, defenseMod, "Wood shield", null);
		wsList = new LinkedList<>();
		wsList.add(new WoodItem());
		wsList.add(new WoodItem());
		wsList.add(new WoodItem());
	}

	public static List<Item> getRequiredMaterials() {
		return wsList;
	}
}
