/**
 * 
 */
package model;

/**
 * Singleton used for the game map
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
	 * @param map the map to set
	 */
	public static void setMap(Map map) {
		Game.map = map;
	}
	
}