package model;

import java.util.List;

public class CavernBlock extends BuildingBlock {

	private final static int durability = 0;
	
	public CavernBlock() {
		super(durability, false, true);
	}

	@Override
	public List<Item> lootBlock() {
		return null;
	}

}
