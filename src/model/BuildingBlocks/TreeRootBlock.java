package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;
import model.Items.WoodItem;

/**
 * TreeRootBlock is a BuildingBlock for the map. So far I'm thinking this could either extend
 * down from the top soil, or we could have fossilized roots spread more through the map
 * so they can be gathered at all levels, or a combination of the two.
 * 
 * @author Katherine Walters
 */
public class TreeRootBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 5;
	public final static String id = "Tree root";
	
	public TreeRootBlock() {
		super(durability, true, false, Color.GREEN, id);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new WoodItem());
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

}