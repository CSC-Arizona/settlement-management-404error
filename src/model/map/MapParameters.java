package model.map;

import java.io.Serializable;

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

	public final int numberOfStartingActors;

	public MapParameters(int mapWidth, int airHeight, int dirtDepth,
			int stoneDepth, int lavaDepth, double ironFrequency,
			double goldFrequency, double treeFrequency, double grassFrequency,
			double mountainFrequency, double caveFrequency,
			int numberOfAntColonies, int numberOfStartingActors,
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

	}

	public static MapParameters getSmallMapParameters() {
		return new MapParameters(100, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.1, 0.005, 2, 7, 0.05);
	}

	public static MapParameters getParametersWithNoMountains() {
		return new MapParameters(1000, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.0, 0.005, 0, 7, 0.05);
	}

	public static MapParameters getDefaultParameters() {
		return new MapParameters(1000, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.1, 0.005, 2, 7, 0.05);
	}

	public static MapParameters getCutsceneParameters() {
		return new MapParameters(50, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	//Same as default parameters, but only contains three actors
	public static MapParameters getThreeActorParameters() {
		return new MapParameters(1000, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.1, 0.005, 2, 3, 0.05);
	}

}
