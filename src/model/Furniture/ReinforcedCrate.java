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
	/**
	 * 
	 */
	private static final long serialVersionUID = -4875461862312912501L;
	private final static double weightCapacity = 500.0;
	private List<Item> reinforcedCrateList;
	
	public ReinforcedCrate() {
		super(50, weightCapacity);
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
	
	@Override
	public String toString() {
		return "Reinforced crate " + super.toString();
	}
}
