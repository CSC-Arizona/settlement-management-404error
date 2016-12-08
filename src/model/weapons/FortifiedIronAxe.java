package model.weapons;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Fortified Iron Axe weapon
//Fortified Iron Axe: 2 wood, 1 stone, 2 iron
public class FortifiedIronAxe extends Weapon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5154963681454698902L;
	private final static int attackMod = 21;
	private static List<Item> fortifiedIronAxeList;
	
	public FortifiedIronAxe() {
		super(attackMod, 0, 25.0, "Fortified iron axe", null);
	}

	public static List<Item> getRequiredMaterials() {
		fortifiedIronAxeList = new LinkedList<>();
		fortifiedIronAxeList.add(new WoodItem());
		fortifiedIronAxeList.add(new WoodItem());
		fortifiedIronAxeList.add(new StoneItem());
		fortifiedIronAxeList.add(new IronItem());
		fortifiedIronAxeList.add(new IronItem());
		return fortifiedIronAxeList;
	}
}
