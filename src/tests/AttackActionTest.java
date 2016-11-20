/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import model.Actors.Actor;
import model.Actors.AttackAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;

/**
 * @author Jonathon Davis
 *
 */
public class AttackActionTest {
	
	@Test
	public void attackTest(){
		Actor tester = new PlayerControlledActor(5,new Position(10,10));
		Actor rival = new PlayerControlledActor(5,new Position(11,10));
		tester.addToActionQueue(new AttackAction(rival));
		assertEquals(5,rival.getHealth());
		assertEquals(0,tester.getSkills().getCombatLevel());
		tester.update();
		assertEquals(0,tester.getSkills().getCombatLevel());
		assertEquals(4,rival.getHealth());
		tester.update();
		assertEquals(0,tester.getSkills().getCombatLevel());
		assertEquals(3,rival.getHealth());
		tester.update();
		assertEquals(1,tester.getSkills().getCombatLevel());
		assertEquals(2,rival.getHealth());
		tester.update();
		assertEquals(1,tester.getSkills().getCombatLevel());
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
		tester.update();
		assertEquals(0,rival.getHealth());
	}

}
