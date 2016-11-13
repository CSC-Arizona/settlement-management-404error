package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.AntLarvaItem;
import model.IronItem;
import model.StoneItem;
import model.WoodItem;

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

}
