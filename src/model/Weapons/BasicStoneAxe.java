package model.Weapons;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Basic Stone Axe weapon
//Basic Stone Axe: 2 wood and 1 stone

public class BasicStoneAxe extends Weapon {
	
	private final static int attackMod = 15;
	private List<Item> basicStoneAxeList;
	
	public BasicStoneAxe() {
		super(attackMod, 0, 11.0);
		basicStoneAxeList = new LinkedList<>();
		basicStoneAxeList.add(new WoodItem());
		basicStoneAxeList.add(new WoodItem());
		basicStoneAxeList.add(new StoneItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return basicStoneAxeList;
	}

}
