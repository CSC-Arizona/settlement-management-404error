package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.AntTunnelBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.GrassBlock;
import model.BuildingBlocks.CavernBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.GoldOreBlock;
import model.BuildingBlocks.IronOreBlock;
import model.BuildingBlocks.LavaBlock;
import model.BuildingBlocks.StoneBlock;

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

	private ArrayList<AppleTree> trees;
	private ArrayList<Integer[]> cavernFloorBlocks;

	private double ironFrequency = 0.01;
	private double goldFrequency = 0.001;
	private double mountainFrequency = 0.03;
	private double treeFrequency = 0.05;
	private double grassFrequency = 0.1;
	private double caveFrequency = 0.005;
	private int numberOfAntColonies = 2;

	private BuildingBlock[][] map;

	public Map(int totalHeight, int totalWidth, int dirtDepth, int stoneDepth,
			int seed) {
		random = new Random(seed);

		this.totalHeight = totalHeight;
		this.dirtDepth = dirtDepth;
		this.stoneDepth = stoneDepth;
		this.airHeight = (totalHeight - dirtDepth - stoneDepth - lavaDepth);
		this.totalWidth = totalWidth;
		constructMap();
	}

	public Map(BuildingBlock[][] map) {
		this.totalHeight = map.length;
		this.totalWidth = map[0].length;
		this.map = map;
	}

	public BuildingBlock getBuildingBlock(int row, int col) {
		return map[row][col];
	}

	/**
	 * @return the totalHeight
	 */
	public int getTotalHeight() {
		return totalHeight;
	}

	/**
	 * @return the totalWidth
	 */
	public int getTotalWidth() {
		return totalWidth;
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
		addCaves();
		addMushrooms();
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
		trees = new ArrayList<AppleTree>();
		int totalTrees = (int) (treeFrequency * totalWidth);

		for (int i = 0; i < totalTrees; i++) {
			AppleTree tree = new AppleTree(totalWidth, airHeight, map, random);
			tree.addToMap();
			trees.add(tree);
		}
	}

	private void addBushes() {
		int totalBushes = (int) (grassFrequency * totalWidth);

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
					map[bushY][bushX] = new GrassBlock();
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

			for (int j = 0; j < random.nextInt(3) + 1; j++) {
				addAntTunnel(startX);
			}

		}
	}

	private void addAntTunnel(int startX) {
		int tunnelSize = random.nextInt(200) + 400;
		int tunnelX = startX;
		int tunnelY = airHeight - 1;
		int[] offsetsX = new int[] { -1, 0, 1 };
		int[] offsetsY = new int[] { 1, 1, -1, 0, 1 };
		int newTunnelX;
		int newTunnelY;
		int directionX = 0;
		int directionY = 0;
		boolean inChamber = false;
		for (int j = 0; j < tunnelSize; j++) {
			if (inChamber) {
				newTunnelX = tunnelX + offsetsX[random.nextInt(3)];
				newTunnelY = tunnelY + offsetsY[random.nextInt(5)];
				newTunnelX = Math.floorMod(newTunnelX, totalWidth);
				if (newTunnelY > 0
						&& newTunnelY >= airHeight
						&& (map[newTunnelY][newTunnelX].getID().equals(
								EarthBlock.id) || map[newTunnelY][newTunnelX]
								.getID().equals(CavernBlock.id))
						&& (map[newTunnelY - 1][newTunnelX].getID()
								.equals(EarthBlock.id))) {

					map[newTunnelY][newTunnelX] = new CavernBlock();
					tunnelX = newTunnelX;
					tunnelY = newTunnelY;
				}
				if (random.nextDouble() < 0.01) {
					inChamber = false;
					directionX = offsetsX[random.nextInt(3)];
					directionY = offsetsY[random.nextInt(5)];
				}
			} else {
				newTunnelX = tunnelX + directionX;
				newTunnelY = tunnelY + directionY;
				newTunnelX = Math.floorMod(newTunnelX, totalWidth);
				if (newTunnelY > 0
						&& newTunnelY >= airHeight
						&& (map[newTunnelY][newTunnelX].getID().equals(
								EarthBlock.id) || map[newTunnelY][newTunnelX]
								.getID().equals(CavernBlock.id))
						&& (map[newTunnelY - 1][newTunnelX].getID()
								.equals(EarthBlock.id))) {

					map[newTunnelY][newTunnelX] = new CavernBlock();
					tunnelX = newTunnelX;
					tunnelY = newTunnelY;
				}
				if (random.nextDouble() < 0.1) {
					inChamber = true;
				}
			}

		}
	}

	/**
	 * 
	 */
	private void addCaves() {
		cavernFloorBlocks = new ArrayList<Integer[]>();
		int totalCaves = (int) (caveFrequency * totalWidth);

		for (int i = 0; i < totalCaves; i++) {
			double a = 10 * (random.nextDouble() + 1);
			double b = 3 * (random.nextDouble() + 1);

			int originX = random.nextInt(totalWidth);
			int originY = random.nextInt(stoneDepth / 2) + airHeight
					+ dirtDepth + stoneDepth / 2;

			for (double t = 0.0; t <= Math.PI; t += 0.01) {
				int x = originX + (int) (a * Math.cos(t));
				int y1 = originY
						+ (int) (b * Math.sin(t) + 4 * random.nextDouble());
				int y2 = originY
						- (int) (b * Math.sin(t) + 4 * random.nextDouble());

				x = Math.floorMod(x, totalWidth);

				if (y1 < totalHeight) {
					if (!map[y1 + 1][x].getID().equals(LavaBlock.id))
						cavernFloorBlocks.add(new Integer[] { y1 + 1, x });
				}

				for (int y = originY; y < y1; y++) {
					if (!map[y][x].getID().equals(LavaBlock.id)) {
						map[y][x] = new CavernBlock();
					}
				}

				for (int y = originY; y > y2; y--) {
					if (!map[y][x].getID().equals(LavaBlock.id)) {
						map[y][x] = new CavernBlock();
					}
				}
			}

		}

	}

	private void addMushrooms() {
		for (int i = 0; i < 10; i++) {
			Integer[] cavernFloorBlock = cavernFloorBlocks.get(random
					.nextInt(cavernFloorBlocks.size()));
			Mushroom mushroom = new Mushroom(totalWidth, map, random,
					cavernFloorBlock);
			mushroom.addToMap();
		}
	}

}