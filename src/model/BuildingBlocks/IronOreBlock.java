package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.IronItem;
import model.Items.Item;

/**
 * IronOreBlock yields IronItem when it is destroyed.
 * 
 * @author Katherine Walters
 */
public class IronOreBlock extends BuildingBlock {

	private final static int durability = 10;
	private List<Item> itemsInBlock;
	public final static String id = "Iron ore";
	
	public IronOreBlock() {
		super(durability, true, false, Color.BLACK, id);
        itemsInBlock = new LinkedList<>();
        itemsInBlock.add(new IronItem());
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