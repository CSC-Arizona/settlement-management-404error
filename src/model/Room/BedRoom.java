package model.Room;

import java.util.LinkedList;
import java.util.List;

import model.Actors.Position;
import model.Furniture.Bed;
import model.Furniture.Furniture;
import model.Items.Item;

/**
 * The BedRoom contains 3 Beds and has an initial max occupancy of six. An upgrade 
 * basically adds an additional HealingBed and increases the MaxOccupancy by 2. The
 * width of the BedRoom is 12 and the number of upgrades allowed is three, so at full
 * upgraded status there will be one Bed per 2 squares in the room.
 * 
 * @author Katherine Walters
 */
public class BedRoom extends Room {

	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;
	
	public BedRoom(Position p) {
		super(2, 12, 6, 3, p);
		this.furniture = new LinkedList<>();
		for (int i = 0; i < 3; i++)
		    this.furniture.add(new Bed());
		this.requiredBuildingMaterials = new LinkedList<>();
		for (Furniture f : furniture) {
			for (Item i : f.getRequiredMaterials())
				this.requiredBuildingMaterials.add(i);
		}
		this.requiredUpgradeMaterials = new LinkedList<>();
		for (Item b : new Bed().getRequiredMaterials())
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
		return 2;
	}
}
