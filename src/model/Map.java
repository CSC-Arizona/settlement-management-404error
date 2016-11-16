package model;

import java.util.ArrayList;
import java.util.Random;

import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.AntTunnelBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.BushBlock;
import model.BuildingBlocks.CavernBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.GoldOreBlock;
import model.BuildingBlocks.IronOreBlock;
import model.BuildingBlocks.LavaBlock;
import model.BuildingBlocks.LeafBlock;
import model.BuildingBlocks.StoneBlock;
import model.BuildingBlocks.WoodBlock;

/**
 * Constructs a random map with various geographical features
 * 
 * @author Ethan Ward
 *
 */
public class Map {

	private int totalHeight;
	private int airHeight;
	private int dirtDepth;
	private int stoneDepth;
	private int lavaDepth = 10;
	private int totalWidth;
	private Random random;

	private double ironFrequency = 0.01;
	private double goldFrequency = 0.001;
	private double mountainFrequency = 0.03;
	private double treeFrequency = 0.05;
	private double bushFrequency = 0.05;
	private int numberOfAntColonies = 2;

	private BuildingBlock[][] map;

	public Map(int totalHeight, int totalWidth, int dirtDepth, int stoneDepth, int seed) {
		random = new Random(seed);

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
		addStone();
		addDirt();
		addLava();
		addIron();
		addGold();
		addMountains();
		addAntColonies();
		addTrees();
		addBushes();
	}

	private void addAir() {
		for (int i = 0; i < airHeight; i++) {
			for (int j = 0; j < totalWidth; j++) {
				map[i][j] = new AirBlock();
			}
		}
	}

	private void addDirt() {
		int randomEdge = random.nextInt(10);
		for (int j = 0; j < totalWidth; j++) {
			for (int i = airHeight; i < (airHeight + dirtDepth); i++) {
				map[i][j] = new EarthBlock();
			}
			if (randomEdge <= 2) {
				if (random.nextDouble() < 0.8) {
					randomEdge += 1;
				} else {
					if (random.nextDouble() < 0.6) {
						randomEdge -= 1;
					}
				}
			} else {
				if (random.nextDouble() < 0.2) {
					randomEdge += 1;
				} else {
					if (random.nextDouble() < 0.6) {
						randomEdge -= 1;
					}
				}
			}
			if (randomEdge < 0)
				randomEdge += 1;
			for (int i = (airHeight + dirtDepth); i < (airHeight + dirtDepth + randomEdge); i++) {
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

		for (int i = 0; i < second_third; i++) {
			int startX = random.nextInt(totalWidth);
			int startY = random.nextInt(stoneDepth / 3) + dirtDepth + airHeight
					+ stoneDepth / 3;
			if (map[startY][startX].getID().equals(StoneBlock.id)) {
				map[startY][startX] = new IronOreBlock();
			}
		}
		for (int i = 0; i < third_third; i++) {
			int startX = random.nextInt(totalWidth);
			int startY = random.nextInt(stoneDepth / 3) + dirtDepth + airHeight
					+ 2 * stoneDepth / 3;
			if (map[startY][startX].getID().equals(StoneBlock.id)) {
				map[startY][startX] = new IronOreBlock();
			}
		}
	}

	private void addGold() {
		int totalGoldClusters = (int) (goldFrequency * stoneDepth * totalWidth);
		for (int i = 0; i < totalGoldClusters; i++) {
			int startX = random.nextInt(totalWidth);
			int startY = random.nextInt(stoneDepth / 5) + dirtDepth + airHeight
					+ 4 * stoneDepth / 5;
			if (map[startY][startX].getID().equals(StoneBlock.id)) {
				map[startY][startX] = new GoldOreBlock();
			}
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
				double testValue = random.nextDouble();
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
				double testValue = random.nextDouble();
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
			while (!map[treeY][treeX].getID().equals(AirBlock.id)) {
				treeY -= 1;
				if (treeY < 0)
					break;
			}

			if (map[treeY + 1][treeX].getID().equals(EarthBlock.id)) {

				// add trunk
				for (int j = 0; j < treeHeight; j++) {
					if (treeY - j >= 0) {
						map[treeY - j][treeX] = new WoodBlock();
					}
				}

				// add leaves
				int leafCount = random.nextInt(20) + 20;
				ArrayList<Integer[]> availableSpaces = new ArrayList<Integer[]>();
				availableSpaces
						.add(new Integer[] { treeY - treeHeight, treeX });
				int[] offsets = new int[] { -1, 0, 1 };
				for (int j = 0; j < leafCount; j++) {
					Integer[] basePos = availableSpaces.get(random
							.nextInt(availableSpaces.size()));

					int o1 = offsets[random.nextInt(3)];
					int o2 = offsets[random.nextInt(3)];

					if (!(Math.abs(o1) == 1 && Math.abs(o2) == 1)) {
						int newLeafY = basePos[0] + o1;
						int newLeafX = basePos[1] + o2;
						int dist = Math.abs(treeY - treeHeight - newLeafY)
								+ (treeX - newLeafX);
						if (dist <= 3 && (newLeafY <= (treeY - treeHeight + 1))) {
							newLeafX = Math.floorMod(newLeafX, totalWidth);
							if (newLeafY >= 0) {
								if (map[newLeafY][newLeafX].getID().equals(
										AirBlock.id)) {
									map[newLeafY][newLeafX] = new LeafBlock();
									availableSpaces.add(new Integer[] {
											newLeafY, newLeafX });
								}
							}
						}
					}
				}
			}
		}
	}

	private void addBushes() {
		int totalBushes = (int) (bushFrequency * totalWidth);

		for (int i = 0; i < totalBushes; i++) {
			int bushX = random.nextInt(totalWidth);

			// move up until we are no longer underground
			int bushY = airHeight;
			while (!map[bushY][bushX].getID().equals(AirBlock.id)) {
				bushY -= 1;
				if (bushY < 0)
					break;
			}
			if (map[bushY + 1][bushX].getID().equals(EarthBlock.id)) {
				if (map[bushY][bushX].getID().equals(AirBlock.id)) {
					map[bushY][bushX] = new BushBlock();
				}
			}

		}
	}

	private void addAntColonies() {
		for (int i = 0; i < numberOfAntColonies; i++) {
			int startX = random.nextInt(totalWidth);
			int height = random.nextInt(2) + 5;

			int startY = airHeight;
			// move up until we are no longer underground
			while (map[startY][startX].getID().equals(EarthBlock.id)
					|| map[startY][startX].getID().equals(AntTunnelBlock.id)) {
				startY -= 1;
				if (startY < 0)
					break;
			}
			startY -= height;
			if (startY < 0) {
				startY = 0;
			}

			for (int j = startY; j < airHeight; j++) {
				int width = 2 * (j - startY);
				for (int k = (-width + 1); k < width; k++) {
					int row = j;
					int col = Math.floorMod(startX + k, totalWidth);
					if (k != 0) {

						if (map[row][col].getID().equals(AirBlock.id)) {
							map[row][col] = new AntTunnelBlock();
						}
					} else {
						map[row][col] = new CavernBlock();
					}
				}
			}

			// TODO: add tunnels / chambers

		}
	}
}