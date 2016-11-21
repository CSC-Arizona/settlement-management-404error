package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;

/**
 * 
 * @author Ethan Ward
 */
public class MushroomBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 5;
	public final static String id = "Mushroom";
	
	
	public MushroomBlock() {
		super(durability, true, false, new Color(173, 33, 183), id);
		itemsInBlock = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}