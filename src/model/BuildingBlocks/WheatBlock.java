package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.WheatItem;
//import model.Items.WheatStem;
import model.Items.WheatKernelItem;
import model.Items.WheatStemItem;

public class WheatBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	public final static String id = "Wheat";

	public WheatBlock() {
		super(1, true, false, new Color(244, 209, 66), id);
		itemsInBlock = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
		    itemsInBlock.add(new WheatItem());
		    itemsInBlock.add(new WheatStemItem());
		    itemsInBlock.add(new WheatKernelItem());
		}
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}
