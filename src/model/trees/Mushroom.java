package model.trees;

import java.util.ArrayList;
import java.util.Random;

import model.actors.Position;
import model.building_blocks.BuildingBlock;
import model.building_blocks.MushroomBlock;
import model.building_blocks.MushroomFruitBlock;
import model.map.Map;

/**
 * @author Ethan Ward
 *
 */
public class Mushroom extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map map;
	private Random random;
	private Position startingPos;
	
	private static int[] getHeightParameters() {
		return new int[] { 7, 2 };
	}

	private static int[] getLeafParameters() {
		return new int[] { 50, 50 };
	}

	private static int[] getFruitParameters() {
		return new int[] { 3, 0 };
	}
	
	public Mushroom(Map map, Random random) {
		super(map, random, getHeightParameters(), getLeafParameters(),
				getFruitParameters());
		this.map = map;
		this.random = random;
	}

	@Override
	public BuildingBlock getTrunkBlock() {
		return new MushroomBlock();
	}

	@Override
	public BuildingBlock getLeafBlock() {
		return new MushroomBlock();
	}

	@Override
	public BuildingBlock getFruitBlock() {
		return new MushroomFruitBlock();
	}

	@Override
	public Position getStartingPos() {
		if (startingPos != null) {
			return startingPos;
		}
		if (map == null) {
			map = super.getMap();
		}
		if (random == null) {
			random = super.getRandom();
		}
		
		ArrayList<Integer[]> cavernFloorBlocks = map.getCavernFloorBlocks();
		
		Integer[] cavernFloorBlock = cavernFloorBlocks.get(random
				.nextInt(cavernFloorBlocks.size()));
		
		this.startingPos = new Position(cavernFloorBlock[0], cavernFloorBlock[1]);
		return startingPos;
	}

}
