package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.furniture.Furniture;
import model.items.IronItem;
import model.items.Item;
import model.items.RetroencabulatorItem;
import model.items.StoneItem;

/**
 * Spaceship which can be upgraded to win the game
 * 
 * @author Ethan Ward
 *
 */
public class Spaceship extends Room {

	private List<Item> requiredUpgradeMaterials;
	
	public Spaceship(Position pos) {
		super(5, 5, 5, 1, pos);
	}

	@Override
	public TreeMap<Position, Furniture> getFurniture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getRequiredBuildMaterials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getRequiredUpgradeMaterials() {
		requiredUpgradeMaterials = new LinkedList<>();
		if (this.getUpgradesAllowed() == 1) {
			requiredUpgradeMaterials.add(new RetroencabulatorItem());
		}
		return requiredUpgradeMaterials;
	}

	@Override
	public int increaseCapacityBy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BuildingBlock getAppropriateBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void performUpgrade(int upgradeNum) {
		// TODO Auto-generated method stub
		
	}

}
