package model.building_blocks;

import java.awt.Color;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;

public class SpaceShipBlock extends BuildingBlock {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7466847276590138449L;
	private final static int durability = 7;
	public final static String id = "Space ship";
	
	public SpaceShipBlock() {
		super(durability, false, true, Color.GRAY, null, id, null);
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
