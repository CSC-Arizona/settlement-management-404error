package model.Weapons;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Ultra Sword weapon, which is the strongest weapon in the game
//Ultra sword: 1 wood, 3 stone, 3 iron

public class UltraSword extends Weapon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7899298860484130800L;
	private final static int attackMod = 27;
	private List<Item> usList;
	
	public UltraSword() {
		super(attackMod, 0, 39.0);
		usList = new LinkedList<>();
		usList.add(new WoodItem());
		usList.add(new StoneItem());
		usList.add(new StoneItem());
		usList.add(new StoneItem());
		usList.add(new IronItem());
		usList.add(new IronItem());
		usList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return usList;
	}

}
