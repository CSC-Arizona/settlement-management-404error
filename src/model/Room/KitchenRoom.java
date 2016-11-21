package model.Room;

import java.util.LinkedList;
import java.util.List;

import model.Furniture.Furniture;
import model.Items.Item;

/**
 * The KitchenRoom contains one MillingMachine (that converts wheat to flour) and 
 * one FirePlace (that the dragons can breathe on) and has an initial max occupancy 
 * of 3. One upgrade is allowed and adds an additional FirePlace and increases the 
 * MaxOccupancy by 1. 
 * 
 * @author Katherine Walters
 */
public class KitchenRoom extends Room {

	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;
	
	public KitchenRoom() {
		super(2, 6, 3, 1);
		this.furniture = new LinkedList<>();
	    //this.furniture.add(new FirePlace());
	    //this.furniture.add(new MillingMachine());
		this.requiredBuildingMaterials = new LinkedList<>();
		for (Furniture f : furniture) {
			for (Item i : f.getRequiredMaterials())
				this.requiredBuildingMaterials.add(i);
		}
		this.requiredUpgradeMaterials = new LinkedList<>();
		//for (Item b : new FirePlace().getRequiredMaterials())
		 //   requiredUpgradeMaterials.add(b);
	}

	@Override
	public List<Item> getRequiredBuildMaterials() {
		return requiredBuildingMaterials;
	}

	@Override
	public List<Item> getRequiredUpgradeMaterials() {
		return requiredUpgradeMaterials;
	}

	@Override
	public int increaseCapacityBy() {
		return 1;
	}
}
