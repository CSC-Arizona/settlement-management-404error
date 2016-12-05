package model.furniture;

import java.util.LinkedList;
import java.util.List;

import model.items.Item;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Basic Crate
//Basic Crate (6 wood): 30lb weight capacity
public class BasicCrate extends Crate {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4679484485182352115L;
	private final static double weightCapacity = 30.0;
	private List<Item> basicCrateList;
	
	public BasicCrate() {
		super(5, weightCapacity);
		basicCrateList = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
			basicCrateList.add(new WoodItem());
		}
		
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return basicCrateList;
	}

	@Override
	public String toString() {
		return "Basic crate " + super.toString();
	}

}
