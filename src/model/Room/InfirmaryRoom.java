package model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.InfirmaryRoomBlock;
import model.BuildingBlocks.TunnelBlock;
import model.Furniture.Furniture;
import model.Furniture.HealingBed;
import model.Items.Item;


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

	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;
	
	public InfirmaryRoom(Position p) {
		super(2, 4, 8, 2, p);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		// TODO Auto-generated method stub
		return new InfirmaryRoomBlock();
	}

}
