package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import model.armor.AntArmor;
import model.armor.GreatChestPlate;
import model.armor.GreatShield;
import model.armor.IronChestPlate;
import model.armor.IronShield;
import model.armor.StoneChestPlate;
import model.armor.StoneShield;
import model.armor.WoodChestPlate;
import model.armor.WoodShield;
import model.items.AntLarvaItem;
import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class tests the construction of Armor items
public class ArmorTest {
	
	private WoodItem wi = new WoodItem();
	private StoneItem si = new StoneItem();
	private IronItem ii = new IronItem();
	private AntLarvaItem ali = new AntLarvaItem();
	
	@Test
	public void testWoodShield() {
	    WoodShield ws = new WoodShield();
	    assertFalse(ws.getIsEdible());
	    assertEquals(4, ws.getAttackModifier());
	    assertEquals(10, ws.getDefenseModifier());
	    assertEquals(0, ws.getHealthPoints());
	    assertEquals(9.0, ws.getWeight(), 0.00001);
	    List<Item> wsList = WoodShield.getRequiredMaterials();
	    ListIterator<Item> li = wsList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
	}
	
	@Test
	public void testStoneShield() {
	    StoneShield ss = new StoneShield();
	    assertFalse(ss.getIsEdible());
	    assertEquals(10, ss.getAttackModifier());
	    assertEquals(12, ss.getDefenseModifier());
	    assertEquals(0, ss.getHealthPoints());
	    assertEquals(15.0, ss.getWeight(), 0.00001);
	    List<Item> ssList = StoneShield.getRequiredMaterials();
	    ListIterator<Item> li = ssList.listIterator();
	    assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
	}
	
	@Test
	public void testIronShield() {
	    IronShield is = new IronShield();
	    assertFalse(is.getIsEdible());
	    assertEquals(18, is.getAttackModifier());
	    assertEquals(14, is.getDefenseModifier());
	    assertEquals(0, is.getHealthPoints());
	    assertEquals(21.0, is.getWeight(), 0.00001);
	    List<Item> isList = IronShield.getRequiredMaterials();
	    ListIterator<Item> li = isList.listIterator();
	    assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testGreatShield() {
	    GreatShield gs = new GreatShield();
	    assertFalse(gs.getIsEdible());
	    assertEquals(20, gs.getAttackModifier());
	    assertEquals(16, gs.getDefenseModifier());
	    assertEquals(0, gs.getHealthPoints());
	    assertEquals(22.0, gs.getWeight(), 0.00001);
	    List<Item> gsList = GreatShield.getRequiredMaterials();
	    ListIterator<Item> li = gsList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testWoodChestPlate() {
	    WoodChestPlate wcp = new WoodChestPlate();
	    assertFalse(wcp.getIsEdible());
	    assertEquals(6, wcp.getAttackModifier());
	    assertEquals(12, wcp.getDefenseModifier());
	    assertEquals(0, wcp.getHealthPoints());
	    assertEquals(9.0, wcp.getWeight(), 0.00001);
	    List<Item> wcpList = WoodChestPlate.getRequiredMaterials();
	    ListIterator<Item> li = wcpList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
	}
	
	@Test
	public void testStoneChestPlate() {
	    StoneChestPlate scp = new StoneChestPlate();
	    assertFalse(scp.getIsEdible());
	    assertEquals(12, scp.getAttackModifier());
	    assertEquals(14, scp.getDefenseModifier());
	    assertEquals(0, scp.getHealthPoints());
	    assertEquals(20.0, scp.getWeight(), 0.00001);
	    List<Item> scpList = StoneChestPlate.getRequiredMaterials();
	    ListIterator<Item> li = scpList.listIterator();
	    assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
	}
	
	@Test
	public void testIronChestPlate() {
	    IronChestPlate icp = new IronChestPlate();
	    assertFalse(icp.getIsEdible());
	    assertEquals(20, icp.getAttackModifier());
	    assertEquals(16, icp.getDefenseModifier());
	    assertEquals(0, icp.getHealthPoints());
	    assertEquals(28.0, icp.getWeight(), 0.00001);
	    List<Item> icpList = IronChestPlate.getRequiredMaterials();
	    ListIterator<Item> li = icpList.listIterator();
	    assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testGreatChestPlate() {
	    GreatChestPlate gcp = new GreatChestPlate();
	    assertFalse(gcp.getIsEdible());
	    assertEquals(22, gcp.getAttackModifier());
	    assertEquals(18, gcp.getDefenseModifier());
	    assertEquals(0, gcp.getHealthPoints());
	    assertEquals(30.0, gcp.getWeight(), 0.00001);
	    List<Item> gcpList = GreatChestPlate.getRequiredMaterials();
	    ListIterator<Item> li = gcpList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	

	@Test
	public void testAntArmor() {
	    AntArmor aa = new AntArmor();
	    assertFalse(aa.getIsEdible());
	    assertEquals(10, aa.getAttackModifier());
	    assertEquals(10, aa.getDefenseModifier());
	    assertEquals(0, aa.getHealthPoints());
	    assertEquals(20.0, aa.getWeight(), 0.00001);
	    List<Item> aaList = aa.getRequiredMaterials();
	    ListIterator<Item> li = aaList.listIterator();
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ali.getClass(), li.next().getClass());
		assertEquals(ali.getClass(), li.next().getClass());
	}

}
