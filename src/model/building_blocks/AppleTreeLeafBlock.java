package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.actors.Actor;
import model.furniture.Furniture;
import model.items.AppleItem;
import model.items.AppleSeedItem;
import model.items.Item;

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
		super(durability, true, true, new Color(242, 58, 58), new Color(84, 232, 67), id, ImageEnum.APPLE);
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

	@Override
	public BuildingBlock getAppropriateReplacement() {
		return new AirBlock();
	}
}
