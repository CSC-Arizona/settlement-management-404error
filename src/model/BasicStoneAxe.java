package model;

//Author: Maxwell Faridian
//This class defines the Basic Stone Axe weapon
//Basic Stone Axe: 2 wood and 1 stone

public class BasicStoneAxe extends Item {
	
	private final static int attackMod = 15;
	
	public BasicStoneAxe() {
		super(false, attackMod, 0, 11.0);
	}

}
