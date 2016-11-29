/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Actors.MoveAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.Game.Game;
import model.Map.Map;

/**
 * @author Jonathon Davis
 *
 */
public class MoveActionTest {

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
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1, 1));
		test.addToActionQueue(new MoveAction(new Position(2, 4)));

		assertEquals(1, test.getPosition().getRow());
		assertEquals(1, test.getPosition().getCol());

		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(0, test.getPosition().getCol());

		test.update();
		assertEquals(2, test.getPosition().getRow());
		assertEquals(4, test.getPosition().getCol());
	}

	@Test
	public void testNonMoveAction() {
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 } };
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1, 1));
		test.addToActionQueue(new MoveAction(new Position(2, 4)));

		assertEquals(1, test.getPosition().getRow());
		assertEquals(1, test.getPosition().getCol());

		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(1, test.getPosition().getCol());
	}

}
