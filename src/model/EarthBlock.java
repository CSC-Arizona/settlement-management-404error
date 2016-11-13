package model;

import java.util.List;

/**
 * EarthBlock represents normal earth. It can be dug out to make space, but it doesn't 
 * yeild any resources.
 * 
 * @author Katherine Walters
 */
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
