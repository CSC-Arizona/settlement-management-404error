package model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.TunnelBlock;
import model.Furniture.Furniture;
import model.Furniture.Ladder;
import model.Items.Item;

/**
 * VerticalTunnel contains a ladder that actors can use to travel up and down within the tunnel
 * system.
 * 
 * @author Katherine Walters
 */
public class VerticalTunnel extends Room {
	
	private TreeMap<Position, Furniture> reqFurniture;
	private List<Item> reqItems;

	public VerticalTunnel(Position p) {
		super(2, 1, 20, 0, p);
        reqFurniture = new TreeMap<Position, Furniture>();
        reqFurniture.put(new Position(0,0), new Ladder());
        reqFurniture.put(new Position(1,0), new Ladder());
        reqItems = new LinkedList<>();
        for (Furniture f : reqFurniture.values()) {
			for (Item i : f.getRequiredMaterials())
				this.reqItems.add(i);
		}
	}

	@Override
	public List<Item> getRequiredBuildMaterials() {
		return reqItems;
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

}
