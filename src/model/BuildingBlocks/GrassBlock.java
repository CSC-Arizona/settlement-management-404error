package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.WheatKernelItem;

/**
 * Grass blocks drop wheat kernels (and other things? material to make clothes?)
 *
 */
public class GrassBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 1;
	public final static String id = "Bush";
	
	public GrassBlock() {
		super(durability, true, false, new Color(0, 87, 3), id);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new WheatKernelItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}