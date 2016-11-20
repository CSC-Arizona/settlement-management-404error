package model.Room;

import java.util.List;

import model.Items.Item;

public class HorizontalTunnel extends Room {

	public HorizontalTunnel(int requiredHeight, int requiredWidth, int roomCapacity, int upgradesAllowed) {
		super(1, 2, 10, 0);
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
