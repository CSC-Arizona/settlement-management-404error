package model.BuildingBlocks;

import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

import model.Actors.Actor;
import model.Furniture.Furniture;
import model.Items.Item;

/**
 * AirBlock represents any blank space above ground that is visible.. I'm not
 * sure where we're going to start the game so I added it in case
 * 
 * @author Katherine Walters
 */
public class AirBlock extends BuildingBlock {

	private Furniture furniture;
	private List<Actor> actorsInBlock;
	private List<Item> itemsOnGround;
	private final static int durability = 0;
	public final static String id = "Air";
	private boolean occupiable = true;

	public AirBlock() {
		super(durability, false, true, new Color(206, 237, 240), id);
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return null;
	}

	@Override
	public boolean addActor(Actor actor) {
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