package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BedRoomBlock;
import model.building_blocks.BuildingBlock;
import model.furniture.Bed;
import model.furniture.Furniture;
import model.furniture.Ladder;
import model.items.Item;

/**
 * The BedRoom contains 3 Beds and has an initial max occupancy of six. An
 * upgrade basically adds an additional Bed and increases the MaxOccupancy by 2.
 * The width of the BedRoom is 12 and the number of upgrades allowed is three,
 * so at full upgraded status there will be one Bed per 2 squares in the room.
 * 
 * @author Katherine Walters
 */
public class BedRoom extends Room {

	private TreeMap<Position, Furniture> reqFurniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;
	private static RoomEnum room = RoomEnum.BEDROOM;

	public static int getHeight() {
		return room.getHeight();
	}

	public static int getWidth() {
		return room.getWidth();
	}

	public BedRoom(Position p) {
		super(getHeight(), getWidth(), 6, 3, p, true);
		addInitialFurniture();
		requiredBuildingMaterials = makeBuildMaterialsList();
		this.requiredUpgradeMaterials = new LinkedList<>();
		for (Item b : new Bed().getRequiredMaterials())
		    requiredUpgradeMaterials.add(b);
	}
	
	private void addInitialFurniture() {
		reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(0,0), new Ladder());
		reqFurniture.put(new Position(1,0), new Ladder());
		reqFurniture.put(new Position(2,0), new Ladder());
		reqFurniture.put(new Position(3,0), new Ladder());
		reqFurniture.put(new Position(0,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(1,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(2,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(3,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(2, 1), new Bed());
		reqFurniture.put(new Position(2, 3), new Bed());
		reqFurniture.put(new Position(2, 5), new Bed());		
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Room.Room#getFurniture()
	 */
	@Override
	public TreeMap<Position, Furniture> getFurniture() {
		return reqFurniture;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		return new BedRoomBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		int pos = this.getRequiredWidth() - (2 * upgradeNum);
		reqFurniture.put(new Position(2, pos), new Bed());
	}

	@Override
	public String getID() {
		return "bedroom";
	}
}
