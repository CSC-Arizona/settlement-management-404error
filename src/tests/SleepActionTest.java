/**
 * 
 */
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Actors.Actor;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.Furniture.Bed;
import model.Game.Game;
import model.Map.Map;

/**
 * @author Jonathon Davis
 *
 */
public class SleepActionTest {
	
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
	public void testMoveAction() {
		int[][] mapGen = new int[][] { { 1, 0, 0, 0, 0 }, 
										{ 1, 0, 0, 0, 0 }, 
										{ 1, 1, 1, 1, 1 }, 
										{ 1, 1, 1, 1, 1 },
										{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		Game.getMap().addFurniture(new Bed(), new Position(1,1));
		PlayerControlledActor.allActors = null;
		Actor.allActors = null;
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1, 4));
		test.setFatigue(990);
		
		for(int i = 0; i < 9; i++){
			test.update();
			assertTrue(test.getPosition().equals(new Position(1, 4)));
		}
		
		test.update();
		assertTrue(test.getPosition().equals(new Position(1, 3)));
		
		test.update();
		assertTrue(test.getPosition().equals(new Position(1, 2)));
		
		test.update();
		assertTrue(test.getPosition().equals(new Position(1, 1)));


	}
}
