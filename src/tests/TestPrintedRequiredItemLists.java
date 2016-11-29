package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Actors.Position;
import model.Armor.GreatChestPlate;
import model.Armor.GreatShield;
import model.Armor.IronChestPlate;
import model.Armor.IronShield;
import model.Armor.StoneChestPlate;
import model.Armor.StoneShield;
import model.Armor.WoodChestPlate;
import model.Armor.WoodShield;
import model.Furniture.AppleTreePlot;
import model.Furniture.BasicCrate;
import model.Furniture.Bed;
import model.Furniture.Couch;
import model.Furniture.Fireplace;
import model.Furniture.HealingBed;
import model.Furniture.IncubationChamber;
import model.Furniture.Ladder;
import model.Furniture.MetalCrate;
import model.Furniture.MillingMachine;
import model.Furniture.PoolTable;
import model.Furniture.ReinforcedCrate;
import model.Furniture.WheatPlantPlot;
import model.Items.AntLarvaPieCookable;
import model.Items.ApplePieCookable;
import model.Items.BreadCookable;
import model.Room.BedRoom;
import model.Room.EntertainmentRoom;
import model.Room.HorizontalTunnel;
import model.Room.IncubationRoom;
import model.Room.InfirmaryRoom;
import model.Weapons.BasicIronAxe;
import model.Weapons.BasicStoneAxe;
import model.Weapons.BasicSword;
import model.Weapons.FortifiedIronAxe;
import model.Weapons.FortifiedStoneAxe;
import model.Weapons.LureAxe;
import model.Weapons.UltraSword;

/**
 * Tests the output of the reqItemsToString that exists in the CookableItem, Craftable, Furniture, 
 * and Room classes.
 * 
 * @author Katherine Walters
 */
public class TestPrintedRequiredItemLists {

	@Test
	public void testCookable() {
		BreadCookable bc = new BreadCookable();
		assertEquals("2 Wheats", bc.reqMaterialsToString());
		AntLarvaPieCookable alpc = new AntLarvaPieCookable();
		assertEquals("3 Ant larvas, 1 Wheat", alpc.reqMaterialsToString());
		ApplePieCookable apc = new ApplePieCookable();
		assertEquals("3 Apples, 1 Wheat", apc.reqMaterialsToString());
	}
	
	@Test
	public void testCraftable() {
		// armor
		GreatChestPlate gpc = new GreatChestPlate();
		assertEquals("2 Woods, 3 Irons, 2 Stones", gpc.reqMaterialsToString());
		GreatShield gs = new GreatShield();
		assertEquals("1 Wood, 2 Irons, 1 Stone", gs.reqMaterialsToString());
		IronChestPlate icp = new IronChestPlate();
		assertEquals("4 Irons", icp.reqMaterialsToString());
		IronShield is = new IronShield();
		assertEquals("3 Irons", is.reqMaterialsToString());
		StoneChestPlate scp = new StoneChestPlate();
		assertEquals("4 Stones", scp.reqMaterialsToString());
		StoneShield ss = new StoneShield();
		assertEquals("3 Stones", ss.reqMaterialsToString());
		WoodChestPlate wcp = new WoodChestPlate();
		assertEquals("4 Woods", wcp.reqMaterialsToString());
		WoodShield ws = new WoodShield();
		assertEquals("3 Woods", ws.reqMaterialsToString());
		// weapons
		BasicIronAxe bia = new BasicIronAxe();
		assertEquals("2 Woods, 1 Iron, 1 Stone", bia.reqMaterialsToString());
		BasicStoneAxe bsa = new BasicStoneAxe();
		assertEquals("2 Woods, 1 Stone", bsa.reqMaterialsToString());
		BasicSword bs = new BasicSword();
		assertEquals("1 Wood, 2 Irons, 2 Stones", bs.reqMaterialsToString());
		FortifiedIronAxe fia = new FortifiedIronAxe();
		assertEquals("2 Woods, 2 Irons, 1 Stone", fia.reqMaterialsToString());
		FortifiedStoneAxe fsa = new FortifiedStoneAxe();
		assertEquals("2 Woods, 2 Stones", fsa.reqMaterialsToString());
		LureAxe la = new LureAxe();
		assertEquals("1 Ant larva, 2 Woods, 1 Stone", la.reqMaterialsToString());
		UltraSword us = new UltraSword();
		assertEquals("1 Wood, 3 Irons, 3 Stones", us.reqMaterialsToString());
	}
	
	@Test
	public void testFurniture() {
		AppleTreePlot atp = new AppleTreePlot();
		assertEquals("2 Ant larvas, 2 Apple seeds", atp.reqMaterialsToString());
		BasicCrate bc = new BasicCrate();
		assertEquals("6 Woods", bc.reqMaterialsToString());
		Bed b = new Bed();
		assertEquals("4 Wheat stems, 4 Woods, 2 Stones", b.reqMaterialsToString());
		Couch c = new Couch();
		assertEquals("4 Wheat stems, 4 Woods, 1 Stone", c.reqMaterialsToString());
		Fireplace f = new Fireplace();
		assertEquals("2 Irons, 4 Stones", f.reqMaterialsToString());
		HealingBed hb = new HealingBed();
		assertEquals("5 Wheat stems, 4 Woods, 1 Iron, 2 Stones", hb.reqMaterialsToString());
		IncubationChamber ic = new IncubationChamber();
		assertEquals("2 Irons, 2 Stones", ic.reqMaterialsToString());
		Ladder l = new Ladder();
		assertEquals("2 Woods", l.reqMaterialsToString());
		MetalCrate mc = new MetalCrate();
		assertEquals("6 Woods, 2 Irons, 2 Stones", mc.reqMaterialsToString());
		PoolTable pt = new PoolTable();
		assertEquals("4 Woods, 1 Iron, 3 Stones", pt.reqMaterialsToString());
		MillingMachine mm = new MillingMachine();
		assertEquals("2 Irons, 2 Stones", mm.reqMaterialsToString());
		ReinforcedCrate rc = new ReinforcedCrate();
		assertEquals("6 Woods, 2 Stones", rc.reqMaterialsToString());
		WheatPlantPlot wpp = new WheatPlantPlot();
		assertEquals("2 Ant larvas, 2 Wheat kernels", wpp.reqMaterialsToString());
	}
	
	@Test
	public void testRooms() {
		Position p = new Position(0, 0);
		BedRoom br = new BedRoom(p); // 3 beds
		assertEquals("12 Wheat stems, 12 Woods, 6 Stones", br.reqMaterialsToString());
		EntertainmentRoom er = new EntertainmentRoom(p); // pool table, 2 couch
		assertEquals("8 Wheat stems, 12 Woods, 1 Iron, 5 Stones", er.reqMaterialsToString());
		HorizontalTunnel ht = new HorizontalTunnel(p);
		assertEquals("", ht.reqMaterialsToString());
		IncubationRoom ir = new IncubationRoom(p);
		assertEquals("4 Irons, 4 Stones", ir.reqMaterialsToString());
		InfirmaryRoom inr = new InfirmaryRoom(p);
		assertEquals("10 Wheat stems, 8 Woods, 2 Irons, 4 Stones", inr.reqMaterialsToString());
	}

}
