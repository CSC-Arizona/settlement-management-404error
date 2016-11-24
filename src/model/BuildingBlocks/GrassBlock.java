package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;
import model.Items.WheatKernelItem;

/**
 * Grass blocks drop wheat kernels (and other things? material to make clothes?)
 *
 */
public class GrassBlock extends BuildingBlock {

	private List<Actor> actorsInBlock;
	private List<Item> itemsInBlock;
	private List<Item> itemsOnGround;
	private final static int durability = 1;
	public final static String id = "Grass";

	public GrassBlock() {
		super(durability, true, true, new Color(0, 87, 3), id);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new WheatKernelItem());
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
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
		return itemsOnGround;
	}

}