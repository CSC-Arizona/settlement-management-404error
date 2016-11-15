package model.Items;


//Author: Maxwell Faridian
//This class defines the Great Shield armor
//Great Shield: 1 wood, 1 stone, 2 iron

public class GreatShield extends Armor {

	private final static int attackMod = 20;
	private final static int defenseMod = 16;
	
	public GreatShield() {
		super(false, attackMod, 0, 22.0, defenseMod);
	}
}
