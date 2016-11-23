/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import model.Map;
import model.Actors.Actor;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;
import model.Items.AntLarvaItem;

/**
 * @author Jonathon Davis
 *
 */
public class HungerTest {

	public Map generateMap(int[][] map) {
		BuildingBlock[][] mapTypes = new BuildingBlock[map.length][map[0].length];
		for (int i = 0; i < mapTypes.length; i++) {
			for (int j = 0; j < mapTypes[i].length; j++) {
				if (map[i][j] == 0)
					mapTypes[i][j] = new AirBlock();
				else if (map[i][j] == 2)
					mapTypes[i][j] = new IronOreBlock();
				else
					mapTypes[i][j] = new EarthBlock();
			}
		}
		return new Map(mapTypes);
	}

	@Test
	public void testEat() {
		int[][] mapGen = new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 2 },
				{ 0, 1, 1, 1, 1 }, { 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1 } };
		Map map = generateMap(mapGen);
		HashMap<Actor, Position> actors = new HashMap<>();
		PlayerControlledActor test = new PlayerControlledActor(10, 0,
				new Position(1, 1), map, actors);
		test.getInventory().addItem(new AntLarvaItem());
		assertTrue(test.isAlive());
		for (int i = 0; i < 1000; i++) {
			test.update();
		}
		assertTrue(test.isAlive());
		for (int i = 0; i < 500; i++) {
			test.update();
		}
		assertFalse(test.isAlive());
	}

	@Test
	public void testHunger() {
		int[][] mapGen = new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 2 },
				{ 0, 1, 1, 1, 1 }, { 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1 } };
		Map map = generateMap(mapGen);
		HashMap<Actor, Position> actors = new HashMap<>();
		PlayerControlledActor test = new PlayerControlledActor(10, 0,
				new Position(1, 1), map, actors);
		assertTrue(test.isAlive());
		for (int i = 0; i < 1500; i++) {
			test.update();
		}
		assertFalse(test.isAlive());
	}

}
