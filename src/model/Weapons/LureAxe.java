package model.Weapons;

import java.util.LinkedList;
import java.util.List;

import model.Items.AntLarvaItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Lure Axe weapon, which lures ants to you
//Lure Axe: 2 wood, 1 stone, 1 antLarvaItem

public class LureAxe extends Weapon {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7178058005109807707L;
	//This item has a weaker attackMod since it attracts ants
	private final static int attackMod = 12;
	private List<Item> laList;
	
	public LureAxe() {
		super(attackMod, 1, 11.5, "Lure axe");
		laList = new LinkedList<>();
		laList.add(new WoodItem());
		laList.add(new WoodItem());
		laList.add(new StoneItem());
		laList.add(new AntLarvaItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return laList;
	}

}
