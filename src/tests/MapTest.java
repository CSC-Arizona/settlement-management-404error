package tests;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import controller.ControllerMain;
import model.GameMap;
import model.Map;
import model.MapParameters;

/**
 * Tests the map and game map classes
 * 
 * @author Jonathon Davis
 *
 */
public class MapTest {

	@Test
	public void testMap() {

		Map map = new Map(MapParameters.getDefaultParameters(), null, null,
				new Random(), null);
		assertEquals(MapParameters.getDefaultParameters().mapHeight,
				map.getTotalHeight());
		assertEquals(MapParameters.getDefaultParameters().mapWidth,
				map.getTotalWidth());
	}

	@Test
	public void testGameMap() {

		Map map = new Map(MapParameters.getDefaultParameters(), null, null,
				new Random(), null);
		assertEquals(MapParameters.getDefaultParameters().mapHeight,
				map.getTotalHeight());
		assertEquals(MapParameters.getDefaultParameters().mapWidth,
				map.getTotalWidth());

		GameMap.map = map;
		assertEquals(MapParameters.getDefaultParameters().mapHeight,
				GameMap.map.getTotalHeight());
		assertEquals(MapParameters.getDefaultParameters().mapWidth,
				GameMap.map.getTotalWidth());
	}

}
