package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Items.AntLarvaPieCookable;
import model.Items.BreadCookable;

/**
 * Tests the output of the reqItemsToString that exists in the CookableItem, Craftable, Furniture, 
 * and Room classes.
 * 
 * @author Katherine Walters
 */
public class TestPrintedRequiredItemLists {

	@Test
	public void testCookable() {
		BreadCookable bc = new BreadCookable();
		assertEquals("Required Items: 2 Wheat", bc.reqMaterialsToString());
		AntLarvaPieCookable alpc = new AntLarvaPieCookable();
		assertEquals("Required Items: 3 Ant larva, 1 Wheat", alpc.reqMaterialsToString());
		
	}

}
