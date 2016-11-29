/**
 * 
 */
package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Actors.CraftAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.Game.Game;
import model.Items.IronItem;
import model.Items.StoneItem;
import model.Items.WoodItem;
import model.Map.Map;
import model.Weapons.BasicSword;

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
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1, 1));
		BasicSword item = new BasicSword();
		test.addToActionQueue(new CraftAction(item, item.getRequiredMaterials()));
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
