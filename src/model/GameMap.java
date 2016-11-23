/**
 * 
 */
package model;

import model.Actors.Position;
import model.BuildingBlocks.BuildingBlock;

/**
 * Singleton used for the game map
 * @author Jonathon Davis
 *
 */
public final class GameMap {
	public static Map map;

	public static int mapHeight(){
		return map.getTotalHeight();
	}
	
	public static int mapWidth(){
		return map.getTotalWidth();
	}

	public static BuildingBlock getBlock(Position position) {
		return map.getBuildingBlock(position);
	}
	
	public static BuildingBlock getBlock(int row, int col) {
		return map.getBuildingBlock(new Position(row,col));
	}
	
	
	public static void setBuildingBlock(Position position, BuildingBlock newBlock){
		map.setBuildingBlock(position, newBlock);
	}
	
}
