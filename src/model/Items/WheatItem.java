package model.Items;

//Wheat item, wheatStem, wheatKernels

//Author: Maxwell Faridian
//This class defines the Wheat item, which can be eaten raw or cooked with other edible items
public class WheatItem extends Item {
	private final static int attackMod = 1;
	private final static int healthPts = 1;
	
	public WheatItem() {
		super(true, attackMod, healthPts, 1);
	}
	
	

}
