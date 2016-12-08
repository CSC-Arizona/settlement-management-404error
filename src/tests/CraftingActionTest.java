/**
 * 
 */
package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.actors.CraftAction;
import model.actors.PlayerControlledActor;
import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.game.Game;
import model.items.IronItem;
import model.items.StoneItem;
import model.items.WoodItem;
import model.map.Map;
import model.weapons.BasicSword;

/**
 * @author Jonathon Davis
 *
 */
public class CraftingActionTest {
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
	public void testCrafting() {
		Game.reset();
		int[][] mapGen = new int[][] { { 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 } };
		Game.setMap(generateMap(mapGen));
		PlayerControlledActor test = new PlayerControlledActor(new Position(1, 1));
		BasicSword item = new BasicSword();
		//test.addToActionQueue(new CraftAction(item, BasicSword.getRequiredMaterials()));
		test.getInventory().addItem(new WoodItem());
		test.getInventory().addItem(new WoodItem());
		test.getInventory().addItem(new StoneItem());
		test.getInventory().addItem(new StoneItem());
		test.getInventory().addItem(new IronItem());
		test.update();
		assertTrue(test.getInventory().contains(new WoodItem()));
		assertFalse(test.getInventory().contains(new StoneItem()));
		assertFalse(test.getInventory().contains(new IronItem()));
		assertFalse(test.getInventory().contains(new BasicSword()));
		test.getInventory().addItem(new IronItem());
		test.update();
		assertTrue(test.getInventory().contains(new WoodItem()));
		assertFalse(test.getInventory().contains(new StoneItem()));
		assertFalse(test.getInventory().contains(new IronItem()));
		assertTrue(test.getInventory().contains(new BasicSword()));
	}

}
