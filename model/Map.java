package model;

import java.util.ArrayList;
import java.util.Random;

public class Map {

	private int totalHeight;
	private int airHeight;
	private int dirtDepth;
	private int stoneDepth;
	private int lavaDepth = 10;
	private int totalWidth;
	private Random random;

	private double ironFrequency = 0.01;
	private double mountainFrequency = 0.03;
	private double treeFrequency = 0.05;

	private BuildingBlock[][] map;

	public Map(int totalHeight, int totalWidth, int dirtDepth, int stoneDepth) {
		random = new Random();

		this.totalHeight = totalHeight;
		this.dirtDepth = dirtDepth;
		this.stoneDepth = stoneDepth;
		this.airHeight = (totalHeight - dirtDepth - stoneDepth - lavaDepth);
		this.totalWidth = totalWidth;
		constructMap();
	}

	public BuildingBlock getBuildingBlock(int row, int col) {
		return map[row][col];
	}

	private void constructMap() {
		map = new BuildingBlock[totalHeight][totalWidth];
		addAir();
		addDirt();
		addStone();
		addLava();
		addIron();
		addMountains();
		addTrees();
	}

	private void addAir() {
		for (int i = 0; i < airHeight; i++) {
			for (int j = 0; j < totalWidth; j++) {
				map[i][j] = new AirBlock();
			}
		}
	}

	private void addDirt() {
		for (int i = airHeight; i < (airHeight + dirtDepth); i++) {
			for (int j = 0; j < totalWidth; j++) {
				map[i][j] = new EarthBlock();
			}
		}
	}

	private void addStone() {
		for (int i = (airHeight + dirtDepth); i < (airHeight + dirtDepth + stoneDepth); i++) {
			for (int j = 0; j < totalWidth; j++) {
				map[i][j] = new StoneBlock();
			}
		}
	}

	private void addLava() {
		for (int i = (airHeight + dirtDepth + stoneDepth); i < (airHeight
				+ dirtDepth + stoneDepth + lavaDepth); i++) {
			for (int j = 0; j < totalWidth; j++) {
				map[i][j] = new LavaBlock();
			}
		}
	}

	private void addIron() {
		int totalIronClusters = (int) (ironFrequency * stoneDepth * totalWidth);

		int first_third = totalIronClusters / 10;
		int second_third = totalIronClusters / 40;
		int third_third = totalIronClusters - first_third - second_third;

		for (int i = 0; i < first_third; i++) {
			int startX = random.nextInt(totalWidth);
			int startY = random.nextInt(stoneDepth / 3) + dirtDepth + airHeight;
			map[startY][startX] = new IronOreBlock();
		}
		for (int i = 0; i < second_third; i++) {
			int startX = random.nextInt(totalWidth);
			int startY = random.nextInt(stoneDepth / 3) + dirtDepth + airHeight
					+ stoneDepth / 3;
			map[startY][startX] = new IronOreBlock();
		}
		for (int i = 0; i < third_third; i++) {
			int startX = random.nextInt(totalWidth);
			int startY = random.nextInt(stoneDepth / 3) + dirtDepth + airHeight
					+ 2 * stoneDepth / 3;
			map[startY][startX] = new IronOreBlock();
		}
	}

	private void addMountains() {
		int totalMountains = (int) (mountainFrequency * totalWidth);

		for (int i = 0; i < totalMountains; i++) {
			int X = random.nextInt(totalWidth);
			int widthEstimate = random.nextInt(100);
			int Y = airHeight;

			// up
			for (int j = 0; j < widthEstimate / 2; j++) {
				map[Y][X] = new EarthBlock();
				for (int k = Y; k < airHeight; k++) {
					map[k][X] = new EarthBlock();
				}
				double testValue = Math.random();
				if (testValue < 0.5) {
					X += 1;
					Y -= 1;
				} else if (testValue < 0.95) {
					X += 1;
				} else {
					Y -= 1;
				}
				if (Y < 0)
					Y++;
				if (Y >= airHeight)
					Y--;
				X %= totalWidth;
			}

			// down
			while (true) {
				map[Y][X] = new EarthBlock();
				for (int k = Y; k < airHeight; k++) {
					map[k][X] = new EarthBlock();
				}
				double testValue = Math.random();
				if (testValue < 0.5) {
					X += 1;
					Y += 1;
				} else if (testValue < 0.95) {
					X += 1;
				} else {
					Y += 1;
				}
				if (Y < 0)
					Y++;
				if (Y >= airHeight)
					break;
				X %= totalWidth;
			}
		}
	}

	private void addTrees() {
		int totalTrees = (int) (treeFrequency * totalWidth);

		for (int i = 0; i < totalTrees; i++) {
			int treeX = random.nextInt(totalWidth);
			int treeHeight = random.nextInt(4) + 2;

			// move up until we are no longer underground
			int treeY = airHeight;
			while (map[treeY][treeX].getID().equals(EarthBlock.id)) {
				treeY -= 1;
				if (treeY < 0)
					break;
			}

			// add trunk
			for (int j = 0; j < treeHeight; j++) {
				if (treeY - j >= 0) {
					map[treeY - j][treeX] = new WoodBlock();
				}
			}

			// add leaves
			int leafCount = random.nextInt(20) + 10;
			ArrayList<Integer[]> availableSpaces = new ArrayList<Integer[]>();
			availableSpaces.add(new Integer[] { treeY - treeHeight, treeX });
			int[] offsets = new int[] { -1, 0, 1 };
			for (int j = 0; j < leafCount; j++) {
				Integer[] basePos = availableSpaces.get(random
						.nextInt(availableSpaces.size()));

				int newLeafY = basePos[0] + offsets[random.nextInt(3)];
				int newLeafX = basePos[1] + offsets[random.nextInt(3)];
				int dist = Math.abs(treeY - treeHeight - newLeafY)
						+ (treeX - newLeafX);
				if (dist <= 3 && (newLeafY <= (treeY - treeHeight + 1))) {
					newLeafX = Math.floorMod(newLeafX, totalWidth);
					if (newLeafY >= 0) {
						if (map[newLeafY][newLeafX].getID().equals(AirBlock.id)) {
							map[newLeafY][newLeafX] = new LeafBlock();
							availableSpaces.add(new Integer[] { newLeafY,
									newLeafX });
						}
					}

				}
			}
		}
	}

}
