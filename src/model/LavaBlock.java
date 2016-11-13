package model;

import java.util.List;

/**
 * LavaBlock acts as a boundary on the bottom edge of the map. It cannot be 
 * destroyed, and thus does not yield any resources.
 * 
 * @author Katherine Walters
 */
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
