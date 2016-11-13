package model;

import java.util.LinkedList;
import java.util.List;

/**
 * TreeRootBlock is a BuildingBlock for the map. So far I'm thinking this could either extend
 * down from the top soil, or we could have fossilized roots spread more through the map
 * so they can be gathered at all levels, or a combination of the two.
 * 
 * @author Katherine Walters
 */
public class TreeRootBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 5;
	
	public TreeRootBlock() {
		super(durability, true, false);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new WoodItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}
