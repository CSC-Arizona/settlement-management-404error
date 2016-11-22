package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import model.Furniture.AppleTreePlot;
import model.Furniture.BasicCrate;
import model.Furniture.Bed;
import model.Furniture.Couch;
import model.Furniture.Fireplace;
import model.Furniture.HealingBed;
import model.Furniture.Ladder;
import model.Furniture.MetalCrate;
import model.Furniture.MillingMachine;
import model.Furniture.PoolTable;
import model.Furniture.ReinforcedCrate;
import model.Furniture.WheatPlantPlot;
import model.Items.AntLarvaItem;
import model.Items.AppleSeedItem;
import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WheatKernelItem;
import model.Items.WheatStemItem;
import model.Items.WoodItem;

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
		assertEquals(1, bc.getCapacity());
		bc.increaseCapacityBy(1);
		assertEquals(2, bc.getCapacity());
		assertEquals(30, bc.getWeightCapacity());
		List<Item> basicCrateList = bc.getRequiredMaterials();
		ListIterator<Item> li = basicCrateList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
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
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
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
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
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
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
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
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(wsi.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testMetalCrate() {
		MetalCrate mc = new MetalCrate();
		assertEquals(1, mc.getCapacity());
		mc.increaseCapacityBy(1);
		assertEquals(2, mc.getCapacity());
		assertEquals(70, mc.getWeightCapacity());
		List<Item> metalCrateList = mc.getRequiredMaterials();
		ListIterator<Item> li = metalCrateList.listIterator();
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testMillingMachine() {
		MillingMachine mm = new MillingMachine();
		assertEquals(0, mm.getCapacity());
		mm.increaseCapacityBy(1);
		assertEquals(1, mm.getCapacity());
		List<Item> millingMachineList = mm.getRequiredMaterials();
		ListIterator<Item> li = millingMachineList.listIterator();
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
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
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(si.getClass(), li.next().getClass());
		assertEquals(ii.getClass(), li.next().getClass());
	}
	
	@Test
	public void testReinforcedCrate() {
		ReinforcedCrate rc = new ReinforcedCrate();
		assertEquals(1, rc.getCapacity());
		rc.increaseCapacityBy(1);
		assertEquals(2, rc.getCapacity());
		assertEquals(50, rc.getWeightCapacity());
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
		assertEquals(wi.getClass(), li.next().getClass());
		assertEquals(wi.getClass(), li.next().getClass());
	}
	
}