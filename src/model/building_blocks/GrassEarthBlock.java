package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;
import model.items.WheatItem;
import model.items.WheatKernelItem;
import model.items.WheatStemItem;

/**
 * Same as an EarthBlock but with different colors
 * 
 * @author Ethan Ward
 */
public class GrassEarthBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778429167592410879L;
	private final static int durability = 3;
	public final static String id = "Earth";
	public List<Item> lootItems;

	public GrassEarthBlock() {
		super(durability, true, false, new Color(24, 165, 27), new Color(193,
				156, 125), id, ImageEnum.GRASSBLOCK);
		lootItems = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
		    lootItems.add(new WheatStemItem());
		    lootItems.add(new WheatKernelItem());
		    lootItems.add(new WheatItem());
		}
	}

	@Override
	public List<Item> lootBlock() {
		return null;
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
