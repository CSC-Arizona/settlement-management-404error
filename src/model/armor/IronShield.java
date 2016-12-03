package model.armor;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;

//Author: Maxwell Faridian
//This class defines the Iron Shield armor
//Iron Shield: 3 iron

public class IronShield extends Armor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8280816099515266107L;
	private final static int attackMod = 18;
	private final static int defenseMod = 14;
	private List<Item> isList;
	
	public IronShield() {
		super(false, attackMod, 0, 21.0, defenseMod, "Iron shield");
		isList = new LinkedList<>();
		isList.add(new IronItem());
		isList.add(new IronItem());
		isList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return isList;
	}
}
