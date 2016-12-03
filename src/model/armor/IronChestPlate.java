package model.armor;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;

//Author: Maxwell Faridian
//This class defines the Iron Chest Plate armor
//Iron Chest Plate: 4 iron

public class IronChestPlate extends Armor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8725107513330446951L;
	private final static int attackMod = 20;
	private final static int defenseMod = 16;
	private List<Item> icpList;
	
	public IronChestPlate() {
		super(false, attackMod, 0, 28.0, defenseMod, "Iron chestplate");
		icpList = new LinkedList<>();
		icpList.add(new IronItem());
		icpList.add(new IronItem());
		icpList.add(new IronItem());
		icpList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return icpList;
	}
}
