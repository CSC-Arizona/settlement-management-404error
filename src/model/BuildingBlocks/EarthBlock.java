package model.BuildingBlocks;

import java.awt.Color;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;

/**
 * EarthBlock represents normal earth. It can be dug out to make space, but it doesn't 
 * yeild any resources.
 * 
 * @author Katherine Walters
 */
public class EarthBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778429167592410879L;
	private final static int durability = 3;
	public final static String id = "Earth";
	
	public EarthBlock() {
		super(durability, true, false, new Color(222,210,140), id);
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