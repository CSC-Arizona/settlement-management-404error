package model.BuildingBlocks;

import java.util.List;
import java.awt.Color;

import model.Items.Item;

/**
 * AirBlock represents any blank space above ground that is visible.. I'm not sure 
 * where we're going to start the game so I added it in case
 * 
 * @author Katherine Walters
 */
public class AirBlock extends BuildingBlock {

	private final static int durability = 0;
	public final static String id = "Air";
	private boolean occupiable = true;
	
	public AirBlock() {
		super(durability, false, true, new Color(206, 237, 240), id);
	}

	@Override
	public List<Item> lootBlock() {
		return null;
	}

}