package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;

/**
 * IronOreBlock yields IronItem when it is destroyed.
 * 
 * @author Katherine Walters
 */
public class IronOreBlock extends BuildingBlock {

	private final static int durability = 10;
	private List<Item> itemsInBlock;
	public final static String id = "Iron ore";
	
	public IronOreBlock() {
		super(durability, true, false, Color.BLACK, id);
        itemsInBlock = new LinkedList<>();
        itemsInBlock.add(new IronItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}