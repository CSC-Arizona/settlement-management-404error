package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Basic Crate
//Basic Crate (6 wood): 30lb weight capacity
public class BasicCrate extends Crate {
	
	private final static int weightCapacity = 30;
	private List<Item> basicCrateList;
	
	public BasicCrate() {
		super(5, weightCapacity);
		basicCrateList = new LinkedList<>();
		for (int i = 0; i < 6; i++) {
			basicCrateList.add(new WoodItem());
		}
		
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return basicCrateList;
	}

}
