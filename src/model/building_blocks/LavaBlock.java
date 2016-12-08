package model.building_blocks;

import java.awt.Color;
import java.util.List;

import images.ImageEnum;
import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;

/**
 * LavaBlock acts as a boundary on the bottom edge of the map. It cannot be 
 * destroyed, and thus does not yield any resources.
 * 
 * @author Katherine Walters
 */
public class LavaBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -159975281404992710L;
	private final static int durability = 0;
	public final static String id = "Lava";
	
	public LavaBlock() {
		super(durability, false, false, Color.RED, null, id, ImageEnum.LAVA);
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
		return null;
	}
}
