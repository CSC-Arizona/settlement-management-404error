package model.Items;


//Author: Maxwell Faridian
//This class defines the Great Chest Plate armor
//Great Chest Plate: 2 wood, 2 stone, 3 iron

public class GreatChestPlate extends Armor {
	
	private final static int attackMod = 22;
	private final static int defenseMod = 18;
	
	public GreatChestPlate() {
		super(false, attackMod, 0, 30.0, defenseMod);
	}
}
