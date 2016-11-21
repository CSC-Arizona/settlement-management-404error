package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Items.AntLarvaItem;
import model.Items.AppleItem;
import model.Items.AppleSeedItem;
import model.Items.IronItem;
import model.Items.StoneItem;
import model.Items.WheatItem;
import model.Items.WheatKernelItem;
import model.Items.WheatStemItem;
import model.Items.WoodItem;

/**
 * ItemTest covers all of the functionality of the Item constructor and 
 * the resource items (AntLarvaItem, IronItem, StoneItem, and WoodItem).
 * 
 * @author Katherine Walters
 */
public class ItemTest {
	
	@Test
	public void testAntLarvaItem() {
	    AntLarvaItem ali = new AntLarvaItem();
	    assertTrue(ali.getIsEdible());
	    assertEquals(1, ali.getAttackModifier());
	    assertEquals(2, ali.getHealthPoints());
	    assertEquals(0.5, ali.getWeight(), 0.00001);
	}
	
	@Test
	public void testIronItem() {
	    IronItem ii = new IronItem();
	    assertFalse(ii.getIsEdible());
	    assertEquals(10, ii.getAttackModifier());
	    assertEquals(0, ii.getHealthPoints());
	    assertEquals(7.0, ii.getWeight(), 0.00001);
	}
	
	@Test
	public void testStoneItem() {
		StoneItem si = new StoneItem();
		assertFalse(si.getIsEdible());
	    assertEquals(5, si.getAttackModifier());
	    assertEquals(0, si.getHealthPoints());
	    assertEquals(5.0, si.getWeight(), 0.0001);
	}
	
	@Test
	public void testWoodItem() {
		WoodItem wi = new WoodItem();
		assertFalse(wi.getIsEdible());
		assertEquals(2, wi.getAttackModifier());
		assertEquals(0, wi.getHealthPoints());
		assertEquals(3.0, wi.getWeight(), 0.00001);
	}
	
	@Test
	public void testAppleItem() {
	    AppleItem ai = new AppleItem();
	    assertTrue(ai.getIsEdible());
	    assertEquals(2, ai.getAttackModifier());
	    assertEquals(5, ai.getHealthPoints());
	    assertEquals(1.0, ai.getWeight(), 0.00001);
	}
	
	@Test
	public void testAppleSeedItem() {
	    AppleSeedItem asi = new AppleSeedItem();
	    assertFalse(asi.getIsEdible());
	    assertEquals(1, asi.getAttackModifier());
	    assertEquals(0, asi.getHealthPoints());
	    assertEquals(.2, asi.getWeight(), 0.00001);
	}
	
	@Test
	public void testWheatItem() {
	    WheatItem wi = new WheatItem();
	    assertTrue(wi.getIsEdible());
	    assertEquals(1, wi.getAttackModifier());
	    assertEquals(1, wi.getHealthPoints());
	    assertEquals(1, wi.getWeight(), 0.00001);
	}
	
	@Test
	public void testWheatKernelItem() {
	    WheatKernelItem wki = new WheatKernelItem();
	    assertTrue(wki.getIsEdible());
	    assertEquals(1, wki.getAttackModifier());
	    assertEquals(1, wki.getHealthPoints());
	    assertEquals(1, wki.getWeight(), 0.00001);
	}
	
	@Test
	public void testWheatStemItem() {
	    WheatStemItem wsi = new WheatStemItem();
	    assertFalse(wsi.getIsEdible());
	    assertEquals(1, wsi.getAttackModifier());
	    assertEquals(0, wsi.getHealthPoints());
	    assertEquals(1, wsi.getWeight(), 0.00001);
	}
	
	
	

}
