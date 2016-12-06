package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;
import model.items.StoneItem;

/**
 * EarthBlock represents normal earth. It can be dug out to make space, but it doesn't 
 * yeild any resources.
 * 
 * @author Katherine Walters
 */
public class EarthBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778429167592410879L;
	private final static int durability = 3;
	public final static String id = "Earth";
	private List<Item> itemsInBlock;
	
	
	public EarthBlock() {
		super(durability, true, false, new Color(193,156,125), null, id, null);
		itemsInBlock = new LinkedList<>();
		Random rand = new Random();
		int num = rand.nextInt(100);
		if (num < 10) {
			for (int i = 0; i < num; i++)
				itemsInBlock.add(new StoneItem());
		}
	}
	
	@Override
	public List<Item> lootBlock() {
		if (itemsInBlock.isEmpty())
			return null;
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
