package tests;

//Author: Maxwell Faridian
//This class tests the functionality of the Crate class and all classes that extend Crate 
//Tests edible, attackMod, healthPts, weight, weightCapacity
import static org.junit.Assert.*;

import org.junit.Test;

import model.Items.BasicCrate;
import model.Items.MetalCrate;
import model.Items.ReinforcedCrate;

public class CrateTest {

	@Test
	public void testBasicCrate() {
		BasicCrate bc = new BasicCrate();
		assertFalse(bc.getIsEdible());
		assertEquals(6, bc.getAttackModifier());
		assertEquals(0, bc.getHealthPoints());
		assertEquals(10.0, bc.getWeight(), 0.00001);
		assertEquals(30, bc.getWeightCapacity());
	}
	
	@Test
	public void testReinforcedCrate() {
		ReinforcedCrate rc = new ReinforcedCrate();
		assertFalse(rc.getIsEdible());
		assertEquals(6, rc.getAttackModifier());
		assertEquals(0, rc.getHealthPoints());
		assertEquals(20.0, rc.getWeight(), 0.00001);
		assertEquals(50, rc.getWeightCapacity());
	}
	
	@Test
	public void testMetalCrate() {
		MetalCrate mc = new MetalCrate();
		assertFalse(mc.getIsEdible());
		assertEquals(6, mc.getAttackModifier());
		assertEquals(0, mc.getHealthPoints());
		assertEquals(30.0, mc.getWeight(), 0.00001);
		assertEquals(70, mc.getWeightCapacity());
	}

}
