package model.Armor;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.StoneItem;

//Author: Maxwell Faridian
//This class defines the Stone Chest Plate armor
//Stone Chest Plate: 4 stone

public class StoneChestPlate extends Armor {
	private final static int attackMod = 12;
	private final static int defenseMod = 14;
	private List<Item> scpList;
	
	public StoneChestPlate() {
		super(false, attackMod, 0, 20.0, defenseMod);
		scpList = new LinkedList<>();
		scpList.add(new StoneItem());
		scpList.add(new StoneItem());
		scpList.add(new StoneItem());
		scpList.add(new StoneItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return scpList;
	}
}
