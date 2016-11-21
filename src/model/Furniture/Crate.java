package model.Furniture;

import java.util.List;

import model.Items.Item;

//Author: Maxwell Faridian
//This class constructs Crate objects, which can be used to store items in a storage room

public abstract class Crate extends Furniture {
	
	private static int capacity = 1;
	private int weightCapacity;
	
	public Crate (int weightCapacity) {
		super(capacity);
		this.weightCapacity = weightCapacity;
	}
	
	//Returns the total weight of items that can be stored in this crate
	public int getWeightCapacity() {
		return this.weightCapacity;
	}
}
