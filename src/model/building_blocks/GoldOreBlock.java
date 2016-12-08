package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.actors.Actor;
import model.furniture.Furniture;
import model.items.GoldItem;
import model.items.Item;

/**
 */
public class GoldOreBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6644333828930335834L;
	private final static int durability = 10;
	private List<Item> itemsInBlock;
	public final static String id = "Gold ore";

	public GoldOreBlock() {
		super(durability, true, false, new Color(255, 223, 0), null, id,
				ImageEnum.GOLD);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new GoldItem());
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
