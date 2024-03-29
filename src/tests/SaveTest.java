package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.actors.Actor;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.game.Game;
import model.items.IronItem;
import model.map.Map;
import model.save.SaveFile;
import model.weapons.BasicSword;

/**
 * @author Jonathon Davis
 *
 */
public class SaveTest {

	public Map generateMap(int[][] map) {
		Game.reset();
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
	public void testSave() {
		Game.reset();
		// create an intial state of the game
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		Actor.allActors = null;
		PlayerControlledActor test = new PlayerControlledActor(new Position(4, 2));
		PlayerControlledActor test2 = new PlayerControlledActor(new Position(3, 3));
		PlayerControlledActor test3 = new PlayerControlledActor(new Position(1, 1));
		test.getInventory().addItem(new BasicSword());
		test2.getInventory().addItem(new IronItem());
		Map oldMap = Game.getMap();
		
		// save the current state
		new SaveFile("test").save();

		//flush out the old state and create a new state
		int[][] mapGen2 = new int[][] {{ 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 0, 0 }, 
										{ 1, 0, 0, 0, 1 },
										{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen2));
		Actor.allActors = null;
		PlayerControlledActor bad = new PlayerControlledActor(new Position(3, 1));
		PlayerControlledActor bad2 = new PlayerControlledActor(new Position(3, 2));
		PlayerControlledActor bad3 = new PlayerControlledActor(new Position(3, 3));
		
		//test to make sure the flush worked
		assertFalse(Actor.allActors.contains(test));
		assertFalse(Actor.allActors.contains(test2));
		assertFalse(Actor.allActors.contains(test3));
		assertTrue(Actor.allActors.contains(bad));
		assertTrue(Actor.allActors.contains(bad2));
		assertTrue(Actor.allActors.contains(bad3));
		
		new SaveFile().load("test");
		
		//test if the map is the same
		for(int i = 0; i < mapGen.length; i++){
			for(int j = 0; j < mapGen[i].length; j++){
				assertTrue(oldMap.getBuildingBlock(i, j).getID().equals(Game.getMap().getBuildingBlock(i, j).getID()));
			}
		}
		
		assertTrue(test.getPosition().equals(Actor.allActors.get(0).getPosition()));
		assertTrue(test2.getPosition().equals(Actor.allActors.get(1).getPosition()));
		assertTrue(test3.getPosition().equals(Actor.allActors.get(2).getPosition()));
		assertTrue(test.getInventory().contains(Actor.allActors.get(0).getInventory().getItem(0)));
		assertTrue(test2.getInventory().contains(Actor.allActors.get(1).getInventory().getItem(0)));

	}

}
