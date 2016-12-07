package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import model.furniture.AppleTreePlot;
import model.furniture.BasicCrate;
import model.furniture.Bed;
import model.furniture.Couch;
import model.furniture.Fireplace;
import model.furniture.HealingBed;
import model.furniture.IncubationChamber;
import model.furniture.Ladder;
import model.furniture.MetalCrate;
import model.furniture.CraftingMachine;
import model.furniture.PoolTable;
import model.furniture.ReinforcedCrate;
import model.furniture.WheatPlantPlot;
import model.items.AntLarvaItem;
import model.items.AppleSeedItem;
import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WheatKernelItem;
import model.items.WheatStemItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class tests the construction of furniture items and the methods within furniture classes
public class FurnitureTest {

	private AppleSeedItem asi = new AppleSeedItem();
	private AntLarvaItem ali = new AntLarvaItem();
	private WoodItem wi = new WoodItem();
	private StoneItem si = new StoneItem();
	private WheatStemItem wsi = new WheatStemItem();
	private IronItem ii = new IronItem();
	private WheatKernelItem wki = new WheatKernelItem();
	
	@Test
	public void testAppleTreePlot() {
		AppleTreePlot atp = new AppleTreePlot();
		assertEquals(1, atp.getCapacity());
		atp.increaseCapacityBy(1);
		assertEquals(2, atp.getCapacity());
		List<Item> appleTreePlotList = atp.getRequiredMaterials();
		ListIterator<Item> li = appleTreePlotList.listIterator();
		assertEquals(asi.getClass(), li.next().getClass());
		assertEquals(asi.getClass(), li.next().getClass());
		assertEquals(ali.getClass(), li.next().getClass());
		assertEquals(ali.getClass(), li.next().getClass());
	}

	@Test
	public void testBasicCrate() {
		BasicCrate bc = new BasicCrate();
		assertEquals(5, bc.getCapacity());
		bc.increaseCapacityBy(1);
		assertEquals(6, bc.getCapacity());
		assertEquals(30, bc.getWeightCapacity(), 1e-10);
		List<Item> basicCrateList = bc.getRequiredMaterials();
		ListIterator<Item> li = basicCrateList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());

	}
	
	@Test
	public void testBed() {
		Bed b = new Bed();
		assertEquals(2, b.getCapacity());
		b.increaseCapacityBy(1);
		assertEquals(3, b.getCapacity());
		List<Item> bedList = b.getRequiredMaterials();
		ListIterator<Item> li = bedList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());

	}
	
	@Test
	public void testCouch() {
		Couch c = new Couch();
		assertEquals(3, c.getCapacity());
		c.increaseCapacityBy(1);
		assertEquals(4, c.getCapacity());
		List<Item> couchList = c.getRequiredMaterials();
		ListIterator<Item> li = couchList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
	}
	
	@Test
	public void testFireplace() {
		Fireplace f = new Fireplace();
		assertEquals(2, f.getCapacity());
		f.increaseCapacityBy(1);
		assertEquals(3, f.getCapacity());
		List<Item> fireplaceList = f.getRequiredMaterials();
		ListIterator<Item> li = fireplaceList.listIterator();
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testHealingBed() {
		HealingBed hb = new HealingBed();
		assertEquals(2, hb.getCapacity());
		hb.increaseCapacityBy(1);
		assertEquals(3, hb.getCapacity());
		List<Item> healingBedList = hb.getRequiredMaterials();
		ListIterator<Item> li = healingBedList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());

	}
	
	@Test
	public void testMetalCrate() {
		MetalCrate mc = new MetalCrate(new LinkedList<Item>());
		assertEquals(20, mc.getCapacity());
		mc.increaseCapacityBy(1);
		assertEquals(21, mc.getCapacity());
		assertEquals(200, mc.getWeightCapacity(), 1e-10);
		List<Item> metalCrateList = mc.getRequiredMaterials();
		ListIterator<Item> li = metalCrateList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testMillingMachine() {
		CraftingMachine mm = new CraftingMachine();
		assertEquals(0, mm.getCapacity());
		mm.increaseCapacityBy(1);
		assertEquals(1, mm.getCapacity());
		List<Item> millingMachineList = mm.getRequiredMaterials();
		ListIterator<Item> li = millingMachineList.listIterator();
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testPoolTable() {
		PoolTable pt = new PoolTable();
		assertEquals(4, pt.getCapacity());
		pt.increaseCapacityBy(1);
		assertEquals(5, pt.getCapacity());
		List<Item> poolTableList = pt.getRequiredMaterials();
		ListIterator<Item> li = poolTableList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());

	}
	
	@Test
	public void testReinforcedCrate() {
		ReinforcedCrate rc = new ReinforcedCrate(new LinkedList<Item>());
		assertEquals(50, rc.getCapacity());
		rc.increaseCapacityBy(1);
		assertEquals(51, rc.getCapacity());
		assertEquals(500, rc.getWeightCapacity(), 1e-10);
		List<Item> reinforcedCrateList = rc.getRequiredMaterials();
		ListIterator<Item> li = reinforcedCrateList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
	}
	
	@Test
	public void testWheatPlantPlot() {
		WheatPlantPlot wpp = new WheatPlantPlot();
		assertEquals(1, wpp.getCapacity());
		wpp.increaseCapacityBy(1);
		assertEquals(2, wpp.getCapacity());
		List<Item> wheatPlantPlotList = wpp.getRequiredMaterials();
		ListIterator<Item> li = wheatPlantPlotList.listIterator();
		assertEquals(wki.getClass(), li.next().getClass());
		assertEquals(wki.getClass(), li.next().getClass());
		assertEquals(ali.getClass(), li.next().getClass());
		assertEquals(ali.getClass(), li.next().getClass());
	}
	
	@Test
	public void testLadder() {
		Ladder l = new Ladder();
		assertEquals(10, l.getCapacity());
		l.increaseCapacityBy(1);
		assertEquals(11, l.getCapacity());
		List<Item> ladderList = l.getRequiredMaterials();
		ListIterator<Item> li = ladderList.listIterator();

	}
	
	@Test
	public void testIncubationChamber() {
		IncubationChamber ic = new IncubationChamber();
		assertEquals(1, ic.getCapacity());
		ic.increaseCapacityBy(1);
		assertEquals(2, ic.getCapacity());
		List<Item> incubationChamberList = ic.getRequiredMaterials();
		ListIterator<Item> li = incubationChamberList.listIterator();
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
	}
	
}
