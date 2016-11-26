package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 8273562508743492717L;
	private List<Item> itemsInBlock;
	private final static int durability = 1;
	public final static String id = "Apple";

	public AppleTreeLeafBlock() {
		super(durability, true, false, new Color(242, 58, 58), id);
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

	@Override
	public boolean addActor(Actor actor) {
		return false;
	}

	@Override
	public boolean removeActor(Actor actor) {
		return false;
	}
	
	@Override
	public List<Actor> getActors() {
		return null;
	}
	
	@Override
	public boolean addFurniture(Furniture furniture) {
		return false;
	}

	@Override
	public boolean removeFurniture() {
		return false;
	}

	@Override
	public Furniture getFurniture() {
		return null;
	}

	@Override
	public List<Item> itemsOnGround() {
		return null;
	}
}