package model.BuildingBlocks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Items.AntLarvaItem;
import model.Items.Item;

/**
 * AntTunnelBlock make up ant tunnels. When they are destroyed they drop 
 * AntLarvaItems.
 * 
 * @author Katherine Walters
 */
public class AntTunnelBlock extends BuildingBlock {

	private List<Actor> actorsInBlock;
	private final static int durability = 7;
	private List<Item> itemsInBlock;
	public final static String id = "Ant tunnel";
	public AntTunnelBlock() {
		super(durability, true, true, Color.BLACK, id);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new AntLarvaItem());
		actorsInBlock = new LinkedList<>();
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
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

}