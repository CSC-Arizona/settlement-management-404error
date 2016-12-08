package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import model.actors.Position;
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
import model.items.Item;
import model.room.BedRoom;
import model.room.EntertainmentRoom;
import model.room.HorizontalTunnel;
import model.room.IncubationRoom;
import model.room.InfirmaryRoom;

/**
 * Tests the output of the reqItemsToString that exists in the CookableItem, Craftable, Furniture, 
 * and Room classes.
 * 
 * @author Katherine Walters
 */
public class TestPrintedRequiredItemLists {

//	@Test
//	public void testCookable() {
//		BreadCookable bc = new BreadCookable();
//		assertEquals("2 Wheats", bc.reqMaterialsToString());
//		AntLarvaPieCookable alpc = new AntLarvaPieCookable();
//		assertEquals("3 Ant larvas, 1 Wheat", alpc.reqMaterialsToString());
//		ApplePieCookable apc = new ApplePieCookable();
//		assertEquals("3 Apples, 1 Wheat", apc.reqMaterialsToString());
//	}
//	
//	@Test
//	public void testCraftable() {
//		// armor
//		GreatChestPlate gpc = new GreatChestPlate();
//		assertEquals("2 Woods, 3 Irons, 2 Stones", gpc.reqMaterialsToString());
//		GreatShield gs = new GreatShield();
//		assertEquals("1 Wood, 2 Irons, 1 Stone", gs.reqMaterialsToString());
//		IronChestPlate icp = new IronChestPlate();
//		assertEquals("4 Irons", icp.reqMaterialsToString());
//		IronShield is = new IronShield();
//		assertEquals("3 Irons", is.reqMaterialsToString());
//		StoneChestPlate scp = new StoneChestPlate();
//		assertEquals("4 Stones", scp.reqMaterialsToString());
//		StoneShield ss = new StoneShield();
//		assertEquals("3 Stones", ss.reqMaterialsToString());
//		WoodChestPlate wcp = new WoodChestPlate();
//		assertEquals("4 Woods", wcp.reqMaterialsToString());
//		WoodShield ws = new WoodShield();
//		assertEquals("3 Woods", ws.reqMaterialsToString());
//		// weapons
//		BasicIronAxe bia = new BasicIronAxe();
//		assertEquals("2 Woods, 1 Iron, 1 Stone", bia.reqMaterialsToString());
//		BasicStoneAxe bsa = new BasicStoneAxe();
//		assertEquals("2 Woods, 1 Stone", bsa.reqMaterialsToString());
//		BasicSword bs = new BasicSword();
//		assertEquals("1 Wood, 2 Irons, 2 Stones", bs.reqMaterialsToString());
//		FortifiedIronAxe fia = new FortifiedIronAxe();
//		assertEquals("2 Woods, 2 Irons, 1 Stone", fia.reqMaterialsToString());
//		FortifiedStoneAxe fsa = new FortifiedStoneAxe();
//		assertEquals("2 Woods, 2 Stones", fsa.reqMaterialsToString());
//		LureAxe la = new LureAxe();
//		assertEquals("1 Ant larva, 2 Woods, 1 Stone", la.reqMaterialsToString());
//		UltraSword us = new UltraSword();
//		assertEquals("1 Wood, 3 Irons, 3 Stones", us.reqMaterialsToString());
//	}
//	
	@Test
	public void testFurniture() {
		AppleTreePlot atp = new AppleTreePlot();
		assertEquals("2 Ant larvas, 2 Apple seeds", atp.reqMaterialsToString());
		BasicCrate bc = new BasicCrate();
		assertEquals("2 Woods", bc.reqMaterialsToString());
		Bed b = new Bed();
		assertEquals("1 Wheat stem, 1 Wood, 1 Stone", b.reqMaterialsToString());
		Couch c = new Couch();
		assertEquals("1 Wheat stem, 1 Wood, 1 Stone", c.reqMaterialsToString());
		Fireplace f = new Fireplace();
		assertEquals("1 Iron, 2 Stones", f.reqMaterialsToString());
		HealingBed hb = new HealingBed();
		assertEquals("2 Wheat stems, 2 Woods, 1 Iron, 2 Stones", hb.reqMaterialsToString());
		IncubationChamber ic = new IncubationChamber();
		assertEquals("1 Wheat stem, 2 Stones", ic.reqMaterialsToString());
		Ladder l = new Ladder();
		assertEquals("", l.reqMaterialsToString());
		MetalCrate mc = new MetalCrate(new LinkedList<Item>());
		assertEquals("3 Woods, 1 Iron, 2 Stones", mc.reqMaterialsToString());
		PoolTable pt = new PoolTable();
		assertEquals("2 Woods, 1 Iron, 2 Stones", pt.reqMaterialsToString());
		CraftingMachine mm = new CraftingMachine();
		assertEquals("1 Iron, 1 Stone", mm.reqMaterialsToString());
		ReinforcedCrate rc = new ReinforcedCrate(new LinkedList<Item>());
		assertEquals("6 Woods, 2 Stones", rc.reqMaterialsToString());
		WheatPlantPlot wpp = new WheatPlantPlot();
		assertEquals("2 Ant larvas, 2 Wheat kernels", wpp.reqMaterialsToString());
	}
	
	@Test
	public void testRooms() {
		Position p = new Position(0, 0);
		BedRoom br = new BedRoom(p); // 3 beds
		assertEquals("3 Wheat stems, 4 Woods, 3 Stones", br.reqMaterialsToString());
		EntertainmentRoom er = new EntertainmentRoom(p); // pool table, 2 couch
		assertEquals("2 Wheat stems, 5 Woods, 1 Iron, 4 Stones", er.reqMaterialsToString());
		HorizontalTunnel ht = new HorizontalTunnel(p);
		assertEquals("", ht.reqMaterialsToString());
		IncubationRoom ir = new IncubationRoom(p);
		assertEquals("3 Wheat stems, 1 Wood, 6 Stones", ir.reqMaterialsToString());
		InfirmaryRoom inr = new InfirmaryRoom(p);
		assertEquals("4 Wheat stems, 5 Woods, 2 Irons, 4 Stones", inr.reqMaterialsToString());
	}

}
