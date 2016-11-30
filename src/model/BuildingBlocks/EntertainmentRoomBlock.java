package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;

public class EntertainmentRoomBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8963951396251379906L;
	private Furniture furniture;
	private List<Actor> actorsInBlock;
	private List<Item> itemsOnGround;
	private final static int durability = 0;
	public final static String id = "Entertainment Lounge";
	
	public EntertainmentRoomBlock() {
		super(durability, false, true, new Color(224, 224, 224), id);
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return null;
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
		if (this.furniture == null) {
			this.furniture = furniture;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeFurniture() {
		if (this.furniture != null) {
			this.furniture = null;
			return true;
		}
		return false;
	}

	@Override
	public Furniture getFurniture() {
		return furniture;
	}

	@Override
	public List<Item> itemsOnGround() {
		return itemsOnGround;
	}


}