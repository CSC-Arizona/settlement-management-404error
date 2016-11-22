package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Items.Item;

public class LeafBlock extends BuildingBlock {

	private List<Item> itemsInBlock;
	private final static int durability = 1;
	public final static String id = "Leaf";
	
	public LeafBlock() {
		super(durability, true, false, new Color(84, 232, 67), id);
		itemsInBlock = new LinkedList<>();

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

}