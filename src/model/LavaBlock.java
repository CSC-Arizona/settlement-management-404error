package model;

import java.util.List;

public class LavaBlock extends BuildingBlock {

	private final static int durability = 0;

	public LavaBlock() {
		super(durability, false, false);
	}

	@Override
	public List<Item> lootBlock() {
		return null;
	}
}
