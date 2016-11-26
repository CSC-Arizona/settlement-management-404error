package model.Map;

import java.util.ArrayList;
import java.util.Random;

import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.CavernBlock;
import model.BuildingBlocks.MushroomBlock;
import model.BuildingBlocks.MushroomFruitBlock;

/**
 * note: this should be merged with Tree class but I need to figure out how to
 * get generic parameters to work right
 * 
 * @author Ethan Ward
 *
 */
public class Mushroom {

	private ArrayList<Integer[]> trunk;
	private ArrayList<Integer[]> leaves;
	private ArrayList<Integer[]> fruit;

	private Random random;
	private int totalWidth;
	private BuildingBlock[][] map;

	private int treeHeight;
	private int treeX;
	private int treeY;
	private int[] heightParameters = new int[] { 7, 2 };
	private int[] leafParameters = new int[] { 50, 50 };
	private int[] fruitParameters = new int[] { 3, 0 };

	/**
	 * Construct a random tree
	 * 
	 * @param totalWidth
	 * @param airHeight
	 * @param map
	 * @param random
	 */
	public Mushroom(int totalWidth, BuildingBlock[][] map, Random random,
			Integer[] startingPos) {
		trunk = new ArrayList<Integer[]>();
		leaves = new ArrayList<Integer[]>();
		fruit = new ArrayList<Integer[]>();
		this.totalWidth = totalWidth;
		this.map = map;
		this.random = random;

		treeHeight = random.nextInt(heightParameters[0]) + heightParameters[1];

		treeX = startingPos[1];
		treeY = startingPos[0];

		addTrunk();
		addLeaves();
		addFruit();

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
						if (map[newLeafY][newLeafX].getID().equals(
								CavernBlock.id)) {
							leaves.add(new Integer[] { newLeafY, newLeafX });
							availableSpaces.add(new Integer[] { newLeafY,
									newLeafX });
						}
					}
				}
			}
		}
	}

	private void addFruit() {
		if (leaves.size() != 0) {
			int totalApples = random.nextInt(fruitParameters[0])
					+ fruitParameters[1];

			for (int i = 0; i < totalApples; i++) {
				Integer[] applePos = leaves.get(random.nextInt(leaves.size()));
				fruit.add(applePos);
			}
		}
	}

	/**
	 * tree is added
	 */
	public void addToMap() {
		for (Integer[] pos : trunk) {
			map[pos[0]][pos[1]] = new MushroomBlock();
		}

		for (Integer[] pos : leaves) {
			if (map[pos[0]][pos[1]].getID().equals(CavernBlock.id)) {
				map[pos[0]][pos[1]] = new MushroomBlock();
			}
		}

		for (Integer[] pos : fruit) {
			if (map[pos[0]][pos[1]].getID().equals(MushroomBlock.id)) {
				map[pos[0]][pos[1]] = new MushroomFruitBlock();
			}
		}

	}

	/**
	 * tree is destroyed
	 */
	public void removeFromMap() {
		for (Integer[] pos : trunk) {
			map[pos[0]][pos[1]] = new CavernBlock();
		}

		for (Integer[] pos : leaves) {
			map[pos[0]][pos[1]] = new CavernBlock();
		}

		for (Integer[] pos : fruit) {
			map[pos[0]][pos[1]] = new CavernBlock();
		}
	}

}
