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
@SuiteClasses({ ArmorTest.class, AttackActionTest.class, BuildingBlockTest.class, InventoryTest.class, ItemTest.class,
		MoveActionTest.class, HungerTest.class, GatherActionTest.class })
public class AllTests {

}
