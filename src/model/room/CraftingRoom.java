package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.CraftingRoomBlock;
import model.building_blocks.TunnelBlock;
import model.furniture.Fireplace;
import model.furniture.Furniture;
import model.furniture.HealingBed;
import model.furniture.Ladder;
import model.furniture.CraftingMachine;
import model.items.Item;

/**
 * The KitchenRoom contains one MillingMachine (that converts wheat to flour) and 
 * one FirePlace (that the dragons can breathe on) and has an initial max occupancy 
 * of 3. One upgrade is allowed and adds an additional FirePlace and increases the 
 * MaxOccupancy by 1. 
 * 
 * @author Katherine Walters
 */
public class CraftingRoom extends Room {

	private TreeMap<Position, Furniture> reqFurniture;
	private List<Furniture> furniture;
	private List<Item> requiredBuildingMaterials;
	private List<Item> requiredUpgradeMaterials;
	
	public static int getHeight() {
		return 2;
	}
	
	public static int getWidth() {
		return 6;
	}
	
	public CraftingRoom(Position p) {
        super(getHeight(), getWidth(), 3, 1, p);
		this.reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(2,1), new Fireplace());
		reqFurniture.put(new Position(2,2), new CraftingMachine());
		this.furniture = new LinkedList<>();

	    this.furniture.add(new Fireplace());
	    this.furniture.add(new CraftingMachine());
		this.requiredBuildingMaterials = new LinkedList<>();
		for (Furniture f : furniture) {
			for (Item i : f.getRequiredMaterials())
				this.requiredBuildingMaterials.add(i);
		}
        this.requiredUpgradeMaterials = new LinkedList<>();
		for (Item b : new Fireplace().getRequiredMaterials())
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
		return new CraftingRoomBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		int pos = this.getRequiredWidth() - (2 * upgradeNum);
		reqFurniture.put(new Position(0, pos), new Fireplace());
	}
}
