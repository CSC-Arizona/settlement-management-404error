package model.map;

import java.io.Serializable;

import model.actors.EnemyActor;
import model.game.Settings;

public class MapParameters implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4087298872483722924L;
	// total height / width of the map
	public final int mapHeight;
	public final int mapWidth;

	// basic layers: air, dirt, stone, lava
	public final int airHeight;
	public final int dirtDepth;
	public final int stoneDepth;
	public final int lavaDepth;

	// resource frequencies
	public final double ironFrequency;
	public final double goldFrequency;
	public final double treeFrequency;
	public final double grassFrequency;

	// geographic features frequencies
	public final double mountainFrequency;
	public final double caveFrequency;
	public final int numberOfAntColonies;
	public final double grassBlockFrequency;
	public final int numberOfEnemyActors;

	public final int numberOfStartingActors;

	public MapParameters(int mapWidth, int airHeight, int dirtDepth,
			int stoneDepth, int lavaDepth, double ironFrequency,
			double goldFrequency, double treeFrequency, double grassFrequency,
			double mountainFrequency, double caveFrequency,
			int numberOfAntColonies, int numberOfEnemyActors, int numberOfStartingActors, 
			double grassBlockFrequency) {

		this.mapHeight = airHeight + dirtDepth + stoneDepth + lavaDepth;
		this.mapWidth = mapWidth;
		this.airHeight = airHeight;
		this.dirtDepth = dirtDepth;
		this.stoneDepth = stoneDepth;
		this.lavaDepth = lavaDepth;
		this.ironFrequency = ironFrequency;
		this.goldFrequency = goldFrequency;
		this.treeFrequency = treeFrequency;
		this.grassFrequency = grassFrequency;
		this.mountainFrequency = mountainFrequency;
		this.caveFrequency = caveFrequency;
		this.numberOfAntColonies = numberOfAntColonies;
		this.grassBlockFrequency = grassBlockFrequency;
		this.numberOfStartingActors = numberOfStartingActors;
		this.numberOfEnemyActors = numberOfEnemyActors;

	}

	public static MapParameters getCustumMapParameters(Settings settings){
		int size = 0;
		if (settings.getSize() == Settings.SMALL)
			size = 200;
		else if (settings.getSize() == Settings.NORMAL)
			size = 500;
		else if (settings.getSize() == Settings.LARGE)
			size = 1000;
		else if (settings.getSize() == Settings.EXTRA_LARGE)
			size = 2000;
		else
			throw new IllegalArgumentException();
		
		int numberOfAntColonies = 0, numberOfEnemyActors = 0, numberOfPlayerActors = 0;
		
		if(settings.getDifficulty() == Settings.EASY){
			numberOfAntColonies = 1;
			numberOfEnemyActors = 4;
			numberOfPlayerActors = 7;
			EnemyActor.timeTillAttack = Integer.MAX_VALUE;
		} else if(settings.getDifficulty() == Settings.NORMAL){
			numberOfAntColonies = 2;
			numberOfEnemyActors = 7;
			numberOfPlayerActors = 7;
			EnemyActor.timeTillAttack = 15000;
		} else if(settings.getDifficulty() == Settings.HARD){
			numberOfAntColonies = 2;
			numberOfEnemyActors = 10;
			numberOfPlayerActors = 7;
			EnemyActor.timeTillAttack = 7000;
		} else if(settings.getDifficulty() == Settings.IMPOSSIBLE){
			numberOfAntColonies = 2;
			numberOfEnemyActors = 15;
			numberOfPlayerActors = 7;
			EnemyActor.timeTillAttack = 1000;
		}
		return new MapParameters(size, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.1, 0.005, numberOfAntColonies, numberOfEnemyActors, numberOfPlayerActors, 0.05);
	}
	
	public static MapParameters getParametersWithNoMountains() {
		return new MapParameters(1000, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.0, 0.005, 0, 7, 7, 0.05);
	}

	public static MapParameters getDefaultParameters() {
		return new MapParameters(1000, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.1, 0.005, 2, 7, 7, 0.05);
	}


}
