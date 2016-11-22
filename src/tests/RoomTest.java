package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Actors.Position;
import model.Items.AppleItem;
import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WheatKernelItem;
import model.Room.BedRoom;
import model.Room.EntertainmentRoom;
import model.Room.FarmRoom;
import model.Room.HorizontalTunnel;
import model.Room.InfirmaryRoom;
import model.Room.KitchenRoom;
import model.Room.Room;
import model.Room.StoreRoom;
import model.Room.VerticalTunnel;

/**
 * Tests for all classes contained in model.Room
 * 
 * @author Katherine Walters
 *
 */
public class RoomTest {

	@Test
	public void testBedRoom() {
		Room br = new BedRoom(new Position(0,0));
		assertEquals(0, br.getPosition().compareTo(new Position(0,0)));
		assertEquals(12, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(6, br.getRoomCapacity());
		// bedrooms can have 3 upgrades
		assertTrue(br.upgradeRoom());
		assertEquals(6 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(6 + br.increaseCapacityBy() + br.increaseCapacityBy(), br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(6 + br.increaseCapacityBy() + br.increaseCapacityBy() + br.increaseCapacityBy(), br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(6 + br.increaseCapacityBy() + br.increaseCapacityBy() + br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredUpgradeMaterials().getClass());
	}
	
	@Test
	public void testEntertainmentRoom() {
		Room er = new EntertainmentRoom(new Position(0,0));
		assertEquals(0, er.getPosition().compareTo(new Position(0,0)));
		assertEquals(13, er.getRequiredWidth());
		assertEquals(2, er.getRequiredHeight());
		assertEquals(8, er.getRoomCapacity());
		// entertainment rooms can have 2 upgrades
		assertTrue(er.upgradeRoom());
		assertEquals(8 + er.increaseCapacityBy(), er.getRoomCapacity());
		assertTrue(er.upgradeRoom());
		assertEquals(8 + er.increaseCapacityBy() + er.increaseCapacityBy(), er.getRoomCapacity());
		assertFalse(er.upgradeRoom());
		assertEquals(8 + er.increaseCapacityBy() + er.increaseCapacityBy(), er.getRoomCapacity());
		assertEquals(0, er.getNumAgentsInRoom());
		er.increaseNumAgentsInRoom();
		assertEquals(1, er.getNumAgentsInRoom());
		er.decreaseNumAgentsInRoom();
		assertEquals(0, er.getNumAgentsInRoom());
		er.decreaseNumAgentsInRoom();
		assertEquals(0, er.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), er.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), er.getRequiredUpgradeMaterials().getClass());
	}
	
	@Test
	public void testFarmRoom() {
		FarmRoom er = new FarmRoom(new Position(0,0));
		assertEquals(0, er.getPosition().compareTo(new Position(0,0)));
		assertEquals(8, er.getRequiredWidth());
		assertEquals(2, er.getRequiredHeight());
		assertEquals(4, er.getRoomCapacity());
		assertEquals(0, er.increaseCapacityBy());
		// entertainment rooms can have 0 upgrades
		assertFalse(er.upgradeRoom());
		assertEquals(4, er.getRoomCapacity());
		assertEquals(0, er.getNumAgentsInRoom());
		er.increaseNumAgentsInRoom();
		assertEquals(1, er.getNumAgentsInRoom());
		er.decreaseNumAgentsInRoom();
		assertEquals(0, er.getNumAgentsInRoom());
		er.decreaseNumAgentsInRoom();
		assertEquals(0, er.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), er.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), er.getRequiredUpgradeMaterials().getClass());
		assertEquals(0, er.getState());
		er.advanceState();
		assertEquals(0, er.getState());
		int wheat;
		int apple;
		for (int i = 0; i < 100; i++) {
			assertEquals(0, er.getState());
			assertTrue(er.plant(20, 20));
			assertEquals(1, er.getState());
			assertFalse(er.plant(1, 1));
			assertEquals(null, er.harvest());
			er.advanceState();
			assertEquals(2, er.getState());
			List<Item> yield = er.harvest();
			wheat = 0;
			apple = 0;
			for (Item it : yield) {
				if (it.getClass().equals(new AppleItem().getClass())) {
					apple++;
				}
				if (it.getClass().equals(new WheatKernelItem().getClass())) {
					wheat++;
				}
			}
			apple /= 3;
			wheat /= 2;
			assertTrue(apple <= 20 && apple >= 10);
			assertFalse(apple > 20 || apple < 10);
			assertTrue(wheat <= 20 && apple >= 10);
			assertFalse(wheat > 20 || wheat < 10);
		}
	}
	
	@Test
	public void testInfirmaryRoom() {
		Room br = new InfirmaryRoom(new Position(0,0));
		assertEquals(0, br.getPosition().compareTo(new Position(0,0)));
		assertEquals(4, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(8, br.getRoomCapacity());
		// infirmarys can have 2 upgrades
		assertTrue(br.upgradeRoom());
		assertEquals(8 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(8 + br.increaseCapacityBy() + br.increaseCapacityBy(), br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(8 + br.increaseCapacityBy() + br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredUpgradeMaterials().getClass());
	}
	
	@Test
	public void testKitchenRoom() {
		Room br = new KitchenRoom(new Position(0,0));
		assertEquals(0, br.getPosition().compareTo(new Position(0,0)));
		assertEquals(6, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(3, br.getRoomCapacity());
		// kitchens can have 1 upgrades
		assertTrue(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredUpgradeMaterials().getClass());
	}
	
	@Test
	public void testStoreRoom() {
		Room br = new StoreRoom(new Position(0,0));
		assertEquals(0, br.getPosition().compareTo(new Position(0,0)));
		assertEquals(8, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(3, br.getRoomCapacity());
		// store rooms can have 2 upgrades
		assertEquals(new StoneItem().getClass(), br.getRequiredUpgradeMaterials().get(0).getClass());
		assertTrue(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(new IronItem().getClass(), br.getRequiredUpgradeMaterials().get(0).getClass());
		assertTrue(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy() + br.increaseCapacityBy(), br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy() + br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredUpgradeMaterials().getClass());
	}
	
	@Test
	public void testVerticalTunnel() {
		Room br = new VerticalTunnel(new Position(0,0));
		assertEquals(0, br.getPosition().compareTo(new Position(0,0)));
		assertEquals(1, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(20, br.getRoomCapacity());
		// bedrooms can have 0 upgrades
		assertFalse(br.upgradeRoom());
		assertEquals(20, br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br.getRequiredBuildMaterials().getClass());
		assertEquals(null, br.getRequiredUpgradeMaterials());
		assertEquals(0, br.increaseCapacityBy());
	}
	
	@Test
	public void testHorizontalTunnel() {
		Room br = new HorizontalTunnel(new Position(0,0));
		assertEquals(0, br.getPosition().compareTo(new Position(0,0)));
		assertEquals(2, br.getRequiredWidth());
		assertEquals(1, br.getRequiredHeight());
		assertEquals(10, br.getRoomCapacity());
		// bedrooms can have 0 upgrades
		assertFalse(br.upgradeRoom());
		assertEquals(10, br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(null, br.getRequiredBuildMaterials());
		assertEquals(null, br.getRequiredUpgradeMaterials());
		assertEquals(0, br.increaseCapacityBy());
	}
	

}
