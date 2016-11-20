package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.WheatItem;
//import model.Items.WheatStem;

public class WheatBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	public final static String id = "Wheat";

	public WheatBlock() {
		super(1, true, false, new Color(244, 209, 66), id);
		itemsInBlock = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
//		    itemsInBlock.add(new WheatItem());
//		    itemsInBlock.add(new WheatStem());
//		    itemsInBlock.add(new WheatKernel());
		}
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}
