package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import model.furniture.BasicCrate;
import model.furniture.MetalCrate;
import model.furniture.ReinforcedCrate;
import model.items.StoneItem;
import model.items.*;

public class CrateTests {

	@Test
	public void test() {
		BasicCrate bc = new BasicCrate();
		assertEquals(30.0, bc.getCrateCapacity(), 0.00001);
		assertEquals(0.0, bc.getWeightCarried(), 0.00001);
		assertEquals("Basic crate 0.0/30.0 weight carried", bc.toString());
		assertTrue(bc.addItem(new StoneItem()));
		assertEquals("Basic crate 5.0/30.0 weight carried (1 Stone)", bc.toString());
		assertTrue(bc.addItem(new StoneItem()));
		assertEquals("Basic crate 10.0/30.0 weight carried (2 Stones)", bc.toString());
		assertTrue(bc.contains(new StoneItem()));
		assertTrue(bc.removeItem(new StoneItem()));
		assertEquals(5.0, bc.getWeightCarried(), 0.0001);
		assertEquals("Basic crate 5.0/30.0 weight carried (1 Stone)", bc.toString());
		assertTrue(bc.removeItem(new StoneItem()));
		assertEquals(0.0, bc.getWeightCarried(), 0.0001);
		assertEquals("Basic crate 0.0/30.0 weight carried", bc.toString());
		
		MetalCrate mc = new MetalCrate(new LinkedList<Item>());
		assertEquals(200.0, mc.getCrateCapacity(), 0.00001);
		assertEquals(0.0, mc.getWeightCarried(), 0.00001);
		assertEquals("Metal crate 0.0/200.0 weight carried", mc.toString());
		assertTrue(mc.addItem(new StoneItem()));
		assertEquals("Metal crate 5.0/200.0 weight carried (1 Stone)", mc.toString());
		assertTrue(mc.addItem(new StoneItem()));
		assertEquals("Metal crate 10.0/200.0 weight carried (2 Stones)", mc.toString());
		assertTrue(mc.contains(new StoneItem()));
		assertTrue(mc.removeItem(new StoneItem()));
		assertEquals(5.0, mc.getWeightCarried(), 0.0001);
		assertEquals("Metal crate 5.0/200.0 weight carried (1 Stone)", mc.toString());
		assertTrue(mc.removeItem(new StoneItem()));
		assertEquals(0.0, mc.getWeightCarried(), 0.0001);
		assertEquals("Metal crate 0.0/200.0 weight carried", mc.toString());
	}	
    
	@Test
	public void testRCrate() {
		ReinforcedCrate mc = new ReinforcedCrate(new LinkedList<Item>());
		assertEquals(500.0, mc.getCrateCapacity(), 0.00001);
		assertEquals(0.0, mc.getWeightCarried(), 0.00001);
		assertEquals("Reinforced crate 0.0/500.0 weight carried", mc.toString());
		assertTrue(mc.addItem(new StoneItem()));
		assertEquals("Reinforced crate 5.0/500.0 weight carried (1 Stone)", mc.toString());
		assertTrue(mc.addItem(new StoneItem()));
		assertEquals("Reinforced crate 10.0/500.0 weight carried (2 Stones)", mc.toString());
		assertTrue(mc.contains(new StoneItem()));
		assertTrue(mc.removeItem(new StoneItem()));
		assertEquals(5.0, mc.getWeightCarried(), 0.0001);
		assertEquals("Reinforced crate 5.0/500.0 weight carried (1 Stone)", mc.toString());
		assertTrue(mc.removeItem(new StoneItem()));
		assertEquals(0.0, mc.getWeightCarried(), 0.0001);
		assertEquals("Reinforced crate 0.0/500.0 weight carried", mc.toString());
	}

}
