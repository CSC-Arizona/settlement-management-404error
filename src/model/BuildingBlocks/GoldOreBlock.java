package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;

/**
 */
public class GoldOreBlock extends BuildingBlock {

	private final static int durability = 10;
	private List<Item> itemsInBlock;
	public final static String id = "Iron ore";

	public GoldOreBlock() {
		super(durability, true, false, new Color(255, 223, 0), id);
		itemsInBlock = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}