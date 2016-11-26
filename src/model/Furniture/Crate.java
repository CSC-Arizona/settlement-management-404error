package model.Furniture;

//Author: Maxwell Faridian
//This class constructs Crate objects, which can be used to store items in a storage room

public abstract class Crate extends Furniture {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2013070688745156833L;
	public Crate (int capacity, int weightCapacity) {
		super(capacity, weightCapacity, "crate");
	}
	

}
