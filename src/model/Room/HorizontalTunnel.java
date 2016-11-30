package model.Room;

import java.util.List;
import java.util.TreeMap;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.TunnelBlock;
import model.Furniture.Furniture;
import model.Furniture.Ladder;
import model.Items.Item;

/**
 * HorizontalTunnel can be used to travel within the tunnel system and build links between Rooms/Buildings.
 * 
 * @author Katherine Walters
 */
public class HorizontalTunnel extends Room {

	private TreeMap<Position, Furniture> reqFurniture;
	
	public HorizontalTunnel(Position p) {
		super(1, 2, 10, 0, p);
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
