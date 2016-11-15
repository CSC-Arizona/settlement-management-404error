package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import model.Items.GreatChestPlate;
import model.Items.GreatShield;
import model.Items.IronChestPlate;
import model.Items.IronShield;
import model.Items.StoneChestPlate;
import model.Items.StoneShield;
import model.Items.UltraSword;
import model.Items.WoodChestPlate;
import model.Items.WoodShield;

//Author: Maxwell Faridian
//This class tests the construction of Armor items
public class ArmorTest {
	
	//TODO: Write test cases for armor classes
	
	@Test
	public void testWoodShield() {
	    WoodShield ws = new WoodShield();
	    assertFalse(ws.getIsEdible());
	    assertEquals(4, ws.getAttackModifier());
	    assertEquals(10, ws.getDefenseModifier());
	    assertEquals(0, ws.getHealthPoints());
	    assertEquals(9.0, ws.getWeight(), 0.00001);
	}
	
	@Test
	public void testStoneShield() {
	    StoneShield ss = new StoneShield();
	    assertFalse(ss.getIsEdible());
	    assertEquals(10, ss.getAttackModifier());
	    assertEquals(12, ss.getDefenseModifier());
	    assertEquals(0, ss.getHealthPoints());
	    assertEquals(15.0, ss.getWeight(), 0.00001);
	}
	
	@Test
	public void testIronShield() {
	    IronShield is = new IronShield();
	    assertFalse(is.getIsEdible());
	    assertEquals(18, is.getAttackModifier());
	    assertEquals(14, is.getDefenseModifier());
	    assertEquals(0, is.getHealthPoints());
	    assertEquals(21.0, is.getWeight(), 0.00001);
	}
	
	@Test
	public void testGreatShield() {
	    GreatShield gs = new GreatShield();
	    assertFalse(gs.getIsEdible());
	    assertEquals(20, gs.getAttackModifier());
	    assertEquals(16, gs.getDefenseModifier());
	    assertEquals(0, gs.getHealthPoints());
	    assertEquals(22.0, gs.getWeight(), 0.00001);
	}
	
	@Test
	public void testWoodChestPlate() {
	    WoodChestPlate wcp = new WoodChestPlate();
	    assertFalse(wcp.getIsEdible());
	    assertEquals(6, wcp.getAttackModifier());
	    assertEquals(12, wcp.getDefenseModifier());
	    assertEquals(0, wcp.getHealthPoints());
	    assertEquals(9.0, wcp.getWeight(), 0.00001);
	}
	
	@Test
	public void testStoneChestPlate() {
	    StoneChestPlate scp = new StoneChestPlate();
	    assertFalse(scp.getIsEdible());
	    assertEquals(12, scp.getAttackModifier());
	    assertEquals(14, scp.getDefenseModifier());
	    assertEquals(0, scp.getHealthPoints());
	    assertEquals(20.0, scp.getWeight(), 0.00001);
	}
	
	@Test
	public void testIronChestPlate() {
	    IronChestPlate icp = new IronChestPlate();
	    assertFalse(icp.getIsEdible());
	    assertEquals(20, icp.getAttackModifier());
	    assertEquals(16, icp.getDefenseModifier());
	    assertEquals(0, icp.getHealthPoints());
	    assertEquals(28.0, icp.getWeight(), 0.00001);
	}
	
	@Test
	public void testGreatChestPlate() {
	    GreatChestPlate gcp = new GreatChestPlate();
	    assertFalse(gcp.getIsEdible());
	    assertEquals(22, gcp.getAttackModifier());
	    assertEquals(18, gcp.getDefenseModifier());
	    assertEquals(0, gcp.getHealthPoints());
	    assertEquals(30.0, gcp.getWeight(), 0.00001);
	}
	

}
