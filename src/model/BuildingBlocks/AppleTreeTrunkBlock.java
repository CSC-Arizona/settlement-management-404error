package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;
import model.Items.WoodItem;

/**
 * AppleTreeTrunkBlock is the bottom part of apple trees. When it is destroyed, it 
 * drops a WoodItem.
 * 
 * @author Ethan Ward
 */
public class AppleTreeTrunkBlock extends BuildingBlock {

	private List<Actor> actorsInBlock;
	private List<Item> itemsInBlock;
	private final static int durability = 5;
	public final static String id = "Wood";
	
	public AppleTreeTrunkBlock() {
		super(durability, true, true, new Color(174, 144, 55), id);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new WoodItem());
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

}