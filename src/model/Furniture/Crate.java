package model.Furniture;

import java.util.List;

import model.Items.Item;

//Author: Maxwell Faridian
//This class constructs Crate objects, which can be used to store items in a storage room

public abstract class Crate extends Furniture {
	
	private static int capacity = 1;
	
	public Crate (int capacity, int weightCapacity) {
		super(capacity, weightCapacity, "crate");
	}
	

}
