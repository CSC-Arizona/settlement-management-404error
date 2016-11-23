package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Inventory;
import model.Map;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.Items.AntLarvaItem;
import model.Items.StoneItem;
import model.Items.WoodItem;

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
		Inventory inv = new Inventory(null, null);
		assertEquals(0.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new AntLarvaItem());
		assertEquals(0.5, inv.getWeightCarried(), 0.0001);
		inv.addItem(new AntLarvaItem());
		assertEquals(1.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new StoneItem());
		assertEquals(6.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new WoodItem());
		assertEquals(9.0, inv.getWeightCarried(), 0.0001);
		assertEquals(new AntLarvaItem().getClass(), inv.removeItem(0)
				.getClass());
		assertEquals(8.5, inv.getWeightCarried(), 0.0001);
		assertEquals(new AntLarvaItem().getClass(), inv.removeItem(0)
				.getClass());
		assertEquals(8.0, inv.getWeightCarried(), 0.0001);
		assertEquals(new WoodItem().getClass(), inv.removeItem(1).getClass());
		assertEquals(5.0, inv.getWeightCarried(), 0.0001);
		assertEquals(null, inv.removeItem(10));
	}

	@Test
	public void testExceedingCapacity() {
		int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0 } };
		Map map = generateMap(mapGen);

		Inventory inv = new Inventory(new PlayerControlledActor(100, 100,
				new Position(0, 0), null, map), map);
		for (int i = 0; i < 30; i++) {
			assertTrue(inv.addItem(new StoneItem()));
		}
		assertTrue(inv.addItem(new AntLarvaItem()));
	}

}
