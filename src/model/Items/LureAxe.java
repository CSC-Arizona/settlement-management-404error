package model.Items;


//Author: Maxwell Faridian
//This class defines the Lure Axe weapon, which lures ants to you
//Lure Axe: 2 wood, 1 stone, 1 antLarvaItem

public class LureAxe extends Item {
	//This item has a weaker attackMod since it attracts ants
	private final static int attackMod = 12;
	
	public LureAxe() {
		super(true, attackMod, 1, 11.5);
	}

}
