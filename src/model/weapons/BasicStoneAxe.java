package model.weapons;

import java.util.LinkedList;
import java.util.List;

import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Basic Stone Axe weapon
//Basic Stone Axe: 2 wood and 1 stone

public class BasicStoneAxe extends Weapon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7908483863488465121L;
	private final static int attackMod = 15;
	private static List<Item> basicStoneAxeList;
	
	public BasicStoneAxe() {
		super(attackMod, 0, 11.0, "Basic stone axe", null);
	}

	public static List<Item> getRequiredMaterials() {
		basicStoneAxeList = new LinkedList<>();
		basicStoneAxeList.add(new WoodItem());
		basicStoneAxeList.add(new WoodItem());
		basicStoneAxeList.add(new StoneItem());
		return basicStoneAxeList;
	}

}
