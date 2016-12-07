package model.building_blocks;

import images.ImageEnum;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.AntimatterDefenestratorItem;
import model.items.BlackHoleGeneratorItem;
import model.items.Item;

public class BlackHoleGeneratorBlock extends BuildingBlock {

	private final static int durability = 7;
	private List<Item> itemsInBlock;
	private List<Actor> actorsInBlock;
	private List<Item> itemsOnGround;
	public final static String id = "Space ship part";
	
	public BlackHoleGeneratorBlock() {
		super(durability, true, true, Color.GREEN, null, id, ImageEnum.SPACESHIPPARTBLOCK);
		itemsInBlock = new LinkedList<>();
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
		itemsInBlock.add(new BlackHoleGeneratorItem());
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