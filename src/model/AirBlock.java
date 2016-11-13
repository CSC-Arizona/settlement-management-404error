package model;

import java.util.List;

public class AirBlock extends BuildingBlock {

	private final static int durability = 0;
	
	public AirBlock() {
		super(durability, false, true);
	}

	@Override
	public List<Item> lootBlock() {
		return null;
	}

}
