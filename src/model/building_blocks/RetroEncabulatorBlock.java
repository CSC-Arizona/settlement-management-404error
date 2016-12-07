package model.building_blocks;

import images.ImageEnum;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;
import model.items.RetroencabulatorItem;

public class RetroEncabulatorBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = -760848763802167344L;
	private final static int durability = 7;
	private List<Item> itemsInBlock;
	private List<Actor> actorsInBlock;
	private List<Item> itemsOnGround;
	public final static String id = "Space ship part";

	public RetroEncabulatorBlock() {
		super(durability, true, true, Color.GREEN, null, id,
				ImageEnum.SPACESHIPPARTBLOCK);
		itemsInBlock = new LinkedList<>();
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
		itemsInBlock.add(new RetroencabulatorItem());
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
		return new AntTunnelBlock();
	}
}
