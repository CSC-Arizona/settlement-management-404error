package model.Room;

import java.util.LinkedList;
import java.util.List;

import model.Furniture.Furniture;
import model.Items.Item;

/**
 * The StoreRoom contains 4 small Chests and has an initial max occupancy of three. 
 * The first upgrade upgrades the 4 small Chests into 4 medium Chests and the second
 * upgrades them into 4 large Chests. There is a 2 upgrade limit on each StoreRoom, 
 * and each time it upgrades it increases the max occupancy by 1. There shouldn't need 
 * to be many agents in this Room anyways, as it's just a drop-off/pick-up point.
 * 
 * @author Katherine Walters
 */
public class StoreRoom extends Room{

	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;
	
	public StoreRoom() {
		super(2, 8, 3, 2);
		this.furniture = new LinkedList<>();
		for (int i = 0; i < 4; i++)
		    this.furniture.add(new Chest());
		this.requiredBuildingMaterials = new LinkedList<>();
		for (Furniture f : furniture) {
			for (Item i : f.getRequiredMaterials())
				this.requiredBuildingMaterials.add(i);
		}
		this.requiredUpgradeMaterials = new LinkedList<>();
		for (Item b : new Chest().getRequiredMaterials())
		    requiredUpgradeMaterials.add(b);
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
