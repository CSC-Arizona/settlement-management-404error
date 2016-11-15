package model.Items;


//Author: Maxwell Faridian
//This class defines the Basic Sword weapon
//Basic sword: 1 wood, 2 stone, 2 iron

public class BasicSword extends Item {

	private final static int attackMod = 23;
	
	public BasicSword() {
		super(false, attackMod, 0, 27.0);
	}
}
