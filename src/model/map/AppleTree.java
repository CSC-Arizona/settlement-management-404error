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
import model.building_blocks.EarthBlock;
import model.building_blocks.LeafBlock;
import model.items.Item;

public class AppleTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7139983639712762055L;
	private ArrayList<Position> trunk;
	private ArrayList<Position> leaves;
	private ArrayList<Position> apples;
	private LinkedList<Item> allLoot;
	private Map map;

	private Random random;
	private int totalWidth;
	private int airHeight;

	private int treeHeight;
	private Position startingPos;
	private int[] heightParameters = new int[] { 7, 2 };
	private int[] leafParameters = new int[] { 50, 50 };
	private int[] appleParameters = new int[] { 3, 0 };

	private Position getStartingPos() {
		int treeX = random.nextInt(totalWidth);

		// move up until we are no longer underground
		int treeY = airHeight;
		while (!map.getBuildingBlock(treeY, treeX).getID()
				.equals(AirBlock.id)) {
			treeY -= 1;
			if (treeY < 0)
				break;
		}
		return new Position(treeY, treeX);
	}

	/**
	 * Construct a random tree
	 * 
	 * @param totalWidth
	 * @param airHeight
	 * @param map
	 * @param random
	 */
	public AppleTree(int totalWidth, int airHeight, Map map, Random random) {
		trunk = new ArrayList<Position>();
		leaves = new ArrayList<Position>();
		apples = new ArrayList<Position>();
		this.totalWidth = totalWidth;
		this.airHeight = airHeight;
		this.random = random;
		this.allLoot = new LinkedList<>();
		this.map = map;

		treeHeight = random.nextInt(heightParameters[0]) + heightParameters[1];

		startingPos = getStartingPos();

		if (map
				.getBuildingBlock(startingPos.getRow() + 1,
						startingPos.getCol()).getID().equals(EarthBlock.id)) {
			addTrunk();
			addLeaves();
			addApples();

		}
	}

	private void addTrunk() {
		for (int j = 0; j < treeHeight; j++) {
			if (startingPos.getRow() - j >= 0) {
				trunk.add(new Position(startingPos.getRow() - j, startingPos
						.getCol()));
			}
		}
	}

	public ArrayList<Position> getTrunk() {
		return trunk;
	}

	private void addLeaves() {
		int leafCount = random.nextInt(leafParameters[0]) + leafParameters[1];
		ArrayList<Integer[]> availableSpaces = new ArrayList<Integer[]>();
		availableSpaces.add(new Integer[] { startingPos.getRow() - treeHeight,
				startingPos.getCol() });
		int[] offsets = new int[] { -1, 0, 1 };
		for (int j = 0; j < leafCount; j++) {
			Integer[] basePos = availableSpaces.get(random
					.nextInt(availableSpaces.size()));

			int o1 = offsets[random.nextInt(3)];
			int o2 = offsets[random.nextInt(3)];

			if (!(Math.abs(o1) == 1 && Math.abs(o2) == 1)) {
				int newLeafY = basePos[0] + o1;
				int newLeafX = basePos[1] + o2;

				if ((newLeafY <= (startingPos.getRow() - treeHeight + 1))) {
					newLeafX = Math.floorMod(newLeafX, totalWidth);
					if (newLeafY >= 0) {
						if (map.getBuildingBlock(newLeafY, newLeafX)
								.getID().equals(AirBlock.id)) {
							leaves.add(new Position(newLeafY, newLeafX));
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
				Position applePos = leaves.get(random.nextInt(leaves.size()));
				apples.add(applePos);
			}
		}
	}

	/**
	 * tree is added
	 */
	public void addToMap() {
		for (Position pos : trunk) {
			if (map.getBuildingBlock(pos).getID().equals(AirBlock.id)) {
				AppleTreeTrunkBlock attb = new AppleTreeTrunkBlock();
				map.setBuildingBlock(pos, attb);
				for (Item i : attb.lootBlock())
					allLoot.add(i);
			}
		}

		for (Position pos : leaves) {
			if (map.getBuildingBlock(pos).getID().equals(AirBlock.id)) {
				LeafBlock lb = new LeafBlock();
				map.setBuildingBlock(pos, lb);
				for (Item i : lb.lootBlock())
					allLoot.add(i);
			}
		}

		for (Position pos : apples) {
			if (map.getBuildingBlock(pos).getID()
					.equals(LeafBlock.id)) {
				AppleTreeLeafBlock atlb = new AppleTreeLeafBlock();
				map.setBuildingBlock(pos, atlb);
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
			if (map.getBuildingBlock(pos).getID()
					.equals(AppleTreeTrunkBlock.id)) {
				map.setBuildingBlock(pos, new AirBlock());
			}
		}

		for (Position pos : leaves) {
			if (map.getBuildingBlock(pos).getID()
					.equals(LeafBlock.id)) {
				map.setBuildingBlock(pos, new AirBlock());
			}
		}

		for (Position pos : apples) {
			if (map.getBuildingBlock(pos).getID()
					.equals(AppleTreeLeafBlock.id)) {
				map.setBuildingBlock(pos, new AirBlock());
			}
		}
		map.decrementTreeCount();
	}

	public void designate() {
		for (Position pos : trunk) {
			if (map.getBuildingBlock(pos).getID()
					.equals(AppleTreeTrunkBlock.id))
				map.addDesignation(pos,
						Designation.CUTTING_DOWN_TREES);

		}
	}

	public void removeDesignation() {
		for (Position pos : trunk) {
			map.addDesignation(pos, Designation.NONE);
		}
	}

	public List<Item> getAllLoot() {
		return allLoot;
	}

}
