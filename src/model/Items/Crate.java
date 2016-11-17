package model.Items;

//Author: Maxwell Faridian
//This class constructs Crate objects, where items can be stored

public class Crate extends Item {
	
	private int weightCapacity;
	
	public Crate (boolean edible, int attackModifier, int healthPts, double weight, int weightCapacity) {
		//weight is only here so that this class can extend item, but it is essentially an arbitrary value
		super(edible, attackModifier, healthPts, weight);
		this.weightCapacity = weightCapacity;
	}
	
	//Returns the total weight of items that can be stored in this crate
	public int getWeightCapacity() {
		return this.weightCapacity;
	}
}
