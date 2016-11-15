package model.Items;


//Author: Maxwell Faridian
//This class defines the Ultra Sword weapon, which is the strongest weapon in the game
//Ultra sword: 1 wood, 3 stone, 3 iron

public class UltraSword extends Item {
	
	private final static int attackMod = 27;
	
	public UltraSword() {
		super(false, attackMod, 0, 39.0);
	}

}
