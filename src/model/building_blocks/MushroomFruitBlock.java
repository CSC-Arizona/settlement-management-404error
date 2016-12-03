package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;

/**
 * @author Ethan Ward
 *
 */
public class MushroomFruitBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7730904810509788147L;
	private List<Item> itemsInBlock;
	private final static int durability = 1;
	public final static String id = "Mushroom fruit";

	public MushroomFruitBlock() {
		super(durability, true, false, new Color(72, 249, 229), null, id, null);
		itemsInBlock = new LinkedList<>();

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
