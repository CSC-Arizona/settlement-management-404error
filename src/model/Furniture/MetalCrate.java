package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Metal Crate
//Metal Crate (6 wood, 2 stone, 2 iron): 70lb weight capacity
public class MetalCrate extends Crate {
	private final static int weightCapacity = 70;
	private List<Item> metalCrateList;
	
	public MetalCrate() {
		super(weightCapacity);
		metalCrateList = new LinkedList<>();
		for (int i = 0; i < 6; i++) {
			metalCrateList.add(new WoodItem());
		}
		metalCrateList.add(new StoneItem());
		metalCrateList.add(new StoneItem());
		metalCrateList.add(new IronItem());
		metalCrateList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return metalCrateList;
	}
}