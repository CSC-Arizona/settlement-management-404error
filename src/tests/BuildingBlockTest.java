package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.AntTunnelBlock;
import model.BuildingBlocks.CavernBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;
import model.BuildingBlocks.LavaBlock;
import model.BuildingBlocks.StoneBlock;
import model.BuildingBlocks.TreeRootBlock;
import model.Items.AntLarvaItem;
import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

/**
 * BuildingBlockTest tests the functionality of all of the BuildingBlocks.
 * 
 * @author Katherine Walters
 */
public class BuildingBlockTest {

	@Test
	public void testAirBlock() {
		AirBlock ab = new AirBlock();
		assertTrue(ab.isOccupiable());
		assertFalse(ab.isDestroyable());
		assertEquals(null, ab.lootBlock());
		assertEquals(0, ab.getDurability());
	}
	
	@Test
	public void testAntTunnelBlock() {
		AntTunnelBlock atb = new AntTunnelBlock();
		assertTrue(atb.isOccupiable());
		assertTrue(atb.isDestroyable());
		assertEquals(new AntLarvaItem().getClass(), atb.lootBlock().get(0).getClass());
		assertEquals(7, atb.getDurability());
	}
	
	@Test
	public void testCavernBlock() {
		CavernBlock cb = new CavernBlock();
		assertTrue(cb.isOccupiable());
		assertFalse(cb.isDestroyable());
		assertEquals(null, cb.lootBlock());
		assertEquals(0, cb.getDurability());
	}
	
	@Test
	public void testEarthBlock() {
		EarthBlock eb = new EarthBlock();
		assertFalse(eb.isOccupiable());
		assertTrue(eb.isDestroyable());
		assertEquals(null, eb.lootBlock());
		assertEquals(3, eb.getDurability());
	}
	
	@Test
	public void testIronOreBlock() {
		IronOreBlock iob = new IronOreBlock();
		assertFalse(iob.isOccupiable());
		assertTrue(iob.isDestroyable());
		assertEquals(new IronItem().getClass(), iob.lootBlock().get(0).getClass());
		assertEquals(10, iob.getDurability());
	}
	
	@Test
	public void testLavaBlock() {
		LavaBlock lb = new LavaBlock();
		assertFalse(lb.isOccupiable());
		assertFalse(lb.isDestroyable());
		assertEquals(null, lb.lootBlock());
		assertEquals(0, lb.getDurability());
	}
	
	@Test
	public void testStoneBlock() {
		StoneBlock sb = new StoneBlock();
		assertFalse(sb.isOccupiable());
		assertTrue(sb.isDestroyable());
		assertEquals(new StoneItem().getClass(), sb.lootBlock().get(0).getClass());
		assertEquals(8, sb.getDurability());
	}
	
	@Test
	public void testTreeRootBlock() {
		TreeRootBlock trb = new TreeRootBlock();
		assertFalse(trb.isOccupiable());
		assertTrue(trb.isDestroyable());
		assertEquals(new WoodItem().getClass(), trb.lootBlock().get(0).getClass());
		assertEquals(5, trb.getDurability());
	}

}
