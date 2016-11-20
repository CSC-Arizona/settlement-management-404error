package model.Room;

import java.util.LinkedList;
import java.util.List;

import model.Furniture.Bed;
import model.Furniture.Couch;
import model.Furniture.Furniture;
import model.Furniture.PoolTable;
import model.Items.Item;
import model.Items.WoodItem;

/**
 * The EntertainmentRoom contains 2 Couches and 1 PoolTable. An upgrade basically 
 * adds an additional PoolTable and increases the max occupancy by 3. With two upgrades
 * allowed, a fully upgraded EntertainmentRoom will contain three PoolTables and Two Couches,
 * And have a max occupancy of 14.
 * 
 * @author Katherine Walters
 */
public class EntertainmentRoom extends Room{

	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;

	public EntertainmentRoom() {
		super(2, 13, 8, 2);
		this.furniture = new LinkedList<>();
		furniture.add(new PoolTable());
		furniture.add(new Couch());
		furniture.add(new Couch());
		this.requiredBuildingMaterials = new LinkedList<>();
		for (Furniture f : furniture) {
			for (Item i : f.getRequiredMaterials())
				this.requiredBuildingMaterials.add(i);
		}
		this.requiredUpgradeMaterials = new LinkedList<>();
		for (Item b : new PoolTable().getRequiredMaterials())
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
		return 3;
	}
	
	

}
