package model.Armor;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.StoneItem;

//Author: Maxwell Faridian
//This class defines the Stone Shield armor
//Stone Shield: 3 stone

public class StoneShield extends Armor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2717122119561276068L;
	private final static int attackMod = 10;
	private final static int defenseMod = 12;
	private List<Item> ssList;
	
	public StoneShield() {
		super(false, attackMod, 0, 15.0, defenseMod);
		ssList = new LinkedList<>();
		ssList.add(new StoneItem());
		ssList.add(new StoneItem());
		ssList.add(new StoneItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return ssList;
	}
}
