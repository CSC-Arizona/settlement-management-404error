package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Inventory;
import model.Items.AntLarvaItem;
import model.Items.StoneItem;
import model.Items.WoodItem;

/**
 * InventoryTest covers the functionality of the Inventory class.
 * 
 * @author Katherine Walters
 */
public class InventoryTest {

	@Test
	public void test() {
		Inventory inv = new Inventory();
		assertEquals(0.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new AntLarvaItem());
		assertEquals(0.5, inv.getWeightCarried(), 0.0001);
		inv.addItem(new AntLarvaItem());
		assertEquals(1.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new StoneItem());
		assertEquals(6.0, inv.getWeightCarried(), 0.0001);
		inv.addItem(new WoodItem());
		assertEquals(9.0, inv.getWeightCarried(), 0.0001);
		assertEquals(new AntLarvaItem().getClass(), inv.removeItem(0).getClass());
		assertEquals(8.5, inv.getWeightCarried(), 0.0001);
		assertEquals(new AntLarvaItem().getClass(), inv.removeItem(0).getClass());
		assertEquals(8.0, inv.getWeightCarried(), 0.0001);
		assertEquals(new WoodItem().getClass(), inv.removeItem(1).getClass());
		assertEquals(5.0, inv.getWeightCarried(), 0.0001);
		assertEquals(null, inv.removeItem(10));
	}
	
	@Test
	public void testExceedingCapacity() {
		Inventory inv = new Inventory();
		for (int i = 0; i < 20; i++) {
			assertTrue(inv.addItem(new StoneItem()));
		}
		assertFalse(inv.addItem(new AntLarvaItem()));
	}

}
