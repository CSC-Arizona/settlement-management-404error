package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.actors.Position;
import model.building_blocks.AirBlock;
import model.building_blocks.BuildingBlock;
import model.building_blocks.EarthBlock;
import model.building_blocks.IronOreBlock;
import model.game.Game;
import model.items.AppleItem;
import model.items.IronItem;
import model.items.Item;
import model.items.WheatKernelItem;
import model.items.WoodItem;
import model.map.Map;
import model.room.BedRoom;
import model.room.EntertainmentRoom;
import model.room.FarmRoom;
import model.room.HorizontalTunnel;
import model.room.IncubationRoom;
import model.room.InfirmaryRoom;
import model.room.Room;
import model.room.StoreRoom;
import model.room.VerticalTunnel;

/**
 * Tests for all classes contained in model.Room
 * 
 * @author Katherine Walters
 *
 */
public class RoomTest {

	public Map generateMap(int[][] map) {
		BuildingBlock[][] mapTypes = new BuildingBlock[map.length][map[0].length];
		for (int i = 0; i < mapTypes.length; i++) {
			for (int j = 0; j < mapTypes[i].length; j++) {
				if (map[i][j] == 0)
					mapTypes[i][j] = new AirBlock();
				else if (map[i][j] == 2)
					mapTypes[i][j] = new IronOreBlock();
				else
					mapTypes[i][j] = new EarthBlock();
			}
		}
		return new Map(mapTypes);
	}

	int[][] mapGen = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };

	@Test
	public void testNeedsWalls() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Position p = new Position(0, 0);
		BedRoom br = new BedRoom(p);
		assertTrue(br.needsWalls());
		EntertainmentRoom er = new EntertainmentRoom(p);
		assertTrue(er.needsWalls());
		FarmRoom fr = new FarmRoom(p);
		assertTrue(fr.needsWalls());
		HorizontalTunnel ht = new HorizontalTunnel(p);
		assertFalse(ht.needsWalls());
		IncubationRoom ir = new IncubationRoom(p);
		assertTrue(ir.needsWalls());
		StoreRoom sr = new StoreRoom(p);
		assertTrue(sr.needsWalls());
		VerticalTunnel vt = new VerticalTunnel(p);
		assertFalse(vt.needsWalls());
	}

	@Test
	public void testBedRoom() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Room br = new BedRoom(new Position(0, 0));
		assertEquals(0, br.getPosition().compareTo(new Position(0, 0)));
		assertEquals(12, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(6, br.getRoomCapacity());
		// bedrooms can have 3 upgrades
		assertTrue(br.upgradeRoom());
		assertEquals(6 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(6 + br.increaseCapacityBy() + br.increaseCapacityBy(),
				br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(
				6 + br.increaseCapacityBy() + br.increaseCapacityBy()
						+ br.increaseCapacityBy(), br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(
				6 + br.increaseCapacityBy() + br.increaseCapacityBy()
						+ br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredUpgradeMaterials().getClass());
	}

	@Test
	public void testEntertainmentRoom() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Room er = new EntertainmentRoom(new Position(0, 0));
		assertEquals(0, er.getPosition().compareTo(new Position(0, 0)));
		assertEquals(10, er.getRequiredWidth());
		assertEquals(2, er.getRequiredHeight());
		assertEquals(8, er.getRoomCapacity());
		// entertainment rooms can have 2 upgrades
		assertTrue(er.upgradeRoom());
		assertEquals(8 + er.increaseCapacityBy(), er.getRoomCapacity());
		assertTrue(er.upgradeRoom());
		assertEquals(8 + er.increaseCapacityBy() + er.increaseCapacityBy(),
				er.getRoomCapacity());
		assertFalse(er.upgradeRoom());
		assertEquals(8 + er.increaseCapacityBy() + er.increaseCapacityBy(),
				er.getRoomCapacity());
		assertEquals(0, er.getNumAgentsInRoom());
		er.increaseNumAgentsInRoom();
		assertEquals(1, er.getNumAgentsInRoom());
		er.decreaseNumAgentsInRoom();
		assertEquals(0, er.getNumAgentsInRoom());
		er.decreaseNumAgentsInRoom();
		assertEquals(0, er.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), er
				.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), er
				.getRequiredUpgradeMaterials().getClass());
	}

	@Test
	public void testFarmRoom() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		FarmRoom er = new FarmRoom(new Position(0, 0));
		assertEquals(0, er.getPosition().compareTo(new Position(0, 0)));
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
		assertEquals(new LinkedList<Item>().getClass(), er
				.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), er
				.getRequiredUpgradeMaterials().getClass());
		assertEquals(0, er.getState());
		er.advanceState();
		assertEquals(0, er.getState());
		int wheat = 0;

		assertEquals(0, er.getState());
		assertTrue(er.plant(20));
		assertEquals(1, er.getState());
		assertFalse(er.plant(1));
		assertEquals(null, er.harvest());
		er.advanceState();
		assertEquals(2, er.getState());

		for (int i = 0; i < 200; i++) {
			er.advanceState();
		}

		List<Item> yield = er.harvest();
		if (yield != null) {
			for (Item it : yield) {
				if (it.getClass().equals(new WheatKernelItem().getClass())) {
					wheat++;
				}
			}
		}
		
		assertTrue(wheat >= 20 && wheat <= 40);

	}

	@Test
	public void testInfirmaryRoom() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Room br = new InfirmaryRoom(new Position(0, 0));
		assertEquals(0, br.getPosition().compareTo(new Position(0, 0)));
		assertEquals(4, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(8, br.getRoomCapacity());
		// infirmarys can have 2 upgrades
		assertTrue(br.upgradeRoom());
		assertEquals(8 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(8 + br.increaseCapacityBy() + br.increaseCapacityBy(),
				br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(8 + br.increaseCapacityBy() + br.increaseCapacityBy(),
				br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredUpgradeMaterials().getClass());
	}

	@Test
	public void testStoreRoom() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Room br = new StoreRoom(new Position(0, 0));
		assertEquals(0, br.getPosition().compareTo(new Position(0, 0)));
		assertEquals(8, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(3, br.getRoomCapacity());
		// store rooms can have 2 upgrades
		assertEquals(new WoodItem().getClass(), br
				.getRequiredUpgradeMaterials().get(0).getClass());
		assertTrue(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(new WoodItem().getClass(), br
				.getRequiredUpgradeMaterials().get(0).getClass());
		assertTrue(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy() + br.increaseCapacityBy(),
				br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(3 + br.increaseCapacityBy() + br.increaseCapacityBy(),
				br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredUpgradeMaterials().getClass());
	}

	@Test
	public void testVerticalTunnel() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Room br = new VerticalTunnel(new Position(0, 0));
		assertEquals(0, br.getPosition().compareTo(new Position(0, 0)));
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
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredBuildMaterials().getClass());
		assertEquals(0, br.increaseCapacityBy());
	}

	@Test
	public void testHorizontalTunnel() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Room br = new HorizontalTunnel(new Position(0, 0));
		assertEquals(0, br.getPosition().compareTo(new Position(0, 0)));
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
		assertEquals(0, br.increaseCapacityBy());
	}

	@Test
	public void testIncubationRoom() {
		Game.reset();
		Game.setMap(generateMap(mapGen));
		Room br = new IncubationRoom(new Position(0, 0));
		assertEquals(0, br.getPosition().compareTo(new Position(0, 0)));
		assertEquals(12, br.getRequiredWidth());
		assertEquals(2, br.getRequiredHeight());
		assertEquals(4, br.getRoomCapacity());
		// Incubation rooms can have 3 upgrades
		assertTrue(br.upgradeRoom());
		assertEquals(4 + br.increaseCapacityBy(), br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(4 + br.increaseCapacityBy() + br.increaseCapacityBy(),
				br.getRoomCapacity());
		assertTrue(br.upgradeRoom());
		assertEquals(
				4 + br.increaseCapacityBy() + br.increaseCapacityBy()
						+ br.increaseCapacityBy(), br.getRoomCapacity());
		assertFalse(br.upgradeRoom());
		assertEquals(
				4 + br.increaseCapacityBy() + br.increaseCapacityBy()
						+ br.increaseCapacityBy(), br.getRoomCapacity());
		assertEquals(0, br.getNumAgentsInRoom());
		br.increaseNumAgentsInRoom();
		assertEquals(1, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		br.decreaseNumAgentsInRoom();
		assertEquals(0, br.getNumAgentsInRoom());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredBuildMaterials().getClass());
		assertEquals(new LinkedList<Item>().getClass(), br
				.getRequiredUpgradeMaterials().getClass());
	}

}
