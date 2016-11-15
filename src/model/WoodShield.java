package model;

//Author: Maxwell Faridian
//This class defines the Wood Shield armor
//Wood Shield: 3 wood

public class WoodShield extends Armor {

	private final static int attackMod = 4;
	private final static int defenseMod = 10;
	
	public WoodShield() {
		super(false, attackMod, 0, 9.0, defenseMod);
	}
}
