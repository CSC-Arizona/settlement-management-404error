package model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.KitchenRoomBlock;
import model.BuildingBlocks.TunnelBlock;
import model.Furniture.Fireplace;
import model.Furniture.Furniture;
import model.Furniture.HealingBed;
import model.Furniture.MillingMachine;
import model.Items.Item;

/**
 * The KitchenRoom contains one MillingMachine (that converts wheat to flour) and 
 * one FirePlace (that the dragons can breathe on) and has an initial max occupancy 
 * of 3. One upgrade is allowed and adds an additional FirePlace and increases the 
 * MaxOccupancy by 1. 
 * 
 * @author Katherine Walters
 */
public class KitchenRoom extends Room {

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
	
	public KitchenRoom(Position p) {
        super(getHeight(), getWidth(), 3, 1, p);
		this.reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(1,0), new Fireplace());
		reqFurniture.put(new Position(1,2), new MillingMachine());
		this.furniture = new LinkedList<>();

	    this.furniture.add(new Fireplace());
	    this.furniture.add(new MillingMachine());
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
		return new KitchenRoomBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		int pos = this.getRequiredWidth() - (2 * upgradeNum);
		reqFurniture.put(new Position(0, pos), new Fireplace());
	}
}
