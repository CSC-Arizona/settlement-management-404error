package model.Items;


//Author: Maxwell Faridian
//This class defines the Basic Iron Axe weapon
//Basic Iron Axe: 2 wood, 1 stone, 1 iron


public class BasicIronAxe extends Item {
	
	private final static int attackMod = 19;
	
	public BasicIronAxe() {
		super(false, attackMod, 0, 18.0);
	}

}
