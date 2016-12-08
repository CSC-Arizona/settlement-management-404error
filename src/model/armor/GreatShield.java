package model.armor;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Great Shield armor
//Great Shield: 1 wood, 1 stone, 2 iron

public class GreatShield extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6119046218741156100L;
	private final static int attackMod = 20;
	private final static int defenseMod = 16;
	private static List<Item> gsList;
	
	public GreatShield() {
		super(false, attackMod, 0, 22.0, defenseMod, "Great shield", null);
	}

	public static List<Item> getRequiredMaterials() {
		gsList = new LinkedList<>();
		gsList.add(new WoodItem());
		gsList.add(new StoneItem());
		gsList.add(new IronItem());
		gsList.add(new IronItem());
		return gsList;
	}
}
