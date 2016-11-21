package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Items.Item;

/**
 * @author Ethan Ward
 *
 */
public class MushroomFruitBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 1;
	public final static String id = "Mushroom fruit";
	
	public MushroomFruitBlock() {
		super(durability, true, false, new Color(72, 249, 229), id);
		itemsInBlock = new LinkedList<>();

	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}