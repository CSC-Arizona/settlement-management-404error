package model.weapons;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Basic Iron Axe weapon
//Basic Iron Axe: 2 wood, 1 stone, 1 iron
public class BasicIronAxe extends Weapon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3576539972302576150L;
	private final static int attackMod = 19;
	private static List<Item> basicIronAxeList;
	
	public BasicIronAxe() {
		super(attackMod, 0, 18.0, "Basic iron axe", null);
		basicIronAxeList = new LinkedList<>();
		basicIronAxeList.add(new WoodItem());
		basicIronAxeList.add(new WoodItem());
		basicIronAxeList.add(new StoneItem());
		basicIronAxeList.add(new IronItem());
	}

	public static List<Item> getRequiredMaterials() {
		return basicIronAxeList;
	}

}
