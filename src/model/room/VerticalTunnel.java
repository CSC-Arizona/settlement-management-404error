package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.TunnelBlock;
import model.furniture.Furniture;
import model.furniture.Ladder;
import model.items.Item;

/**
 * VerticalTunnel contains a ladder that actors can use to travel up and down within the tunnel
 * system.
 * 
 * @author Katherine Walters
 */
public class VerticalTunnel extends Room {
	
	private TreeMap<Position, Furniture> reqFurniture;
	private LinkedList<Item> requiredBuildingMaterials;

	public static int getHeight() {
		return 2;
	}
	
	public static int getWidth() {
		return 1;
	}
	
	public VerticalTunnel(Position p) {
		super(getHeight(), getWidth(), 20, 0, p);
		addInitialFurniture();
		requiredBuildingMaterials = makeBuildMaterialsList();
	}
	
	private void addInitialFurniture() {
		reqFurniture = new TreeMap<Position, Furniture>();
		reqFurniture.put(new Position(0,0), new Ladder());
		reqFurniture.put(new Position(1,0), new Ladder());
	}

	@Override
	public List<Item> getRequiredBuildMaterials() {
		return requiredBuildingMaterials;
	}

	@Override
	public List<Item> getRequiredUpgradeMaterials() {
		return new LinkedList<>();
	}

	@Override
	public int increaseCapacityBy() {
		return 0;
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
		return new TunnelBlock();
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		// do nothing
	}

}
