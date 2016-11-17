package model.Items;

//Author: Maxwell Faridian
//This class defines the Metal Crate
//Metal Crate (6 wood, 2 stone, 2 iron): 70lb weight capacity
public class MetalCrate extends Crate {
	private final static int attackMod = 6;
	private final static int weightCapacity = 70;
	
	public MetalCrate() {
		super(false, attackMod, 0, 30, weightCapacity);
	}
}
