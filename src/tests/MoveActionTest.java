/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.actors.MoveAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.game.Game;
import model.map.Map;

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
		Game.reset();
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
		Game.reset();
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
