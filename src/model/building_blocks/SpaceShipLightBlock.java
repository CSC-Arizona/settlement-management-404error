package model.building_blocks;

import images.ImageEnum;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;

public class SpaceShipLightBlock extends BuildingBlock {


	private static final long serialVersionUID = 7466847276590138449L;
	private final static int durability = 7;
	private List<Actor> actorsInBlock;
	private List<Item> itemsOnGround;
	public final static String id = "Space ship";
	
	public SpaceShipLightBlock() {
		super(durability, false, true, Color.RED, null, id, ImageEnum.SPACESHIPLIGHT);
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return null;
	}

	@Override
	public boolean addActor(Actor actor) {
		if (actor.isAlive())
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
		return null;
	}

}
