package model.Items;


//Author: Maxwell Faridian
//This class defines the Iron Chest Plate armor
//Iron Chest Plate: 4 iron

public class IronChestPlate extends Armor {
	private final static int attackMod = 20;
	private final static int defenseMod = 16;
	
	public IronChestPlate() {
		super(false, attackMod, 0, 28.0, defenseMod);
	}
}
