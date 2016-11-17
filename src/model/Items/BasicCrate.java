package model.Items;

//Author: Maxwell Faridian
//This class defines the Basic Crate
//Basic Crate (6 wood): 30lb weight capacity
public class BasicCrate extends Crate {
	private final static int attackMod = 6;
	private final static int weightCapacity = 30;
	
	public BasicCrate() {
		super(false, attackMod, 0, 10, weightCapacity);
	}

}
