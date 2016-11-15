/**
 * 
 */
package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Jonathon Davis
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AttackActionTest.class, MoveActionTest.class, BuildingBlockTest.class, InventoryTest.class, ItemTest.class })
public class AllTests {

}