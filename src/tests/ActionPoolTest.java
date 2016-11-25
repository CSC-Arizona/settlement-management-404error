/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Map;
import model.Actors.GatherAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;

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
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 0, 0 }, 
										{ 2, 2, 2, 2, 2 }, 
										{ 1, 1, 1, 1, 1 },
										{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1, 1));
		PlayerControlledActor test2 = new PlayerControlledActor(10, new Position(1, 3));
		PlayerControlledActor.addAction(new GatherAction(new Position(2, 0)));
		PlayerControlledActor.addAction(new GatherAction(new Position(2, 4)));
		PlayerControlledActor.addAction(new GatherAction(new Position(2, 1)));
		PlayerControlledActor.addAction(new GatherAction(new Position(2, 3)));
		PlayerControlledActor.addAction(new GatherAction(new Position(2, 2)));

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
		assertTrue(Game.getMap().getBuildingBlock(2, 0).getID().equals("Air"));
		assertTrue(Game.getMap().getBuildingBlock(2, 4).getID().equals("Air"));
		
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
		assertTrue(Game.getMap().getBuildingBlock(2, 1).getID().equals("Air"));
		assertTrue(Game.getMap().getBuildingBlock(2, 3).getID().equals("Air"));
		
		for (int i = 0; i < durability; i++) {
			assertTrue(test.getPosition().equals(new Position(2, 1)));
			assertTrue(test2.getPosition().equals(new Position(2, 3)));
			assertTrue(Game.getMap().getBuildingBlock(2, 2).getID().equals("Iron ore"));
			test.update();
			test2.update();
		}
		
		assertTrue(test.getPosition().equals(new Position(2, 1)));
		assertTrue(test2.getPosition().equals(new Position(2, 3)));
		assertTrue(Game.getMap().getBuildingBlock(2, 2).getID().equals("Air"));
	}

}
