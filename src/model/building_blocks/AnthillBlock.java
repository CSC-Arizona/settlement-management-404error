package model.building_blocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.actors.Actor;
import model.furniture.Furniture;
import model.items.AntLarvaItem;
import model.items.Item;
import model.items.StoneItem;

/**
 * AntTunnelBlock make up ant tunnels. When they are destroyed they drop 
 * AntLarvaItems.
 * 
 * @author Katherine Walters
 */
public class AnthillBlock extends BuildingBlock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1383914857842009037L;
	private List<Actor> actorsInBlock;
	private List<Item> itemsOnGround;
	private final static int durability = 1;
	private List<Item> itemsInBlock;
	public final static String id = "Anthill";
	public AnthillBlock() {
		super(durability, true, false, Color.GRAY, null, id, null);

		itemsInBlock = new LinkedList<>();
		for (int i = 0; i < 3; i++)
		    itemsInBlock.add(new AntLarvaItem());
		Random rand = new Random();
		int num = rand.nextInt(100);
		if (num < 30) {
			itemsInBlock.add(new StoneItem());
		}
		actorsInBlock = new LinkedList<>();
		itemsOnGround = new LinkedList<>();
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
		return null;
	}

}
