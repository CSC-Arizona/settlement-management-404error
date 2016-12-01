package model.BuildingBlocks;

import java.awt.Color;
import java.util.List;

import Images.ImageEnum;
import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;

/**
 * LavaBlock acts as a boundary on the bottom edge of the map. It cannot be 
 * destroyed, and thus does not yield any resources.
 * 
 * @author Katherine Walters
 * 
 * todo: make it kill actors if they touch it
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
}