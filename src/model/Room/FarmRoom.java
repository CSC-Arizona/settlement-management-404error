package model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.Actors.Position;
import model.BuildingBlocks.AppleTreeLeafBlock;
import model.BuildingBlocks.AppleTreeTrunkBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.WheatBlock;
import model.Items.Item;

/**
 * The FarmRoom can't be upgraded but it can be replanted. It contains a state instance variable
 * which communicates how far along the current plot is. 
 * 
 * @author Katherine Walters
 */
public class FarmRoom extends Room {

	// state of 0 = empty plots, state of 1 = growing plots, state of 2 = ready to harvest
	private int state;
	private List<BuildingBlock> plantedBlocks;
	private Random rand;
	
	public FarmRoom(Position p) {
		super(2, 8, 4, 0, p);
		state = 0;
		plantedBlocks = new LinkedList<>();
		rand = new Random();
	}

	@Override
	public List<Item> getRequiredBuildMaterials() {
		return new LinkedList<>();
	}

	@Override
	public List<Item> getRequiredUpgradeMaterials() {
		return new LinkedList<>();
	}

	@Override
	public int increaseCapacityBy() {
		return 0;
	}
	
	/*
	 * Plants the argument number of wheat and apple seeds in the plot. I think this would
	 * be the place to implement a randomized (aka more realistic) yield per seed rate/
	 * put a limit on how many seeds can be planted at once.
	 */
	public boolean plant(int numWheat, int numApples) {
		if (state == 0) {
			int plantedWheat = rand.nextInt(numWheat/2 + 1) + (numWheat/2);
			int plantedApples = rand.nextInt(numApples/2 + 1) + (numApples/2);
		    for (int i = 0; i < plantedWheat; i++)
			    plantedBlocks.add(new WheatBlock());
		    for (int a = 0; a < plantedApples; a++) {
			    plantedBlocks.add(new AppleTreeLeafBlock());
	            plantedBlocks.add(new AppleTreeTrunkBlock());
		    }
		    this.state++;
		    return true;
		} else {
			System.out.println("You can't plant anything in an already planted field");
			return false;
		}
	}
	
	public int getState() {
		return this.state;
	}
	
	/*
	 * I'm assuming we'll have to adjust this every certain number of game ticks
	 */
	public void advanceState() {
		if (this.state != 0)
			this.state++;
	}
	
	/*
	 * Returns a list of Items containing the drops from however many Blocks were planted
	 * in the Room, and sets the state to 0 and the plantedBlocks list back to an empty list.
	 */
	public List<Item> harvest() {
		if (state == 2) {
			List<Item> yield = new LinkedList<>();
			for (BuildingBlock b : plantedBlocks) {
				for (Item i : b.lootBlock())
					yield.add(i);
			}
			plantedBlocks = new LinkedList<>();
			state = 0;
			return yield;
		} else
			return null;
	}
}