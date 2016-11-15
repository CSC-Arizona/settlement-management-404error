package model.Items;


//Author: Maxwell Faridian
//This class defines the Iron Shield armor
//Iron Shield: 3 iron

public class IronShield extends Armor {
	
	private final static int attackMod = 18;
	private final static int defenseMod = 14;
	
	public IronShield() {
		super(false, attackMod, 0, 21.0, defenseMod);
	}
}
