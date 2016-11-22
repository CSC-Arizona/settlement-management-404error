package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Reinforced Crate
//Reinforced Crate (6 wood, 2 stone): 50lb weight capacity
public class ReinforcedCrate extends Crate {
	private final static int weightCapacity = 50;
	private List<Item> reinforcedCrateList;
	
	public ReinforcedCrate() {
		super(weightCapacity);
		reinforcedCrateList = new LinkedList<>();
		for (int i = 0; i < 6; i++) {
			reinforcedCrateList.add(new WoodItem());
		}
		reinforcedCrateList.add(new StoneItem());
		reinforcedCrateList.add(new StoneItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return reinforcedCrateList;
	}
}