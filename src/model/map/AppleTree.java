package model.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import controller.Designation;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.AppleTreeLeafBlock;
import model.building_blocks.AppleTreeTrunkBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.LeafBlock;
import model.game.Game;
import model.items.Item;

public class AppleTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7139983639712762055L;
	private ArrayList<Position> trunk;
	private ArrayList<Integer[]> leaves;
	private ArrayList<Integer[]> apples;
	private LinkedList<Item> allLoot;

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
		trunk = new ArrayList<Position>();
		leaves = new ArrayList<Integer[]>();
		apples = new ArrayList<Integer[]>();
		this.totalWidth = totalWidth;
		this.airHeight = airHeight;
		this.map = map;
		this.random = random;
		this.allLoot = new LinkedList<>();

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
				trunk.add(new Position(treeY - j, treeX));
			}
		}
	}

	public ArrayList<Position> getTrunk() {
		return trunk;
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
		for (Position pos : trunk) {
			if (map[pos.getRow()][pos.getCol()].getID().equals(AirBlock.id)) {
				AppleTreeTrunkBlock attb = new AppleTreeTrunkBlock();
				map[pos.getRow()][pos.getCol()] = attb;
				for (Item i : attb.lootBlock())
					allLoot.add(i);
			}
		}

		for (Integer[] pos : leaves) {
			if (map[pos[0]][pos[1]].getID().equals(AirBlock.id)) {
				LeafBlock lb = new LeafBlock();
				map[pos[0]][pos[1]] = lb;
				for (Item i : lb.lootBlock())
					allLoot.add(i);
			}
		}

		for (Integer[] pos : apples) {
			if (map[pos[0]][pos[1]].getID().equals(LeafBlock.id)) {
				AppleTreeLeafBlock atlb = new AppleTreeLeafBlock();
				map[pos[0]][pos[1]] = atlb;
				for (Item i : atlb.lootBlock())
					allLoot.add(i);
			}
		}

	}

	/**
	 * tree is destroyed
	 */
	public void removeFromMap() {
		for (Position pos : trunk) {
			map[pos.getRow()][pos.getCol()] = new AirBlock();
		}

		for (Integer[] pos : leaves) {
			map[pos[0]][pos[1]] = new AirBlock();
		}

		for (Integer[] pos : apples) {
			map[pos[0]][pos[1]] = new AirBlock();
		}
		Game.getMap().decrementTreeCount();
	}

	public void designate() {
		for (Position pos : trunk) {
			if (map[pos.getRow()][pos.getCol()].getID().equals(
					AppleTreeTrunkBlock.id))
				map[pos.getRow()][pos.getCol()]
						.addDesignation(Designation.CUTTING_DOWN_TREES);
		}
	}

	public void removeDesignation() {
		for (Position pos : trunk) {
			map[pos.getRow()][pos.getCol()].addDesignation(Designation.NONE);
		}
	}

	public List<Item> getAllLoot() {
		return allLoot;
	}

}
