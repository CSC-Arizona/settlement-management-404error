package model.Items;


//Author: Maxwell Faridian
//This class defines the Wood Chest Plate armor
//Wood Chest Plate: 4 wood

public class WoodChestPlate extends Armor {
	
	private final static int attackMod = 6;
	private final static int defenseMod = 12;
	
	public WoodChestPlate() {
		super(false, attackMod, 0, 9.0, defenseMod);
	}
}
