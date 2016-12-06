package model.building_blocks;

import images.ImageEnum;

import java.awt.Color;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;

public class BlackHoleGeneratorBlock extends BuildingBlock {

	private final static int durability = 7;
	public final static String id = "Space ship part";
	
	public BlackHoleGeneratorBlock() {
		super(durability, true, true, Color.GREEN, null, id, ImageEnum.SPACESHIPPARTBLOCK);
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
