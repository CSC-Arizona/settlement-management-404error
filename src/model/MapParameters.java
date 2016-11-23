package model;

public class MapParameters {

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

	public MapParameters(int mapWidth, int airHeight, int dirtDepth,
			int stoneDepth, int lavaDepth, double ironFrequency,
			double goldFrequency, double treeFrequency, double grassFrequency,
			double mountainFrequency, double caveFrequency,
			int numberOfAntColonies) {

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

	}

	public static MapParameters getDefaultParameters() {
		return new MapParameters(1000, 70, 20, 30, 10, 0.01, 0.001, 0.1, 0.1,
				0.1, 0.005, 2);
	}

}