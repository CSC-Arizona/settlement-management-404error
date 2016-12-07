package model.trees;

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
import model.building_blocks.CavernBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.LeafBlock;
import model.items.Item;
import model.map.Map;

public abstract class Tree implements Serializable {

	private Map map;
	private Random random;
	private int[] heightParameters;
	private int[] leafParameters;
	private int[] fruitParameters;
	private LinkedList<Item> allLoot;
	private int height;

	private ArrayList<Position> trunk;
	private ArrayList<Position> leaves;
	private ArrayList<Position> fruit;

	abstract public BuildingBlock getTrunkBlock();

	abstract public BuildingBlock getLeafBlock();

	abstract public BuildingBlock getFruitBlock();

	abstract public Position getStartingPos();

	private int getHeight() {
		return random.nextInt(heightParameters[0]) + heightParameters[1];
	}

	public Tree(Map map, Random random, int[] heightParameters,
			int[] leafParameters, int[] fruitParameters) {
		this.map = map;
		this.random = random;
		this.heightParameters = heightParameters;
		this.leafParameters = leafParameters;
		this.fruitParameters = fruitParameters;
		this.height = getHeight();
		this.allLoot = new LinkedList<>();
		this.trunk = new ArrayList<>();
		this.leaves = new ArrayList<>();
		this.fruit = new ArrayList<>();

		addTrunk();
		addLeaves();
		addFruit();

	}

	private void addTrunk() {
		for (int j = 0; j < height; j++) {
			if (getStartingPos().getRow() - j >= 0) {
				trunk.add(new Position(getStartingPos().getRow() - j,
						getStartingPos().getCol()));
			}
		}
	}

	public ArrayList<Position> getTrunk() {
		return trunk;
	}

	private void addLeaves() {
		int leafCount = random.nextInt(leafParameters[0]) + leafParameters[1];
		ArrayList<Position> availableSpaces = new ArrayList<>();
		availableSpaces.add(new Position(getStartingPos().getRow() - height,
				getStartingPos().getCol()));
		int[] offsets = new int[] { -1, 0, 1 };
		for (int j = 0; j < leafCount; j++) {
			Position basePos = availableSpaces.get(random
					.nextInt(availableSpaces.size()));

			int o1 = offsets[random.nextInt(3)];
			int o2 = offsets[random.nextInt(3)];

			if (!(Math.abs(o1) == 1 && Math.abs(o2) == 1)) {
				int newLeafY = basePos.getRow() + o1;
				int newLeafX = basePos.getCol() + o2;

				if ((newLeafY <= (getStartingPos().getRow() - height + 1))) {
					newLeafX = Math.floorMod(newLeafX, map.getTotalWidth());
					if (newLeafY >= 0) {
						if (map.getBuildingBlock(newLeafY, newLeafX).getID()
								.equals(AirBlock.id)
								|| map.getBuildingBlock(newLeafY, newLeafX)
										.getID().equals(CavernBlock.id)) {
							leaves.add(new Position(newLeafY, newLeafX));
							availableSpaces
									.add(new Position(newLeafY, newLeafX));
						}
					}
				}
			}
		}
	}

	private void addFruit() {
		if (leaves.size() != 0) {
			int totalFruit = random.nextInt(fruitParameters[0])
					+ fruitParameters[1];

			for (int i = 0; i < totalFruit; i++) {
				Position fruitPos = leaves.get(random.nextInt(leaves.size()));
				fruit.add(fruitPos);
			}
		}
	}

	public void addToMap() {
		for (Position pos : trunk) {
			if (map.getBuildingBlock(pos).getID().equals(AirBlock.id)
					|| map.getBuildingBlock(pos).getID().equals(CavernBlock.id)) {
				BuildingBlock attb = getTrunkBlock();
				map.setBuildingBlock(pos, attb);
				for (Item i : attb.lootBlock())
					allLoot.add(i);
			}
		}

		for (Position pos : leaves) {
			if (map.getBuildingBlock(pos).getID().equals(AirBlock.id)
					|| map.getBuildingBlock(pos).getID().equals(CavernBlock.id)) {
				BuildingBlock lb = getLeafBlock();
				map.setBuildingBlock(pos, lb);
				for (Item i : lb.lootBlock())
					allLoot.add(i);
			}
		}

		for (Position pos : fruit) {
			if (map.getBuildingBlock(pos).getID().equals(LeafBlock.id)
					|| map.getBuildingBlock(pos).getID().equals(CavernBlock.id)) {
				BuildingBlock atlb = getFruitBlock();
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
					.equals(getTrunkBlock().getID())) {
				map.setBuildingBlock(pos, new AirBlock());
			}
		}

		for (Position pos : leaves) {
			if (map.getBuildingBlock(pos).getID()
					.equals(getLeafBlock().getID())) {
				map.setBuildingBlock(pos, new AirBlock());
			}
		}

		for (Position pos : fruit) {
			if (map.getBuildingBlock(pos).getID()
					.equals(getFruitBlock().getID())) {
				map.setBuildingBlock(pos, new AirBlock());
			}
		}
		map.decrementTreeCount();
	}

	public void designate() {
		for (Position pos : trunk) {
			if (map.getBuildingBlock(pos).getID()
					.equals(getTrunkBlock().getID()))
				map.addDesignation(pos, Designation.CUTTING_DOWN_TREES);

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

	public Random getRandom() {
		return random;
	}

	public Map getMap() {
		return map;
	}

}
