package model.Items;


//Author: Maxwell Faridian
//This class defines the Stone Shield armor
//Stone Shield: 3 stone

public class StoneShield extends Armor {
	
	private final static int attackMod = 10;
	private final static int defenseMod = 12;
	
	public StoneShield() {
		super(false, attackMod, 0, 15.0, defenseMod);
	}
}
