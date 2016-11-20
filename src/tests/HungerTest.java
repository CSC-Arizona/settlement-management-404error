/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Items.AntLarvaItem;

/**
 * @author Jonathon Davis
 *
 */
public class HungerTest {
	
	@Test
	public void testEat(){
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1,1));
		test.getInventory().addItem(new AntLarvaItem());
		assertTrue(test.isAlive());
		for(int i = 0; i < 1500; i++){
			test.update();
		}
		assertTrue(test.isAlive());
		for(int i = 0; i < 500; i++){
			test.update();
		}
		assertFalse(test.isAlive());
	}
	
	@Test
	public void testHunger(){
		PlayerControlledActor test = new PlayerControlledActor(10, new Position(1,1));
		assertTrue(test.isAlive());
		for(int i = 0; i < 1500; i++){
			test.update();
		}
		assertFalse(test.isAlive());
	}

}
