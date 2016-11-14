package model;

import java.util.LinkedList;
import java.util.List;

/**
 * StoneBlock yields StoneItem when it is destroyed.
 * 
 * @author Katherine Walters
 */
public class StoneBlock extends BuildingBlock {

	private final static int durability = 8;
	private List<Item> itemsInBlock;
	
	public StoneBlock() {
		super(durability, true, false);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new StoneItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}