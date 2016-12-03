package model.armor;

import java.util.LinkedList;
import java.util.List;

import model.items.Item;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Wood Chest Plate armor
//Wood Chest Plate: 4 wood

public class WoodChestPlate extends Armor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4751978466416272138L;
	private final static int attackMod = 6;
	private final static int defenseMod = 12;
	private List<Item> wcpList;
	
	public WoodChestPlate() {
		super(false, attackMod, 0, 9.0, defenseMod, "Wood chestplate");
		wcpList = new LinkedList<>();
		wcpList.add(new WoodItem());
		wcpList.add(new WoodItem());
		wcpList.add(new WoodItem());
		wcpList.add(new WoodItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return wcpList;
	}
}
