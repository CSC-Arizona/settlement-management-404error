package model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.IncubationRoomBlock;
import model.BuildingBlocks.TunnelBlock;
import model.Furniture.Bed;
import model.Furniture.Furniture;
import model.Furniture.IncubationChamber;
import model.Furniture.Ladder;
import model.Items.Item;

//Author: Maxwell Faridian
//This class defines the Incubation Room, where 2 actors meet to breed and place their new egg item into an incubation chamber
/*The BedRoom contains 2 Incubation Chambers and has an initial max occupancy of 4. An upgrade 
* basically adds an additional IncubationChamber and increases the MaxOccupancy by 2. The
* width of the IncubationRoom is 12 and the number of upgrades allowed is three, so at full
* upgraded status there will be one IncubationChamber per 2 squares in the room.*/
public class IncubationRoom extends Room {
	
	private static int roomCapacity = 4;
	private final static int upgradesAllowed = 3;
	
	private TreeMap<Position, Furniture> reqFurniture;
	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;

	public static int getHeight() {
		return 2;
	}
	
	public static int getWidth() {
		return 12;
	}
	
	public IncubationRoom(Position p) {
		super(getHeight(), getWidth(), roomCapacity, upgradesAllowed, p);
		reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(0,0), new Ladder());
		reqFurniture.put(new Position(0,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(1,0), new Ladder());
		reqFurniture.put(new Position(1,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(2,0), new Ladder());
		reqFurniture.put(new Position(2,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(3,0), new Ladder());
		reqFurniture.put(new Position(3,getWidth() - 1), new Ladder());
		reqFurniture.put(new Position(2, 1), new IncubationChamber());
		reqFurniture.put(new Position(2, 2), new IncubationChamber());
		this.furniture = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
			this.furniture.add(new IncubationChamber());
		}
		this.requiredBuildingMaterials = new LinkedList<>();
		for(Furniture f : furniture) {
			for(Item i : f.getRequiredMaterials()) {
				this.requiredBuildingMaterials.add(i);
			}
		}
		this.requiredUpgradeMaterials = new LinkedList<>();
		for(Item b : new IncubationChamber().getRequiredMaterials()) {
			this.requiredUpgradeMaterials.add(b);
		}
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
		reqFurniture.put(new Position(0, pos), new IncubationChamber());
	}

}
