package model;

import java.util.LinkedList;
import java.util.List;

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
