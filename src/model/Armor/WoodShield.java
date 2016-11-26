package model.Armor;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.WoodItem;

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
	private List<Item> wsList;
	
	public WoodShield() {
		super(false, attackMod, 0, 9.0, defenseMod);
		wsList = new LinkedList<>();
		wsList.add(new WoodItem());
		wsList.add(new WoodItem());
		wsList.add(new WoodItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return wsList;
	}
}
