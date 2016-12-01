package model.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import controller.Designation;
import model.Actors.Actor;
import model.Actors.IncubateAction;
import model.Actors.EnemyActor;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.AnthillBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.CavernBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.GoldOreBlock;
import model.BuildingBlocks.GrassBlock;
import model.BuildingBlocks.IronOreBlock;
import model.BuildingBlocks.LavaBlock;
import model.BuildingBlocks.StoneBlock;
import model.Furniture.Furniture;
import model.Items.Item;

/**
 * Constructs a random map with various geographical features
 * 
 * @author Ethan Ward
 *
 */
public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8180013355509112056L;

	private Random random;

	private HashMap<Position, AppleTree> trees;
	private ArrayList<Integer[]> cavernFloorBlocks;

	private BuildingBlock[][] map;
	private MapParameters mapParameters;

	private HashMap<Furniture, Position> hardCodedFurniture;
	private HashMap<Position, Designation> blocksMarkedAsDesignated;

	private ArrayList<Position> itemsOnGround = new ArrayList<>();
	// using anthillLocations to determine "random" spawn points for ants
	private ArrayList<Position> anthillLocations = new ArrayList<>();

	private int time;
	
	private boolean addActor;
	private Position positionToAdd;

	public Map(MapParameters mapParameters, Random random) {
		this.blocksMarkedAsDesignated = new HashMap<>();
		this.random = random;
		this.mapParameters = mapParameters;
		this.hardCodedFurniture = new HashMap<>();

		constructMap();
	}

	public Map(BuildingBlock[][] mapTypes) {
		this.mapParameters = new MapParameters(mapTypes[0].length,
				mapTypes.length, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.hardCodedFurniture = new HashMap<>();
		this.blocksMarkedAsDesignated = new HashMap<>();
		this.map = mapTypes;
	}

	public BuildingBlock getBuildingBlock(Position position) {
		return map[position.getRow()][position.getCol()];
	}

	public BuildingBlock getBuildingBlock(int row, int col) {
		return map[row][col];
	}

	public void setBuildingBlock(Position position, BuildingBlock newBlock) {
		map[position.getRow()][position.getCol()] = newBlock;
	}

	/**
	 * @return the map height
	 */
	public int getTotalHeight() {
		return mapParameters.mapHeight;
	}

	/**
	 * @return the map width
	 */
	public int getTotalWidth() {
		return mapParameters.mapWidth;
	}

	private void constructMap() {
		map = new BuildingBlock[getTotalHeight()][getTotalWidth()];

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
		addPlayerActors();
		addEnemyActors();
		addFurniture();

	}

	private void addAir() {
		for (int i = 0; i < mapParameters.airHeight; i++) {
			for (int j = 0; j < getTotalWidth(); j++) {
				map[i][j] = new AirBlock();
			}
		}
	}

	private void addDirt() {
		int randomEdge = random.nextInt(10);
		for (int j = 0; j < getTotalWidth(); j++) {
			for (int i = mapParameters.airHeight; i < (mapParameters.airHeight + mapParameters.dirtDepth); i++) {
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
			for (int i = (mapParameters.airHeight + mapParameters.dirtDepth); i < (mapParameters.airHeight
					+ mapParameters.dirtDepth + randomEdge); i++) {
				map[i][j] = new EarthBlock();
			}
		}
	}

	private void addStone() {
		for (int i = (mapParameters.airHeight + mapParameters.dirtDepth); i < (mapParameters.airHeight
				+ mapParameters.dirtDepth + mapParameters.stoneDepth); i++) {
			for (int j = 0; j < getTotalWidth(); j++) {
				map[i][j] = new StoneBlock();
			}
		}
	}

	private void addLava() {
		for (int i = (mapParameters.airHeight + mapParameters.dirtDepth + mapParameters.stoneDepth); i < (mapParameters.airHeight
				+ mapParameters.dirtDepth + mapParameters.stoneDepth + mapParameters.lavaDepth); i++) {
			for (int j = 0; j < getTotalWidth(); j++) {
				map[i][j] = new LavaBlock();
			}
		}
	}

	private void addIron() {
		int totalIronClusters = (int) (mapParameters.ironFrequency
				* mapParameters.stoneDepth * getTotalWidth());

		int first_third = totalIronClusters / 10;
		int second_third = totalIronClusters / 40;
		int third_third = totalIronClusters - first_third - second_third;

		for (int i = 0; i < second_third; i++) {
			int startX = random.nextInt(getTotalWidth());
			int startY = random.nextInt(mapParameters.stoneDepth / 3)
					+ mapParameters.dirtDepth + mapParameters.airHeight
					+ mapParameters.stoneDepth / 3;
			if (map[startY][startX].getID().equals(StoneBlock.id)) {
				map[startY][startX] = new IronOreBlock();
			}
		}
		for (int i = 0; i < third_third; i++) {
			int startX = random.nextInt(getTotalWidth());
			int startY = random.nextInt(mapParameters.stoneDepth / 3)
					+ mapParameters.dirtDepth + mapParameters.airHeight + 2
					* mapParameters.stoneDepth / 3;
			if (map[startY][startX].getID().equals(StoneBlock.id)) {
				map[startY][startX] = new IronOreBlock();
			}
		}
	}

	private void addGold() {
		int totalGoldClusters = (int) (mapParameters.goldFrequency
				* mapParameters.stoneDepth * getTotalWidth());
		for (int i = 0; i < totalGoldClusters; i++) {
			int startX = random.nextInt(getTotalWidth());
			int startY = random.nextInt(mapParameters.stoneDepth / 5)
					+ mapParameters.dirtDepth + mapParameters.airHeight + 4
					* mapParameters.stoneDepth / 5;
			if (map[startY][startX].getID().equals(StoneBlock.id)) {
				map[startY][startX] = new GoldOreBlock();
			}
		}
	}

	private void addMountains() {
		int totalMountains = (int) (mapParameters.mountainFrequency * getTotalWidth());

		for (int i = 0; i < totalMountains; i++) {
			int X = random.nextInt(getTotalWidth());
			int widthEstimate = random.nextInt(100);
			int Y = mapParameters.airHeight;

			// up
			for (int j = 0; j < widthEstimate / 2; j++) {
				map[Y][X] = new EarthBlock();
				for (int k = Y; k < mapParameters.airHeight; k++) {
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
				if (Y >= mapParameters.airHeight)
					Y--;
				X %= getTotalWidth();
			}

			// down
			while (true) {
				map[Y][X] = new EarthBlock();
				for (int k = Y; k < mapParameters.airHeight; k++) {
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
				if (Y >= mapParameters.airHeight)
					break;
				X %= getTotalWidth();
			}
		}
	}

	private void addTrees() {
		trees = new HashMap<>();

		int totalTrees = (int) (mapParameters.treeFrequency * getTotalWidth());

		for (int i = 0; i < totalTrees; i++) {
			AppleTree tree = new AppleTree(getTotalWidth(),
					mapParameters.airHeight, map, random);
			tree.addToMap();

			for (Position pos : tree.getTrunk()) {
				trees.put(pos, tree);
			}
		}
	}

	public HashMap<Position, AppleTree> getTrees() {
		return trees;
	}

	private void addBushes() {
		int totalBushes = (int) (mapParameters.grassFrequency * getTotalWidth());

		for (int i = 0; i < totalBushes; i++) {
			int bushX = random.nextInt(getTotalWidth());

			// move up until we are no longer underground
			int bushY = mapParameters.airHeight;
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
		for (int i = 0; i < mapParameters.numberOfAntColonies; i++) {
			int startX = random.nextInt(getTotalWidth());
			int height = random.nextInt(2) + 5;

			int startY = mapParameters.airHeight;
			// move up until we are no longer underground
			while (map[startY][startX].getID().equals(EarthBlock.id)
					|| map[startY][startX].getID().equals(AnthillBlock.id)) {
				startY -= 1;
				if (startY < 0)
					break;
			}
			startY -= height;
			if (startY < 0) {
				startY = 0;
			}

			for (int j = startY; j < mapParameters.airHeight; j++) {
				int width = 2 * (j - startY);
				for (int k = (-width + 1); k < width; k++) {
					int row = j;
					int col = Math.floorMod(startX + k, getTotalWidth());
					if (k != 0) {

						if (map[row][col].getID().equals(AirBlock.id)) {
							map[row][col] = new AnthillBlock();
							// TODO does this work?
							anthillLocations.add(new Position(row, col));
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
		int tunnelY = mapParameters.airHeight - 1;
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
				newTunnelX = Math.floorMod(newTunnelX, getTotalWidth());
				if (newTunnelY > 0
						&& newTunnelY >= mapParameters.airHeight
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
				newTunnelX = Math.floorMod(newTunnelX, getTotalWidth());
				if (newTunnelY > 0
						&& newTunnelY >= mapParameters.airHeight
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
		int totalCaves = (int) (mapParameters.caveFrequency * getTotalWidth());

		for (int i = 0; i < totalCaves; i++) {

			// ellipse parameters
			double a = 10 * (random.nextDouble() + 1);
			double b = 3 * (random.nextDouble() + 1);

			int originX = random.nextInt(getTotalWidth());
			int originY = random.nextInt(mapParameters.stoneDepth / 2)
					+ mapParameters.airHeight + mapParameters.dirtDepth
					+ mapParameters.stoneDepth / 2;

			for (double t = 0.0; t <= Math.PI; t += 0.01) {
				int x = originX + (int) (a * Math.cos(t));
				int y1 = originY
						+ (int) (b * Math.sin(t) + 4 * random.nextDouble());
				int y2 = originY
						- (int) (b * Math.sin(t) + 4 * random.nextDouble());

				x = Math.floorMod(x, getTotalWidth());

				if (y1 < getTotalHeight()) {
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
			Mushroom mushroom = new Mushroom(getTotalWidth(), map, random,
					cavernFloorBlock);
			mushroom.addToMap();
		}
	}

	private int[] getRandomOccupiablePosition(int centerX, int range) {
		int x = random.nextInt(range) - range / 2;
		int y = mapParameters.airHeight;
		x = Math.floorMod(x, getTotalWidth());

		while (!map[y][x].getID().equals(AirBlock.id)) {
			y -= 1;
			if (y < 0)
				return new int[] { -1, -1 };
		}
		if (y >= 0 && map[y][x].isOccupiable()
				&& map[y + 1][x].getID().equals(EarthBlock.id)) {
			return new int[] { y, x };
		}
		return new int[] { -1, -1 };
	}

	private Position randomStartingPosition() {
		int x = random.nextInt(40) - 20;
		x = Math.floorMod(x, mapParameters.mapWidth);
		int y = mapParameters.airHeight + 1;
		while (!map[y][x].isOccupiable()) {
			y -= 1;
		}
		return new Position(y, x);
	}

	private void addPlayerActors() {
		for (int i = 0; i < mapParameters.numberOfStartingActors; i++) {
			new PlayerControlledActor(100, randomStartingPosition());
		}

		if (PlayerControlledActor.allActors != null) {
			for (Actor actor : PlayerControlledActor.allActors) {
				Position position = actor.getPosition();
				map[position.getRow()][position.getCol()].addActor(actor);
			}
		}
	}

	public void addPlayerActor(Position p) {
		addActor = true;
		positionToAdd = p;
		/*PlayerControlledActor pca = new PlayerControlledActor(100, p);

		// Set new actor to "dead" since not born yet
		pca.setAlive(false, false);

		// Place 1000 Incubate Actions into actor's queue so that
		// Actor can't do anything until "born"
		
		//TODO: Change this back to larger value
		for (int i = 0; i < 1000; i++) {
			pca.addToActionQueue(new IncubateAction(i));
		}*/
	}

	public void addPlayerToMap(Actor a) {
		// Make new player visible on map
		//TODO: May need to move this code to update actor
		Position p = a.getPosition();
		map[p.getRow()][p.getCol()].addActor(a);
	}

	private void addEnemyActors() {
		if (anthillLocations.size() != 0) {
			for (int i = 0; i < mapParameters.numberOfStartingActors; i++) {
				new EnemyActor(100, anthillLocations.get(random
						.nextInt(anthillLocations.size())));
			}
		}

		if (EnemyActor.allActors != null) {
			for (Actor actor : EnemyActor.allActors) {
				Position position = actor.getPosition();
				map[position.getRow()][position.getCol()].addActor(actor);
			}
		}
	}

	public void updateActors(int timeDelta) {
		if (Actor.allActors != null) {
			for (Actor actor : Actor.allActors) {
				Position oldPosition = actor.getPosition();
				map[oldPosition.getRow()%getTotalHeight()][oldPosition.getCol()%getTotalWidth()].removeActor(actor);
				actor.update();
				Position newPosition = actor.getPosition();
				map[newPosition.getRow()%getTotalHeight()][newPosition.getCol()%getTotalWidth()].addActor(actor);
			}
			//TODO: Every 1000 ticks, call breed action or construct incubation room if none built
		}
	}

	private void addFurniture() {
		if (hardCodedFurniture != null) {
			for (Furniture furniture : hardCodedFurniture.keySet()) {
				Position position = hardCodedFurniture.get(furniture);
				map[position.getRow()][position.getCol()]
						.addFurniture(furniture);
			}
		}
	}

	public HashMap<Furniture, Position> getFurniture() {
		return hardCodedFurniture;
	}

	public void addDesignation(Position position, Designation designation) {
		map[position.getRow()][position.getCol()].addDesignation(designation);
		blocksMarkedAsDesignated.put(position, designation);
	}

	public HashMap<Position, Designation> getDesignatedBlocks() {
		return blocksMarkedAsDesignated;
	}

	public void undesignateBlock(Position position) {
		map[position.getRow()][position.getCol()].removeDesignation();
		Iterator<HashMap.Entry<Position, Designation>> iter = blocksMarkedAsDesignated
				.entrySet().iterator();
		while (iter.hasNext()) {
			HashMap.Entry<Position, Designation> entry = iter.next();
			if (position.equals(entry.getValue())) {
				iter.remove();
			}
		}
	}

	public boolean addItemToGround(Position position, Item item) {
		if (getBuildingBlock(position).addItemToGround(item)) {
			itemsOnGround.add(position);
			return true;
		}
		return false;
	}

	public boolean removeItemFromGround(Position position, Item item) {
		if (getBuildingBlock(position).removeItemFromGround(item)) {
			itemsOnGround.remove(0);
			return true;
		}
		return false;
	}

	public void addFurniture(Furniture f, Position p) {
		hardCodedFurniture.put(f, p);
		map[p.getRow()][p.getCol()].addFurniture(f);
	}

	public ArrayList<Position> getItemsOnGround() {
		return itemsOnGround;
	}

	public AppleTree getTree(Position position) {
		for (Position basePosition : trees.keySet()) {
			AppleTree tree = trees.get(basePosition);
			for (Position testPosition : tree.getTrunk()) {
				if (testPosition.equals(position)) {
					return tree;
				}
			}
		}
		return null;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}

}
