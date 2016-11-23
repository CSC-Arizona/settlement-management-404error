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
	private final static int weightCapacity = 500;
	private List<Item> reinforcedCrateList;
	
	public ReinforcedCrate() {
		super(50, weightCapacity);
		reinforcedCrateList = new LinkedList<>();
		for (int i = 0; i < 6; i++) {
			addItem(new WoodItem());
		}
		addItem(new StoneItem());
		addItem(new StoneItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return reinforcedCrateList;
	}
	
	@Override
	public String toString() {
		return "Reinforced crate (remaining capacity " + (weightCapacity-contentsWeight) + ")";
	}
}
