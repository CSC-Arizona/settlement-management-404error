package model.Room;

import java.util.LinkedList;
import java.util.List;

import model.Actors.Position;
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
	
	private List<Furniture> reqFurniture;
	private List<Item> reqItems;

	public VerticalTunnel(Position p) {
		super(2, 1, 20, 0, p);
        reqFurniture = new LinkedList<>();
        reqFurniture.add(new Ladder());
        reqFurniture.add(new Ladder());
        reqItems = new LinkedList<>();
        for (Furniture f : reqFurniture) {
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

}
