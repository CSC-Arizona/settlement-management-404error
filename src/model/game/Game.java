/**
 * 
 */
package model.game;

import model.actors.Actor;
import model.map.Map;

/**
 * Singleton used for the game map
 * 
 * @author Jonathon Davis
 *
 */
public final class Game {
	private static Map map;
	
	/**
	 * @return the map
	 */
	public static Map getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public static void setMap(Map map) {
		Game.map = map;
	}

	/**
	 * Resets the Game
	 */
	public static void reset() {
		Actor.reset();
		Game.map = null;
	}

	/**
	 * Returns whether or not this is a valid move location
	 * 
	 * @param row
	 *            the row to move
	 * @param col
	 *            the col to move
	 * @return whether or not this is a valid move location
	 */
	public static boolean validActorLocation(int row, int col) {
		int row1 = row, col1 = (col > 0) ? col % (Game.getMap().getTotalWidth()) : Game.getMap().getTotalWidth() + col;
		if (col1 == Game.getMap().getTotalWidth())
			col1 = 0;
		return row >= 0 && row1 + 1 < map.getTotalHeight() && map.getBuildingBlock(row1, col1).isOccupiable()
				&& row < Game.getMap().getTotalHeight() &&
				(!map.getBuildingBlock(row1 + 1, col1).isOccupiable()
						|| (map.getBuildingBlock(row1, col1).getFurniture() != null
								&& (map.getBuildingBlock(row1, col1).getFurniture().getID().equals("ladder"))) ||
										map.getBuildingBlock(row1, col1).getID().equals("Ant tunnel"));
	}

}
