package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;
import model.items.WoodItem;

/**
 * AppleTreeTrunkBlock is the bottom part of apple trees. When it is destroyed, it 
 * drops a WoodItem.
 * 
 * @author Ethan Ward
 */
public class AppleTreeTrunkBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2695416023070857080L;
	private List<Actor> actorsInBlock;
	private List<Item> itemsInBlock;
	private List<Item> itemsOnGround;
	private final static int durability = 5;
	public final static String id = "Wood";
	
	public AppleTreeTrunkBlock() {
		super(durability, true, true, new Color(174, 144, 55), null, id, ImageEnum.WOOD);
		itemsInBlock = new LinkedList<>();
		for (int i = 0; i < 8; i++) 
		    itemsInBlock.add(new WoodItem());
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

	@Override
	public boolean addActor(Actor actor) {
		if(actor.isAlive())
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
