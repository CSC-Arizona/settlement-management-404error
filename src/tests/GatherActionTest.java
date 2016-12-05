/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.actors.GatherAction;
import model.actors.MoveAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.IronOreBlock;
import model.game.Game;
import model.map.Map;

/**
 * @author Jonathon Davis
 *
 */
public class GatherActionTest {

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
	public void testMoveAndGatherAction() {
		Game.reset();
		int[][] mapGen = new int[][] { { 1, 0, 0, 0, 0 }, 
										{ 1, 0, 0, 0, 2 }, 
										{ 1, 1, 1, 1, 1 }, 
										{ 1, 1, 1, 1, 1 },
										{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor test = new PlayerControlledActor(new Position(1, 1));
		test.addToActionQueue(new GatherAction(new Position(1, 4)));

		assertEquals(1, test.getPosition().getRow());
		assertEquals(1, test.getPosition().getCol());

		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(2, test.getPosition().getCol());

		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(3, test.getPosition().getCol());

		IronOreBlock testBlock = new IronOreBlock();
		int durability = testBlock.getDurability();
		for (int i = 0; i < durability; i++) {
			test.update();
			assertEquals(1, test.getPosition().getRow());
			assertEquals(3, test.getPosition().getCol());
		}

		int amount = test.getInventory().size();

		assertEquals(amount, testBlock.lootBlock().size());
		assertEquals("Cavern", Game.getMap().getBuildingBlock(1, 4).getID());
	}

	@Test
	public void testDelay() {
		Game.reset();
		int[][] mapGen = new int[][] { { 0, 0, 0, 1, 1 }, 
										{ 0, 0, 0, 1, 2 }, 
										{ 0, 1, 1, 1, 1 }, 
										{ 1, 0, 0, 1, 1 },
										{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor test = new PlayerControlledActor(new Position(1, 1));
		test.addToActionQueue(new GatherAction(new Position(1, 4)));
		test.addToActionQueue(new MoveAction(new Position(3, 2)));

		assertEquals(1, test.getPosition().getRow());
		assertEquals(1, test.getPosition().getCol());

		test.update();
		assertEquals(2, test.getPosition().getRow());
		assertEquals(0, test.getPosition().getCol());

		test.update();
		assertEquals(3, test.getPosition().getRow());
		assertEquals(1, test.getPosition().getCol());

		test.update();
		assertEquals(3, test.getPosition().getRow());
		assertEquals(2, test.getPosition().getCol());

		test.update();
		assertEquals(3, test.getPosition().getRow());
		assertEquals(2, test.getPosition().getCol());
	}

}
