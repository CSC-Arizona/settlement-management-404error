package model.Weapons;

import model.Items.Item;

//Author: Maxwell Faridian
//This class defines the Fortified Iron Axe weapon
//Fortified Iron Axe: 2 wood, 1 stone, 2 iron

public class FortifiedIronAxe extends Item {
	
	private final static int attackMod = 21;
	
	public FortifiedIronAxe() {
		super(false, attackMod, 0, 25.0);
	}
}
