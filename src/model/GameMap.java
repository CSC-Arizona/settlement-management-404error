/**
 * 
 */
package model;

import java.awt.Dimension;

import model.BuildingBlocks.BuildingBlock;

/**
 * Singleton used for the game map
 * @author Jonathon Davis
 *
 */
public class GameMap {
	public static Map map;

	public static int mapHeight(){
		return map.getTotalHeight();
	}
	
	public static int mapWidth(){
		return map.getTotalWidth();
	}

	public static BuildingBlock getBlock(int row, int col) {
		return map.getBuildingBlock(row, col);
	}
	
}
