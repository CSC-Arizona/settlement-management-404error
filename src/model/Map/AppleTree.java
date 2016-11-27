package model.Map;

import java.util.ArrayList;
import java.util.Random;

import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.AppleTreeLeafBlock;
import model.BuildingBlocks.AppleTreeTrunkBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.LeafBlock;

public class AppleTree {

	private ArrayList<Integer[]> trunk;
	private ArrayList<Integer[]> leaves;
	private ArrayList<Integer[]> apples;

	private Random random;
	private int totalWidth;
	private int airHeight;
	private BuildingBlock[][] map;

	private int treeHeight;
	private int treeX;
	private int treeY;
	private int[] heightParameters = new int[] { 7, 2 };
	private int[] leafParameters = new int[] { 50, 50 };
	private int[] appleParameters = new int[] { 3, 0 };

	private int[] getStartingPos() {
		int treeX = random.nextInt(totalWidth);

		// move up until we are no longer underground
		int treeY = airHeight;
		while (!map[treeY][treeX].getID().equals(AirBlock.id)) {
			treeY -= 1;
			if (treeY < 0)
				break;
		}
		return new int[] { treeX, treeY };
	}

	/**
	 * Construct a random tree
	 * 
	 * @param totalWidth
	 * @param airHeight
	 * @param map
	 * @param random
	 */
	public AppleTree(int totalWidth, int airHeight, BuildingBlock[][] map,
			Random random) {
		trunk = new ArrayList<Integer[]>();
		leaves = new ArrayList<Integer[]>();
		apples = new ArrayList<Integer[]>();
		this.totalWidth = totalWidth;
		this.airHeight = airHeight;
		this.map = map;
		this.random = random;

		treeHeight = random.nextInt(heightParameters[0]) + heightParameters[1];

		int[] startingPos = getStartingPos();

		treeX = startingPos[0];
		treeY = startingPos[1];

		if (map[treeY + 1][treeX].getID().equals(EarthBlock.id)) {
			addTrunk();
			addLeaves();
			addApples();

		}
	}

	private void addTrunk() {
		for (int j = 0; j < treeHeight; j++) {
			if (treeY - j >= 0) {
				trunk.add(new Integer[] { treeY - j, treeX });
			}
		}
	}

	private void addLeaves() {
		int leafCount = random.nextInt(leafParameters[0]) + leafParameters[1];
		ArrayList<Integer[]> availableSpaces = new ArrayList<Integer[]>();
		availableSpaces.add(new Integer[] { treeY - treeHeight, treeX });
		int[] offsets = new int[] { -1, 0, 1 };
		for (int j = 0; j < leafCount; j++) {
			Integer[] basePos = availableSpaces.get(random
					.nextInt(availableSpaces.size()));

			int o1 = offsets[random.nextInt(3)];
			int o2 = offsets[random.nextInt(3)];

			if (!(Math.abs(o1) == 1 && Math.abs(o2) == 1)) {
				int newLeafY = basePos[0] + o1;
				int newLeafX = basePos[1] + o2;

				if ((newLeafY <= (treeY - treeHeight + 1))) {
					newLeafX = Math.floorMod(newLeafX, totalWidth);
					if (newLeafY >= 0) {
						if (map[newLeafY][newLeafX].getID().equals(AirBlock.id)) {
							leaves.add(new Integer[] { newLeafY, newLeafX });
							availableSpaces.add(new Integer[] { newLeafY,
									newLeafX });
						}
					}
				}
			}
		}
	}

	private void addApples() {
		if (leaves.size() != 0) {
			int totalApples = random.nextInt(appleParameters[0])
					+ appleParameters[1];

			for (int i = 0; i < totalApples; i++) {
				Integer[] applePos = leaves.get(random.nextInt(leaves.size()));
				apples.add(applePos);
			}
		}
	}

	/**
	 * tree is added
	 */
	public void addToMap() {
		for (Integer[] pos : trunk) {
			if (map[pos[0]][pos[1]].getID().equals(AirBlock.id)) {
				map[pos[0]][pos[1]] = new AppleTreeTrunkBlock();
			}
		}

		for (Integer[] pos : leaves) {
			if (map[pos[0]][pos[1]].getID().equals(AirBlock.id)) {
				map[pos[0]][pos[1]] = new LeafBlock();
			}
		}

		for (Integer[] pos : apples) {
			if (map[pos[0]][pos[1]].getID().equals(LeafBlock.id)) {
				map[pos[0]][pos[1]] = new AppleTreeLeafBlock();
			}
		}

	}

	/**
	 * tree is destroyed
	 */
	public void removeFromMap() {
		for (Integer[] pos : trunk) {
			map[pos[0]][pos[1]] = new AirBlock();
		}

		for (Integer[] pos : leaves) {
			map[pos[0]][pos[1]] = new AirBlock();
		}

		for (Integer[] pos : apples) {
			map[pos[0]][pos[1]] = new AirBlock();
		}
	}

}
