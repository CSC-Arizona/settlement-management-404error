package model.BuildingBlocks;

import java.awt.Color;
import java.util.List;

import model.Items.Item;

/**
 * EarthBlock represents normal earth. It can be dug out to make space, but it doesn't 
 * yeild any resources.
 * 
 * @author Katherine Walters
 */
public class EarthBlock extends BuildingBlock {

	private final static int durability = 3;
	public final static String id = "Earth";
	
	public EarthBlock() {
		super(durability, true, false, new Color(222,210,140), id);
	}
	
	@Override
	public List<Item> lootBlock() {
		return null;
	}

}