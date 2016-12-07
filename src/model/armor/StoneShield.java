package model.armor;

import java.util.LinkedList;
import java.util.List;

import model.items.Item;
import model.items.StoneItem;

//Author: Maxwell Faridian
//This class defines the Stone Shield armor
//Stone Shield: 3 stone

public class StoneShield extends Armor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2717122119561276068L;
	private final static int attackMod = 10;
	private final static int defenseMod = 12;
	private static List<Item> ssList;
	
	public StoneShield() {
		super(false, attackMod, 0, 15.0, defenseMod, "Stone shield", null);
		ssList = new LinkedList<>();
		ssList.add(new StoneItem());
		ssList.add(new StoneItem());
		ssList.add(new StoneItem());
	}

	public static List<Item> getRequiredMaterials() {
		return ssList;
	}
}
