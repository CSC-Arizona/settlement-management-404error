package model.Armor;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;

//Author: Maxwell Faridian
//This class defines the Iron Shield armor
//Iron Shield: 3 iron

public class IronShield extends Armor {
	
	private final static int attackMod = 18;
	private final static int defenseMod = 14;
	private List<Item> isList;
	
	public IronShield() {
		super(false, attackMod, 0, 21.0, defenseMod);
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
