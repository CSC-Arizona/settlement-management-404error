/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Game;
import model.Map;
import model.Actors.GatherAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.GrassBlock;
import model.BuildingBlocks.IronOreBlock;
import model.Furniture.ReinforcedCrate;

/**
 * @author Jonathon Davis
 *
 */
public class PickUpAndStoreActionTest {
	
	public Map generateMap(int[][] map) {
		BuildingBlock[][] mapTypes = new BuildingBlock[map.length][map[0].length];
		for (int i = 0; i < mapTypes.length; i++) {
			for (int j = 0; j < mapTypes[i].length; j++) {
				if (map[i][j] == 0)
					mapTypes[i][j] = new AirBlock();
				else if (map[i][j] == 2)
					mapTypes[i][j] = new GrassBlock();
				else
					mapTypes[i][j] = new EarthBlock();
			}
		}
		return new Map(mapTypes);
	}

	@Test
	public void testMoveAndGatherAction() {
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, 
										{ 0, 0, 2, 2, 2 }, 
										{ 1, 1, 1, 1, 1 }, 
										{ 1, 1, 1, 1, 1 },
										{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		Game.getMap().addFurniture(new ReinforcedCrate(), new Position(1, 0));
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1, 1));
		test.addToActionQueue(new GatherAction(new Position(1, 2)));
		test.addToActionQueue(new GatherAction(new Position(1, 3)));
		test.addToActionQueue(new GatherAction(new Position(1, 4)));


		GrassBlock testBlock = new GrassBlock();
		int durability = testBlock.getDurability();
		for (int i = 0; i < durability; i++) {
			test.update();
			assertEquals(1, test.getPosition().getRow());
			assertEquals(1, test.getPosition().getCol());
		}

		int amount = test.getInventory().size();
		assertEquals(amount, testBlock.lootBlock().size());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(2, test.getPosition().getCol());
		
		for (int i = 0; i < durability; i++) {
			test.update();
			assertEquals(1, test.getPosition().getRow());
			assertEquals(2, test.getPosition().getCol());
		}
		assertEquals(amount, testBlock.lootBlock().size());
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(3, test.getPosition().getCol());
		
		for (int i = 0; i < durability; i++) {
			test.update();
			assertEquals(1, test.getPosition().getRow());
			assertEquals(3, test.getPosition().getCol());
		}
		assertEquals(amount, testBlock.lootBlock().size());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(4, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(0, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(0, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(4, test.getPosition().getCol());
		assertEquals(0, test.getInventory().size());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(3, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(3, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(4, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(0, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(0, test.getPosition().getCol());
		
		test.update();
		assertEquals(1, test.getPosition().getRow());
		assertEquals(4, test.getPosition().getCol());
		
	}

}
