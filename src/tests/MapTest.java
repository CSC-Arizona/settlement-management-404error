package tests;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import model.game.Game;
import model.map.Map;
import model.map.MapParameters;

/**
 * Tests the map and game map classes
 * 
 * @author Jonathon Davis
 *
 */
public class MapTest {

	@Test
	public void testMap() {
		Game.reset();
		Map map = new Map(MapParameters.getDefaultParameters(), new Random());
		assertEquals(MapParameters.getDefaultParameters().mapHeight, map.getTotalHeight());
		assertEquals(MapParameters.getDefaultParameters().mapWidth, map.getTotalWidth());
	}

	@Test
	public void testGameMap() {
		Game.reset();
		Map map = new Map(MapParameters.getDefaultParameters(), new Random());
		assertEquals(MapParameters.getDefaultParameters().mapHeight, map.getTotalHeight());
		assertEquals(MapParameters.getDefaultParameters().mapWidth, map.getTotalWidth());

		assertEquals(MapParameters.getDefaultParameters().mapHeight, map.getTotalHeight());
		assertEquals(MapParameters.getDefaultParameters().mapWidth, map.getTotalWidth());
	}

}
