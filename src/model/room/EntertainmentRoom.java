package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EntertainmentRoomBlock;
import model.furniture.Couch;
import model.furniture.Furniture;
import model.furniture.Ladder;
import model.furniture.PoolTable;
import model.items.Item;

/**
 * The EntertainmentRoom contains 2 Couches and 1 PoolTable. An upgrade basically 
 * adds an additional PoolTable and increases the max occupancy by 3. With two upgrades
 * allowed, a fully upgraded EntertainmentRoom will contain three PoolTables and Two Couches,
 * And have a max occupancy of 14.
 * 
 * @author Katherine Walters
 */
public class EntertainmentRoom extends Room{

	private TreeMap<Position, Furniture> reqFurniture;
	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;

	public static int getHeight() {
		return 2;
	}
	
	public static int getWidth() {
		return 10;
	}
	
	public EntertainmentRoom(Position p) {
        super(getHeight(), getWidth(), 8, 2, p);
		reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(0,0), new Ladder());
		 reqFurniture.put(new Position(0,getWidth() - 1), new Ladder());
		 reqFurniture.put(new Position(1,0), new Ladder());
		 reqFurniture.put(new Position(1,getWidth() - 1), new Ladder());
		 reqFurniture.put(new Position(2,0), new Ladder());
		 reqFurniture.put(new Position(2,getWidth() - 1), new Ladder());
		 reqFurniture.put(new Position(3,0), new Ladder());
		 reqFurniture.put(new Position(3,getWidth() - 1), new Ladder());
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

	/* (non-Javadoc)
	 * @see model.Room.Room#getFurniture()
	 */
	@Override
	public TreeMap<Position, Furniture> getFurniture() {
		return reqFurniture;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		return new EntertainmentRoomBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		int pos = this.getRequiredWidth() - (2 * upgradeNum);
		reqFurniture.put(new Position(0, pos), new PoolTable());
	}
	
	

}
