package model.weapons;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Basic Sword weapon
//Basic sword: 1 wood, 2 stone, 2 iron

public class BasicSword extends Weapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -415305750658078527L;
	private final static int attackMod = 23;
	private static List<Item> basicSwordList;
	
	public BasicSword() {
		super(attackMod, 0, 27.0, "Basic sword", null);
	}

	public static List<Item> getRequiredMaterials() {
		basicSwordList = new LinkedList<>();
		basicSwordList.add(new WoodItem());
		basicSwordList.add(new StoneItem());
		basicSwordList.add(new StoneItem());
		basicSwordList.add(new IronItem());
		basicSwordList.add(new IronItem());
		return basicSwordList;
	}
}
