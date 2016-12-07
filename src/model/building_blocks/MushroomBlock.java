package model.building_blocks;

import images.ImageEnum;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;

/**
 * 
 * @author Ethan Ward
 */
public class MushroomBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -156184307394207619L;
	private List<Actor> actorsInBlock;
	private List<Item> itemsInBlock;
	private final static int durability = 5;
	public final static String id = "Mushroom";
	
	
	public MushroomBlock() {
		super(durability, true, true, new Color(173, 33, 183), null, id, ImageEnum.MUSHROOMBLOCK);
		itemsInBlock = new LinkedList<>();
		actorsInBlock = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

	@Override
	public boolean addActor(Actor actor) {
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
		return null;
	}

	@Override
	public BuildingBlock getAppropriateReplacement() {
		return new CavernBlock();
	}

}
