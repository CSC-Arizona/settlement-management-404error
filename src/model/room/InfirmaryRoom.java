package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.InfirmaryRoomBlock;
import model.building_blocks.TunnelBlock;
import model.furniture.Bed;
import model.furniture.Furniture;
import model.furniture.HealingBed;
import model.items.Item;


/**
 * The InfirmaryRoom contains 2 HealingBeds and has an initial max occupancy of 
 * four. An upgrade basically adds an additional HealingBed and increases the MaxOccupancy
 * by 2. The width of the InfirmaryRoom is set to 8, and the number of upgrades allowed
 * is two. That means that when the room is fully upgraded, there will be one HealingBed
 * per 2 squares of width. I think it would be really cool if we could update the look of the 
 * room at each upgrade so that the user can visually see how much space is left in the room.
 * 
 * @author Katherine Walters
 */
public class InfirmaryRoom extends Room {

	private TreeMap<Position, Furniture> reqFurniture;
	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;
	
	public static int getHeight() {
		return 2;
	}
	
	public static int getWidth() {
		return 4;
	}
	
	public InfirmaryRoom(Position p) {
        super(getHeight(), getWidth(), 8, 2, p);
		reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(1,0), new HealingBed());
		reqFurniture.put(new Position(1,2), new HealingBed());
		this.furniture = new LinkedList<>();
		for (int i = 0; i < 2; i++)
		    this.furniture.add(new HealingBed());
		this.requiredBuildingMaterials = new LinkedList<>();
		for (Furniture f : furniture) {
			for (Item i : f.getRequiredMaterials())
				this.requiredBuildingMaterials.add(i);
		}
		this.requiredUpgradeMaterials = new LinkedList<>();
		for (Item b : new HealingBed().getRequiredMaterials())
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

	/* (non-Javadoc)
	 * @see model.Room.Room#getFurniture()
	 */
	@Override
	public TreeMap<Position, Furniture> getFurniture() {
		return reqFurniture;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		return new InfirmaryRoomBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		int pos = this.getRequiredWidth() - (2 * upgradeNum);
		reqFurniture.put(new Position(0, pos), new HealingBed());
	}

}