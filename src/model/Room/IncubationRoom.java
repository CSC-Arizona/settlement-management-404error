package model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.IncubationRoomBlock;
import model.BuildingBlocks.TunnelBlock;
import model.Furniture.Furniture;
import model.Furniture.IncubationChamber;
import model.Items.Item;

//Author: Maxwell Faridian
//This class defines the Incubation Room, where 2 actors meet to breed and place their new egg item into an incubation chamber
/*The BedRoom contains 2 Incubation Chambers and has an initial max occupancy of 4. An upgrade 
* basically adds an additional IncubationChamber and increases the MaxOccupancy by 2. The
* width of the IncubationRoom is 12 and the number of upgrades allowed is three, so at full
* upgraded status there will be one IncubationChamber per 2 squares in the room.*/
public class IncubationRoom extends Room {
	
	private final static int requiredHeight = 2;
	private final static int requiredWidth = 12;
	private static int roomCapacity = 4;
	private final static int upgradesAllowed = 3;
	
	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;

	public static int getHeight() {
		return requiredHeight;
	}
	
	public static int getWidth() {
		return requiredWidth;
	}
	
	public IncubationRoom(Position p) {
		super(requiredHeight, requiredWidth, roomCapacity, upgradesAllowed, p);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		// TODO Auto-generated method stub
		return new IncubationRoomBlock();
	}

}
