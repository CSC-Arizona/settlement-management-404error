package model;

import java.util.List;

/**
 * CavernBlock represents dragon-made "blank space" underground. Basically 
 * tunnels and caverns. Actors can move through it, and constructed items can be 
 * placed in it.
 * 
 * @author Katherine Walters
 */
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
