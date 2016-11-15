package model.Items;

//Author: Maxwell Faridian
//This class defines the Fortified Stone Axe weapon
//Fortified Stone Axe: 2 wood, 2 stone

public class FortifiedStoneAxe extends Item {

	private final static int attackMod = 17;
	
	public FortifiedStoneAxe() {
		super(false, attackMod, 0, 16.0);
	}
	
}
