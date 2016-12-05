package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.StoreRoomBlock;
import model.furniture.BasicCrate;
import model.furniture.Crate;
import model.furniture.Furniture;
import model.furniture.MetalCrate;
import model.furniture.ReinforcedCrate;
import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;

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

	private TreeMap<Position, Furniture> reqFurniture;
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
		reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(2,2), new BasicCrate());
		reqFurniture.put(new Position(2,4), new BasicCrate());
		reqFurniture.put(new Position(2,6), new BasicCrate());
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
		return reqFurniture;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		return new StoreRoomBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		if (upgradeNum == 2 || upgradeNum == 1) {
		    for (Position p : reqFurniture.keySet()) {
		    	Crate crate = (Crate) reqFurniture.remove(p);
		    	LinkedList<Item> currContents = crate.getItemsContained();
		    	if (upgradeNum == 2)
		    	    reqFurniture.put(p, new ReinforcedCrate(currContents));
		    	else 
		    		reqFurniture.put(p,  new MetalCrate(currContents));
		    }
		}
	}
}
