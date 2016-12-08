package model.weapons;

import java.util.LinkedList;
import java.util.List;

import model.items.GoldItem;
import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Ultra Sword weapon, which is the strongest weapon in the game
//Ultra sword: 1 wood, 3 stone, 3 iron

public class UltraSword extends Weapon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7899298860484130800L;
	private final static int attackMod = 27;
	private static List<Item> usList;
	
	public UltraSword() {
		super(attackMod, 0, 39.0, "Ultra sword", null);
	}

	public static List<Item> getRequiredMaterials() {
		usList = new LinkedList<>();
		usList.add(new WoodItem());
		usList.add(new StoneItem());
		usList.add(new StoneItem());
		usList.add(new StoneItem());
		usList.add(new IronItem());
		usList.add(new IronItem());
		usList.add(new IronItem());
		usList.add(new GoldItem());
		return usList;
	}

}
