package model.Room;

import java.util.List;

import model.Actors.Position;
import model.Items.Item;

public class HorizontalTunnel extends Room {

	public HorizontalTunnel(Position p) {
		super(1, 2, 10, 0, p);
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

}
