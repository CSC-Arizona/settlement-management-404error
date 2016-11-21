package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import model.Items.AntLarvaItem;
import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;
import model.Weapons.BasicIronAxe;
import model.Weapons.BasicStoneAxe;
import model.Weapons.BasicSword;
import model.Weapons.FortifiedIronAxe;
import model.Weapons.FortifiedStoneAxe;
import model.Weapons.LureAxe;
import model.Weapons.UltraSword;

public class WeaponTest {
	
	private AntLarvaItem ali = new AntLarvaItem();
	private WoodItem wi = new WoodItem();
	private StoneItem si = new StoneItem();
	private IronItem ii = new IronItem();

	@Test
	public void testBasicStoneAxe() {
	    BasicStoneAxe bsa = new BasicStoneAxe();
	    assertFalse(bsa.getIsEdible());
	    assertEquals(15, bsa.getAttackModifier());
	    assertEquals(0, bsa.getHealthPoints());
	    assertEquals(11.0, bsa.getWeight(), 0.00001);
	    List<Item> bsaList = bsa.getRequiredMaterials();
	    ListIterator<Item> li = bsaList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
	}
	
	@Test
	public void testFortifiedStoneAxe() {
	    FortifiedStoneAxe fsa = new FortifiedStoneAxe();
	    assertFalse(fsa.getIsEdible());
	    assertEquals(17, fsa.getAttackModifier());
	    assertEquals(0, fsa.getHealthPoints());
	    assertEquals(16.0, fsa.getWeight(), 0.00001);
	    List<Item> fsaList = fsa.getRequiredMaterials();
	    ListIterator<Item> li = fsaList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
	}
	
	@Test
	public void testBasicIronAxe() {
	    BasicIronAxe bia = new BasicIronAxe();
	    assertFalse(bia.getIsEdible());
	    assertEquals(19, bia.getAttackModifier());
	    assertEquals(0, bia.getHealthPoints());
	    assertEquals(18.0, bia.getWeight(), 0.00001);
	    List<Item> biaList = bia.getRequiredMaterials();
	    ListIterator<Item> li = biaList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testFortifiedIronAxe() {
	    FortifiedIronAxe fia = new FortifiedIronAxe();
	    assertFalse(fia.getIsEdible());
	    assertEquals(21, fia.getAttackModifier());
	    assertEquals(0, fia.getHealthPoints());
	    assertEquals(25.0, fia.getWeight(), 0.00001);
	    List<Item> fiaList = fia.getRequiredMaterials();
	    ListIterator<Item> li = fiaList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testLureAxe() {
	    LureAxe la = new LureAxe();
	    assertFalse(la.getIsEdible());
	    assertEquals(12, la.getAttackModifier());
	    assertEquals(1, la.getHealthPoints());
	    assertEquals(11.5, la.getWeight(), 0.00001);
	    List<Item> laList = la.getRequiredMaterials();
	    ListIterator<Item> li = laList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ali.getClass(), li.next().getClass());
	}
	
	@Test
	public void testBasicSword() {
	    BasicSword bs = new BasicSword();
	    assertFalse(bs.getIsEdible());
	    assertEquals(23, bs.getAttackModifier());
	    assertEquals(0, bs.getHealthPoints());
	    assertEquals(27.0, bs.getWeight(), 0.00001);
	    List<Item> bsList = bs.getRequiredMaterials();
	    ListIterator<Item> li = bsList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testUltraSword() {
	    UltraSword us = new UltraSword();
	    assertFalse(us.getIsEdible());
	    assertEquals(27, us.getAttackModifier());
	    assertEquals(0, us.getHealthPoints());
	    assertEquals(39.0, us.getWeight(), 0.00001);
	    List<Item> usList = us.getRequiredMaterials();
	    ListIterator<Item> li = usList.listIterator();
	    assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
}
