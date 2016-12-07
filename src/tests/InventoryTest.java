package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.actors.Inventory;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.game.Game;
import model.items.AntLarvaItem;
import model.items.StoneItem;
import model.items.WoodItem;
import model.map.Map;

/**
 * InventoryTest covers the functionality of the Inventory class.
 * 
 * @author Katherine Walters
 */
public class InventoryTest {

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
	public void test() {
		Game.reset();
		Inventory inv = new Inventory();
		assertEquals(0.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new AntLarvaItem());
		assertEquals(0.5, inv.getWeightCarried(), 0.0001);
		inv.addItem(new AntLarvaItem());
		assertEquals(1.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new StoneItem());
		assertEquals(6.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new WoodItem());
		assertEquals(7.0, inv.getWeightCarried(), 0.0001);
		assertEquals(new AntLarvaItem().getClass(), inv.removeItem(0)
				.getClass());
		assertEquals(6.5, inv.getWeightCarried(), 0.0001);
		assertEquals(new AntLarvaItem().getClass(), inv.removeItem(0)
				.getClass());
		assertEquals(6.0, inv.getWeightCarried(), 0.0001);
		assertEquals(new WoodItem().getClass(), inv.removeItem(1).getClass());
		assertEquals(5.0, inv.getWeightCarried(), 0.0001);
		assertEquals(null, inv.removeItem(10));
	}

	@Test
	public void testExceedingCapacity() {
		Game.reset();
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0 } };
		Game.setMap(generateMap(mapGen));

		Inventory inv = new Inventory();
		for (int i = 0; i < 20; i++) {
			assertTrue(inv.canAdd(new StoneItem()));
			inv.addItem(new StoneItem());
		}
		assertFalse(inv.canAdd(new StoneItem()));
	}
	
	@Test
	public void testingSimpleContainsAndRemove() {
		Inventory inv = new Inventory();
		assertTrue(inv.addItem(new StoneItem()));
		assertEquals("Inventory: 5% full (1 Stone)", inv.toString());
		assertTrue(inv.contains(new StoneItem()));
		assertTrue(inv.removeItem(new StoneItem()));
		assertFalse(inv.contains(new StoneItem()));
		assertFalse(inv.removeItem(new StoneItem()));
	}

}
