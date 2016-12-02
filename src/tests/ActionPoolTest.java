/**
 * 
 */
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.actors.GatherAction;
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
public class ActionPoolTest {

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
	public void testPool() {
		Game.reset();
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 0, 0 }, 
										{ 2, 2, 2, 2, 2 }, 
										{ 1, 1, 1, 1, 1 },
										{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1, 1));
		PlayerControlledActor test2 = new PlayerControlledActor(10, new Position(1, 3));
		test.addActionToPool(new GatherAction(new Position(2, 0)));
		test.addActionToPool(new GatherAction(new Position(2, 4)));
		test.addActionToPool(new GatherAction(new Position(2, 1)));
		test2.addActionToPool(new GatherAction(new Position(2, 3)));
		test2.addActionToPool(new GatherAction(new Position(2, 2)));

		int durability = Game.getMap().getBuildingBlock(2, 2).getDurability();
		for (int i = 0; i < durability; i++) {
			assertTrue(test.getPosition().equals(new Position(1, 1)));
			assertTrue(test2.getPosition().equals(new Position(1, 3)));
			assertTrue(Game.getMap().getBuildingBlock(2, 0).getID().equals("Iron ore"));
			assertTrue(Game.getMap().getBuildingBlock(2, 4).getID().equals("Iron ore"));
			test.update();
			test2.update();
		}
		
		assertTrue(test.getPosition().equals(new Position(1, 1)));
		assertTrue(test2.getPosition().equals(new Position(1, 3)));
		assertTrue(Game.getMap().getBuildingBlock(2, 0).getID().equals("Cavern"));
		assertTrue(Game.getMap().getBuildingBlock(2, 4).getID().equals("Cavern"));
		
		for (int i = 0; i < durability; i++) {
			assertTrue(test.getPosition().equals(new Position(1, 1)));
			assertTrue(test2.getPosition().equals(new Position(1, 3)));
			assertTrue(Game.getMap().getBuildingBlock(2, 1).getID().equals("Iron ore"));
			assertTrue(Game.getMap().getBuildingBlock(2, 3).getID().equals("Iron ore"));
			test.update();
			test2.update();
		}
		
		assertTrue(test.getPosition().equals(new Position(2, 1)));
		assertTrue(test2.getPosition().equals(new Position(2, 3)));
		assertTrue(Game.getMap().getBuildingBlock(2, 1).getID().equals("Cavern"));
		assertTrue(Game.getMap().getBuildingBlock(2, 3).getID().equals("Cavern"));
		
		for (int i = 0; i < durability; i++) {
			assertTrue(test.getPosition().equals(new Position(2, 1)));
			assertTrue(test2.getPosition().equals(new Position(2, 3)));
			assertTrue(Game.getMap().getBuildingBlock(2, 2).getID().equals("Iron ore"));
			test.update();
			test2.update();
		}
		
		assertTrue(test.getPosition().equals(new Position(2, 1)));
		assertTrue(test2.getPosition().equals(new Position(2, 3)));
		assertTrue(Game.getMap().getBuildingBlock(2, 2).getID().equals("Cavern"));
	}

}
