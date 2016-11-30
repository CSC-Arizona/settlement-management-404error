package model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.StoreRoomBlock;
import model.Furniture.BasicCrate;
import model.Furniture.Furniture;
import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;

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
	
	public static int getHeight() {
		return 2;
	}
	
	public static int getWidth() {
		return 8;
	}
	
	public StoreRoom(Position p) {
		super(getHeight(), getWidth(), 3, 2, p);
		this.furniture = new LinkedList<>();

		for (int i = 0; i < 4; i++)
		    this.furniture.add(new BasicCrate());
		this.requiredBuildingMaterials = new LinkedList<>();
		for (Furniture f : furniture) {
			for (Item i : f.getRequiredMaterials())
				this.requiredBuildingMaterials.add(i);
		}
		
	}

	@Override
	public List<Item> getRequiredBuildMaterials() {
		return requiredBuildingMaterials;
	}

	@Override
	public List<Item> getRequiredUpgradeMaterials() {
		requiredUpgradeMaterials = new LinkedList<>();
		if (this.getUpgradesAllowed() == 2) {
			for (int i = 0; i < 8; i++)
				requiredUpgradeMaterials.add(new StoneItem());
		} else if (this.getUpgradesAllowed() == 1) {
			for (int i = 0; i < 8; i++)
				requiredUpgradeMaterials.add(new IronItem());
		} else {
			
		}
		return requiredUpgradeMaterials;
	}

	@Override
	public int increaseCapacityBy() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see model.Room.Room#getFurniture()
	 */
	@Override
	public TreeMap<Position, Furniture> getFurniture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		// TODO Auto-generated method stub
		return new StoreRoomBlock();
	}
}
