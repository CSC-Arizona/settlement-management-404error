package model.items;

import static org.junit.Assert.*;

import org.junit.Test;

public class CraftableEnumTest {

	@Test
	public void test() {
		//assertEquals("", CraftableEnum.getAllCraftableNames());
		String[] names = CraftableEnum.getAllCraftableNames();
		for (int i = 0; i < names.length; i++)
			System.out.println(names[i]);
	}

}
