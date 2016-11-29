/**
 * 
 */
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.Game.Game;
import model.Items.AntLarvaItem;
import model.Map.Map;

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
				else
					mapTypes[i][j] = new EarthBlock();
			}
		}
		return new Map(mapTypes);
	}
	
	@Test
	public void testEat() {
		Game.reset();
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		Game.setMap(generateMap(mapGen));
		
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1,1));
		test.setFatigue(-150000);
		test.getInventory().addItem(new AntLarvaItem());
		assertTrue(test.isAlive());
		for (int i = 0; i < 1000; i++) {
			test.update();
		}
		assertTrue(test.isAlive());
		for (int i = 0; i < 501; i++) {
			test.update();
		}
		//assertFalse(test.isAlive());
	}

	@Test
	public void testHunger() {
		Game.reset();
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		Game.setMap(generateMap(mapGen));
		
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1,1));
		test.setFatigue(-150000);
		assertTrue(test.isAlive());
		for (int i = 0; i < 1000; i++) {
			test.update();
		}
		//assertFalse(test.isAlive());
	}

}
