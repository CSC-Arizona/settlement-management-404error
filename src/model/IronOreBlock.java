package model;

import java.util.LinkedList;
import java.util.List;

/**
 * IronOreBlock yields IronItem when it is destroyed.
 * 
 * @author Katherine Walters
 */
public class IronOreBlock extends BuildingBlock {

	private final static int durability = 10;
	private List<Item> itemsInBlock;
	
	public IronOreBlock() {
		super(durability, true, false);
        itemsInBlock = new LinkedList<>();
        itemsInBlock.add(new IronItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}
