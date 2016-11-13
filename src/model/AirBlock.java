package model;

import java.util.List;

/**
 * AirBlock represents any blank space above ground that is visible.. I'm not sure 
 * where we're going to start the game so I added it in case
 * 
 * @author Katherine Walters
 */
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
