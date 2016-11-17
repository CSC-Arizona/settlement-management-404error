package model.Items;

//Author: Maxwell Faridian
//This class defines the Reinforced Crate
//Reinforced Crate (6 wood, 2 stone): 50lb weight capacity
public class ReinforcedCrate extends Crate {
	private final static int attackMod = 6;
	private final static int weightCapacity = 50;
	
	public ReinforcedCrate() {
		super(false, attackMod, 0, 20, weightCapacity);
	}
}
