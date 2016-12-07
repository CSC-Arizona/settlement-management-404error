package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.IncubationRoomBlock;
import model.furniture.Furniture;
import model.furniture.IncubationChamber;
import model.furniture.Ladder;
import model.items.Item;

//Author: Maxwell Faridian
//This class defines the Incubation Room, where 2 actors meet to breed and place their new egg item into an incubation chamber
/*The BedRoom contains 2 Incubation Chambers and has an initial max occupancy of 4. An upgrade 
* basically adds an additional IncubationChamber and increases the MaxOccupancy by 2. The
* width of the IncubationRoom is 12 and the number of upgrades allowed is three, so at full
* upgraded status there will be one IncubationChamber per 2 squares in the room.*/
public class IncubationRoom extends Room {
	
	private TreeMap<Position, Furniture> reqFurniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;

	public static int getHeight() {
		return 2;
	}
	
	public static int getWidth() {
		return 12;
	}
	
	public IncubationRoom(Position p) {
		super(getHeight(), getWidth(), 4, 3, p);
		addInitialFurniture();
		requiredBuildingMaterials = makeBuildMaterialsList();
		this.requiredUpgradeMaterials = new LinkedList<>();
		for(Item b : new IncubationChamber().getRequiredMaterials()) {
			this.requiredUpgradeMaterials.add(b);
		}
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
		reqFurniture.put(new Position(2,1), new IncubationChamber());
		reqFurniture.put(new Position(2,3), new IncubationChamber());
		reqFurniture.put(new Position(2,5), new IncubationChamber());
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

	/* (non-Javadoc)
	 * @see model.Room.Room#getFurniture()
	 */
	@Override
	public TreeMap<Position, Furniture> getFurniture() {
		return reqFurniture;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		return new IncubationRoomBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		int pos = this.getRequiredWidth() - (2 * upgradeNum);
		reqFurniture.put(new Position(2, pos), new IncubationChamber());
	}

	@Override
	public String getID() {
		return "incubation room";
	}

}
