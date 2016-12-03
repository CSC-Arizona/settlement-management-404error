package model.room;

import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.TunnelBlock;
import model.furniture.Furniture;
import model.furniture.Ladder;
import model.items.Item;

/**
 * HorizontalTunnel can be used to travel within the tunnel system and build links between Rooms/Buildings.
 * 
 * @author Katherine Walters
 */
public class HorizontalTunnel extends Room {

	private TreeMap<Position, Furniture> reqFurniture;
	
	public static int getHeight() {
		return 1;
	}
	
	public static int getWidth() {
		return 2;
	}
	
	
	public HorizontalTunnel(Position p) {
		super(getHeight(), getWidth(), 10, 0, p);
		reqFurniture = new TreeMap<Position, Furniture>();
	}

	@Override
	public List<Item> getRequiredBuildMaterials() {
		return null;
	}

	@Override
	public List<Item> getRequiredUpgradeMaterials() {
		return null;
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