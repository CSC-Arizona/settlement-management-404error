package model.building_blocks;

import images.ImageEnum;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.AntLarvaItem;
import model.items.Item;

/**
 * AntTunnelBlock make up ant tunnels.
 * 
 * @author Katherine Walters
 */
public class AntTunnelBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1383914857842009037L;
	private List<Actor> actorsInBlock;
	private List<Item> itemsOnGround;
	private final static int durability = 7;
	private List<Item> itemsInBlock;
	public final static String id = "Ant tunnel";

	public AntTunnelBlock() {
		super(durability, true, false, new Color(102, 72, 32), null, id,
				ImageEnum.ANTTUNNEL);

		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new AntLarvaItem());
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
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
