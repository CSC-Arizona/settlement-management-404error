package model.Weapons;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Basic Sword weapon
//Basic sword: 1 wood, 2 stone, 2 iron

public class BasicSword extends Weapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -415305750658078527L;
	private final static int attackMod = 23;
	private List<Item> basicSwordList;
	
	public BasicSword() {
		super(attackMod, 0, 27.0);
		basicSwordList = new LinkedList<>();
		basicSwordList.add(new WoodItem());
		basicSwordList.add(new StoneItem());
		basicSwordList.add(new StoneItem());
		basicSwordList.add(new IronItem());
		basicSwordList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return basicSwordList;
	}
}
