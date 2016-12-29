package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;
import model.items.WheatItem;
import model.items.WheatKernelItem;
import model.items.WheatStemItem;

/*
 * Grows in the farms. 
 * 
 * @author: Katherine Walters
 */
public class WheatBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5726876699197732768L;
	private List<Item> itemsInBlock;
	public final static String id = "Wheat";

	public WheatBlock() {
		super(1, true, false, new Color(244, 209, 66), null, id, null);
		itemsInBlock = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
		    itemsInBlock.add(new WheatItem());
		    itemsInBlock.add(new WheatStemItem());
		    itemsInBlock.add(new WheatKernelItem());
		}
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
		return new AirBlock();
	}
}
