package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;
import model.items.WheatKernelItem;
import model.items.WheatStemItem;

/**
 * Grass blocks drop wheat kernels (and other things? material to make clothes?)
 *
 */
public class GrassBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309020162960429106L;
	private List<Actor> actorsInBlock;
	private List<Item> itemsInBlock;
	private List<Item> itemsOnGround;
	private final static int durability = 1;
	public final static String id = "Grass";

	public GrassBlock() {
		super(durability, true, true, new Color(0, 87, 3), new Color(206, 237,
				240), id, ImageEnum.GRASS);
		itemsInBlock = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
		    itemsInBlock.add(new WheatKernelItem());
		    itemsInBlock.add(new WheatStemItem());
		}
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

	@Override
	public boolean addActor(Actor actor) {
		if (actor.isAlive())
			actorsInBlock.add(actor);
		return true;
	}

	@Override
	public boolean removeActor(Actor actor) {
		if (actorsInBlock.contains(actor)) {
			actorsInBlock.remove(actor);
			return true;
		}
		return false;
	}

	@Override
	public List<Actor> getActors() {
		return actorsInBlock;
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
		return itemsOnGround;
	}

	@Override
	public BuildingBlock getAppropriateReplacement() {
		return new AirBlock();
	}

}
