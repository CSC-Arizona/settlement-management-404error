/**
 * 
 */
package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import model.Map;
import model.Actors.Actor;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.BuildingBlocks.AirBlock;
import model.BuildingBlocks.BuildingBlock;
import model.BuildingBlocks.EarthBlock;
import model.BuildingBlocks.IronOreBlock;
import model.Items.AntLarvaItem;

/**
 * @author Jonathon Davis
 *
 */
public class HungerTest {

	@Test
	public void testEat() {

		PlayerControlledActor test = new PlayerControlledActor(10, -150000, new Position(1,1));
		test.getInventory().addItem(new AntLarvaItem());
		assertTrue(test.isAlive());
		for (int i = 0; i < 1000; i++) {
			test.update();
		}
		assertTrue(test.isAlive());
		for (int i = 0; i < 501; i++) {
			test.update();
		}
		//assertFalse(test.isAlive());
	}

	@Test
	public void testHunger() {
		
		PlayerControlledActor test = new PlayerControlledActor(10, -150000, new Position(1,1));
		assertTrue(test.isAlive());
		for (int i = 0; i < 1000; i++) {
			test.update();
		}
		//assertFalse(test.isAlive());
	}

}
