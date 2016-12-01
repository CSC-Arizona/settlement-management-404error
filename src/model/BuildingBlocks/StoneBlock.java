package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;
import model.Items.StoneItem;

/**
 * StoneBlock yields StoneItem when it is destroyed.
 * 
 * @author Katherine Walters
 */
public class StoneBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4160316069140520443L;
	private final static int durability = 8;
	private List<Item> itemsInBlock;
	public final static String id = "Stone";
	
	public StoneBlock() {
		super(durability, true, false, Color.GRAY, null, id, null);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new StoneItem());
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
		return new CavernBlock();
	}

}
