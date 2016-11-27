package model.Armor;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Great Shield armor
//Great Shield: 1 wood, 1 stone, 2 iron

public class GreatShield extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6119046218741156100L;
	private final static int attackMod = 20;
	private final static int defenseMod = 16;
	private List<Item> gsList;
	
	public GreatShield() {
		super(false, attackMod, 0, 22.0, defenseMod);
		gsList = new LinkedList<>();
		gsList.add(new WoodItem());
		gsList.add(new StoneItem());
		gsList.add(new IronItem());
		gsList.add(new IronItem());
	}


	@Override
	public List<Item> getRequiredMaterials() {
		return gsList;
	}
}
