package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.StoneItem;

/**
 * StoneBlock yields StoneItem when it is destroyed.
 * 
 * @author Katherine Walters
 */
public class StoneBlock extends BuildingBlock {

	private final static int durability = 8;
	private List<Item> itemsInBlock;
	public final static String id = "Stone";
	
	public StoneBlock() {
		super(durability, true, false, Color.GRAY, id);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new StoneItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}