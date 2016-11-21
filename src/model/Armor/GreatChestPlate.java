package model.Armor;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Great Chest Plate armor
//Great Chest Plate: 2 wood, 2 stone, 3 iron

public class GreatChestPlate extends Armor {
	
	private final static int attackMod = 22;
	private final static int defenseMod = 18;
	private List<Item> gcpList;
	
	public GreatChestPlate() {
		super(false, attackMod, 0, 30.0, defenseMod);
		gcpList = new LinkedList<>();;
		gcpList.add(new WoodItem());
		gcpList.add(new WoodItem());
		gcpList.add(new StoneItem());
		gcpList.add(new StoneItem());
		gcpList.add(new IronItem());
		gcpList.add(new IronItem());
		gcpList.add(new IronItem());
		
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return gcpList;
	}
}
