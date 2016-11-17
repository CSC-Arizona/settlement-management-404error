package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.AppleItem;
import model.Items.AppleSeedItem;
import model.Items.Item;

/**
 * AppleTreeLeafBlock is the top part of apple trees. When it is destroyed, it 
 * drops AppleItems and AppleSeedItems.
 * 
 * @author Ethan Ward and Katherine Walters
 */
public class AppleTreeLeafBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 1;
	public final static String id = "Leaf";
	
	public AppleTreeLeafBlock() {
		super(durability, true, false, new Color(84, 232, 67), id);
		itemsInBlock = new LinkedList<>();
		for (int a = 0; a < 3; a++)
		    itemsInBlock.add(new AppleItem());
		for (int s = 0; s < 5; s++)
		    itemsInBlock.add(new AppleSeedItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}