package model.furniture;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Metal Crate
//Metal Crate (6 wood, 2 stone, 2 iron): 70lb weight capacity
public class MetalCrate extends Crate {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3910896217729131037L;
	private final static double weightCapacity = 200.0;
	private List<Item> metalCrateList;
	
	public MetalCrate(LinkedList<Item> linkedList) {
		super(20, weightCapacity);
		metalCrateList = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			metalCrateList.add(new WoodItem());
		}
		metalCrateList.add(new StoneItem());
		metalCrateList.add(new StoneItem());
		metalCrateList.add(new IronItem());
		for (Item i : linkedList) {
			this.addItem(i);
		}
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return metalCrateList;
	}

	@Override
	public String toString() {
		return "Metal crate " + super.toString();
	}
}
