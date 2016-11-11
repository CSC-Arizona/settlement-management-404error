/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import model.Actor;
import model.BasicActor;
import model.MoveAction;

/**
 * @author Jonathon Davis
 *
 */
public class MoveActionTest {
	
	@Test
	public void moveTest(){
		Actor tester = new BasicActor(10,new Point(10,10));
		Point location = new Point(15,20);
		tester.addToActionQueue(new MoveAction(location));
		
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
		tester.update();
		assertEquals(15,tester.getPosition().x);
		assertEquals(20,tester.getPosition().y);
		tester.update();
		tester.update();
		tester.update();
		tester.update();
		assertEquals(15,tester.getPosition().x);
		assertEquals(20,tester.getPosition().y);
	}
	
	@Test
	public void moveTest2(){
		Actor tester = new BasicActor(10,new Point(10,10));
		Point location = new Point(5,15);
		tester.addToActionQueue(new MoveAction(location));
		
		assertEquals(10,tester.getPosition().x);
		assertEquals(10,tester.getPosition().y);
		tester.update();
		assertEquals(9,tester.getPosition().x);
		assertEquals(11,tester.getPosition().y);
		tester.update();
		assertEquals(8,tester.getPosition().x);
		assertEquals(12,tester.getPosition().y);
		tester.update();
		assertEquals(7,tester.getPosition().x);
		assertEquals(13,tester.getPosition().y);
		tester.update();
		assertEquals(6,tester.getPosition().x);
		assertEquals(14,tester.getPosition().y);
		tester.update();
		assertEquals(5,tester.getPosition().x);
		assertEquals(15,tester.getPosition().y);
	}

}
