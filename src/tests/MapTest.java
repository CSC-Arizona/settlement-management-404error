package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.GameMap;
import model.Map;

/**
 * Tests the map and game map classes
 * @author Jonathon Davis
 *
 */
public class MapTest {
	
	@Test
	public void testMap() {
		int mapHeight = 150;
		int mapWidth = 1000;
		int mapDirtDepth = 30;
		int mapStoneDepth = 50;

		Map map = new Map(mapHeight, mapWidth, mapDirtDepth, mapStoneDepth,
				(int) (Math.random() * 10000));
		assertEquals(mapHeight, map.getTotalHeight());
		assertEquals(mapWidth, map.getTotalWidth());
	}
	
	@Test
	public void testGameMap(){
		int mapHeight = 150;
		int mapWidth = 1000;
		int mapDirtDepth = 30;
		int mapStoneDepth = 50;

		Map map = new Map(mapHeight, mapWidth, mapDirtDepth, mapStoneDepth,
				(int) (Math.random() * 10000));
		assertEquals(mapHeight, map.getTotalHeight());
		assertEquals(mapWidth, map.getTotalWidth());
		
		GameMap.map = map;
		assertEquals(mapHeight, GameMap.map.getTotalHeight());
		assertEquals(mapWidth, GameMap.map.getTotalWidth());
	}

}
