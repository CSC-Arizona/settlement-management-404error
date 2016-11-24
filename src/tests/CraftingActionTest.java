/**
 * 
 */
package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Actors.CraftAction;
import model.Actors.PlayerControlledActor;
import model.Actors.Position;
import model.Items.IronItem;
import model.Items.StoneItem;
import model.Items.WoodItem;
import model.Weapons.BasicSword;

/**
 * @author Jonathon Davis
 *
 */
public class CraftingActionTest {
	
	@Test
	public void testCrafting(){
		PlayerControlledActor test = new PlayerControlledActor(10, 0, new Position(1,1), null);
		BasicSword item = new BasicSword();
		test.addToActionQueue(new CraftAction(item,item.getRequiredMaterials()));
		test.getInventory().addItem(new WoodItem());
		test.getInventory().addItem(new WoodItem());
		test.getInventory().addItem(new StoneItem());
		test.getInventory().addItem(new StoneItem());
		test.getInventory().addItem(new IronItem());
		test.update();
		assertTrue(test.getInventory().contains(new WoodItem()));
		assertFalse(test.getInventory().contains(new StoneItem()));
		assertFalse(test.getInventory().contains(new IronItem()));
		assertFalse(test.getInventory().contains(new BasicSword()));
		test.getInventory().addItem(new IronItem());
		test.update();
		assertTrue(test.getInventory().contains(new WoodItem()));
		assertFalse(test.getInventory().contains(new StoneItem()));
		assertFalse(test.getInventory().contains(new IronItem()));
		assertTrue(test.getInventory().contains(new BasicSword()));
	}

}
