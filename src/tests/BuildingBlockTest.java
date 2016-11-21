package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.LinkedList;

import org.junit.Test;

import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.AntTunnelBlock;
import model.BuildingBlocks.AppleTreeLeafBlock;
import model.BuildingBlocks.AppleTreeTrunkBlock;
import model.BuildingBlocks.CavernBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.GoldOreBlock;
import model.BuildingBlocks.GrassBlock;
import model.BuildingBlocks.IronOreBlock;
import model.BuildingBlocks.LavaBlock;
import model.BuildingBlocks.StoneBlock;
import model.BuildingBlocks.TreeRootBlock;
import model.Items.AntLarvaItem;
import model.Items.IronItem;
import model.Items.StoneItem;
import model.Items.WheatKernelItem;
import model.Items.WoodItem;

/**
 * BuildingBlockTest tests the functionality of all of the BuildingBlocks.
 * 
 * @author Katherine Walters
 */
public class BuildingBlockTest {
	
	@Test
	public void testLeafBLock() {
		AppleTreeLeafBlock lb = new AppleTreeLeafBlock();
		assertFalse(lb.isOccupiable());
		assertTrue(lb.isDestroyable());
		assertEquals(8, lb.lootBlock().size());
		assertEquals(1, lb.getDurability());
		assertEquals(new Color(242, 58, 58), lb.getColor());
	}
	
	@Test
	public void testBushBlock() {
		GrassBlock bb = new GrassBlock();
		assertFalse(bb.isOccupiable());
		assertTrue(bb.isDestroyable());
		assertEquals(1, bb.lootBlock().size());
		WheatKernelItem i = (WheatKernelItem) bb.lootBlock().get(0);
		assertEquals(1, bb.getDurability());
		assertEquals(new Color(0, 87, 3), bb.getColor());
	}
	
	@Test
	public void testGoldOreBlock() {
		GoldOreBlock gob = new GoldOreBlock();
		assertFalse(gob.isOccupiable());
		assertTrue(gob.isDestroyable());
		assertEquals(new LinkedList(), gob.lootBlock());
		assertEquals(10, gob.getDurability());
		assertEquals(new Color(255, 223, 0), gob.getColor());
	}
	
	@Test
	public void testWoodBlock() {
		AppleTreeTrunkBlock wb = new AppleTreeTrunkBlock();
		assertFalse(wb.isOccupiable());
		assertTrue(wb.isDestroyable());
		assertEquals(new WoodItem().getClass(), wb.lootBlock().get(0).getClass());
		assertEquals(5, wb.getDurability());
		assertEquals(new Color(174, 144, 55), wb.getColor());
	}
	
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
