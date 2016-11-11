/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import model.Actor;
import model.AttackAction;
import model.BasicActor;

/**
 * @author Jonathon Davis
 *
 */
public class AttackActionTest {
	
	@Test
	public void moveAndAttackTest(){
		Actor tester = new BasicActor(5,new Point(10,10));
		Actor rival = new BasicActor(5,new Point(15,20));
		tester.addToActionQueue(new AttackAction(rival));
		
		assertEquals(10,tester.getPosition().x);
		assertEquals(10,tester.getPosition().y);
		tester.update();
		assertEquals(11,tester.getPosition().x);
		assertEquals(11,tester.getPosition().y);
		tester.update();
		assertEquals(12,tester.getPosition().x);
		assertEquals(12,tester.getPosition().y);
		tester.update();
		assertEquals(13,tester.getPosition().x);
		assertEquals(13,tester.getPosition().y);
		tester.update();
		assertEquals(14,tester.getPosition().x);
		assertEquals(14,tester.getPosition().y);
		tester.update();
		assertEquals(15,tester.getPosition().x);
		assertEquals(15,tester.getPosition().y);
		tester.update();
		assertEquals(15,tester.getPosition().x);
		assertEquals(16,tester.getPosition().y);
		tester.update();
		assertEquals(15,tester.getPosition().x);
		assertEquals(17,tester.getPosition().y);
		tester.update();
		assertEquals(15,tester.getPosition().x);
		assertEquals(18,tester.getPosition().y);
		tester.update();
		assertEquals(15,tester.getPosition().x);
		assertEquals(19,tester.getPosition().y);
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
	
	@Test
	public void attackTest(){
		Actor tester = new BasicActor(5,new Point(10,10));
		Actor rival = new BasicActor(5,new Point(11,10));
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
