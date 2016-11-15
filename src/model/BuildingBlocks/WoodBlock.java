package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.WoodItem;

public class WoodBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 5;
	public final static String id = "Wood";
	
	public WoodBlock() {
		super(durability, true, false, new Color(174, 144, 55), id);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new WoodItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}