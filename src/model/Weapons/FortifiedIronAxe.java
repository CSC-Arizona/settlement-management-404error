package model.Weapons;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Fortified Iron Axe weapon
//Fortified Iron Axe: 2 wood, 1 stone, 2 iron
public class FortifiedIronAxe extends Weapon {
	
	private final static int attackMod = 21;
	private List<Item> fortifiedIronAxeList;
	
	public FortifiedIronAxe() {
		super(attackMod, 0, 25.0);
		fortifiedIronAxeList = new LinkedList<>();
		fortifiedIronAxeList.add(new WoodItem());
		fortifiedIronAxeList.add(new WoodItem());
		fortifiedIronAxeList.add(new StoneItem());
		fortifiedIronAxeList.add(new IronItem());
		fortifiedIronAxeList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return fortifiedIronAxeList;
	}
}
