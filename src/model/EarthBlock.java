package model;

import java.util.List;

public class EarthBlock extends BuildingBlock {

	private final static int durability = 3;
	
	public EarthBlock() {
		super(durability, true, false);
	}

	@Override
	public List<Item> lootBlock() {
		return null;
	}

}
