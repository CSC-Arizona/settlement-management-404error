package model.Items;

//Author: Maxwell Faridian
//This class defines the Wheat Kernel Item, which can be planted to create Wheat Blocks
public class WheatKernelItem extends Item {
	private final static int attackMod = 1;
	private final static int healthPts = 1;
	
	public WheatKernelItem() {
		super(true, attackMod, healthPts, 1);
	}
}
