package model.Items;


//Author: Maxwell Faridian
//This class defines the Stone Chest Plate armor
//Stone Chest Plate: 4 stone


public class StoneChestPlate extends Armor {
	private final static int attackMod = 12;
	private final static int defenseMod = 14;
	
	public StoneChestPlate() {
		super(false, attackMod, 0, 20.0, defenseMod);
	}
}
