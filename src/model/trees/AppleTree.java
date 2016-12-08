package model.trees;

import java.util.Random;

import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.AppleTreeLeafBlock;
import model.building_blocks.AppleTreeTrunkBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.LeafBlock;
import model.map.Map;

public class AppleTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map map;
	private Random random;
	private Position startingPos;

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

		while (true) {
			int treeX = random.nextInt(map.getTotalWidth());

			// move up until we are no longer underground
			int treeY = map.getMapParameters().airHeight;
			while (!map.getBuildingBlock(treeY, treeX).getID()
					.equals(AirBlock.id)) {
				treeY -= 1;
				if (treeY < 0)
					continue;
			}
			if (map.getBuildingBlock(treeY + 1, treeX).getID()
					.equals(EarthBlock.id)) {
				this.startingPos = new Position(treeY, treeX);
				break;
			}
		}
		return startingPos;
	}

	private static int[] getHeightParameters() {
		return new int[] { 7, 2 };
	}

	private static int[] getLeafParameters() {
		return new int[] { 50, 50 };
	}

	private static int[] getAppleParameters() {
		return new int[] { 3, 0 };
	}

	public AppleTree(Map map, Random random) {
		super(map, random, getHeightParameters(), getLeafParameters(),
				getAppleParameters());

		this.random = random;
		this.map = map;
		getStartingPos();

	}

	@Override
	public BuildingBlock getTrunkBlock() {
		return new AppleTreeTrunkBlock();
	}

	@Override
	public BuildingBlock getLeafBlock() {
		return new LeafBlock();
	}

	@Override
	public BuildingBlock getFruitBlock() {
		return new AppleTreeLeafBlock();
	}

}
