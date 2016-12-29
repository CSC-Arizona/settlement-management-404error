package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.LinkedList;

import org.junit.Test;

import model.building_blocks.AirBlock;
import model.building_blocks.AnthillBlock;
import model.building_blocks.AppleTreeLeafBlock;
import model.building_blocks.AppleTreeTrunkBlock;
import model.building_blocks.CavernBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.GoldOreBlock;
import model.building_blocks.GrassBlock;
import model.building_blocks.IronOreBlock;
import model.building_blocks.LavaBlock;
import model.building_blocks.StoneBlock;
import model.items.AntLarvaItem;
import model.items.GoldItem;
import model.items.IronItem;
import model.items.StoneItem;
import model.items.WoodItem;

/**
 * BuildingBlockTest tests the functionality of all of the BuildingBlocks.
 * 
 * @author Katherine Walters
 */
public class BuildingBlockTest {
	
	@Test
	public void testLeafBLock() {
		AppleTreeLeafBlock lb = new AppleTreeLeafBlock();
		assertTrue(lb.isOccupiable());
		assertTrue(lb.isDestroyable());
		assertEquals(new LinkedList<Object>().getClass(), lb.lootBlock().getClass());
		assertEquals(8, lb.lootBlock().size());
		assertEquals(1, lb.getDurability());
		assertEquals(new Color(242, 58, 58), lb.getColor());
	}
	
	@Test
	public void testBushBlock() {
		GrassBlock bb = new GrassBlock();
		assertTrue(bb.isOccupiable());
		assertTrue(bb.isDestroyable());
		assertEquals(6, bb.lootBlock().size());
		bb.lootBlock().get(0);
		assertEquals(1, bb.getDurability());
		assertEquals(new Color(0, 87, 3), bb.getColor());
	}
	
	@Test
	public void testGoldOreBlock() {
		GoldOreBlock gob = new GoldOreBlock();
		assertFalse(gob.isOccupiable());
		assertTrue(gob.isDestroyable());
		assertEquals(new GoldItem().getClass(), gob.lootBlock().get(0).getClass());
		assertEquals(10, gob.getDurability());
		assertEquals(new Color(255, 223, 0), gob.getColor());
	}
	
	@Test
	public void testWoodBlock() {
		AppleTreeTrunkBlock wb = new AppleTreeTrunkBlock();
		assertTrue(wb.isOccupiable());
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
		AnthillBlock atb = new AnthillBlock();
		assertFalse(atb.isOccupiable());
		assertTrue(atb.isDestroyable());
		assertEquals(new AntLarvaItem().getClass(), atb.lootBlock().get(0).getClass());
		assertEquals(1, atb.getDurability());
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

}
