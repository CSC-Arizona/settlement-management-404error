package model.weapons;

import java.util.LinkedList;
import java.util.List;

import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Fortified Stone Axe weapon
//Fortified Stone Axe: 2 wood, 2 stone

public class FortifiedStoneAxe extends Weapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624686854967576466L;
	private final static int attackMod = 17;
	private static List<Item> fsaList;
	
	public FortifiedStoneAxe() {
		super(attackMod, 0, 16.0, "Fortified stone axe", null);
	}

	public static List<Item> getRequiredMaterials() {
		fsaList = new LinkedList<>();
		fsaList.add(new WoodItem());
		fsaList.add(new WoodItem());
		fsaList.add(new StoneItem());
		fsaList.add(new StoneItem());	
		return fsaList;
	}
	
}
