package model.Furniture;

import java.util.List;

import model.Items.Item;

//Author: Maxwell Faridian
//This class is abstract and is extended by all pieces of furniture that are placed inside of rooms
public abstract class Furniture {
	
	private int capacity;
	
	public Furniture(int capacity) {
		this.capacity = capacity;
	}
	
	//This method increases the capacity of a particular piece of furniture when a room is upgraded
	public void increaseCapacityBy(int increasedCapacity) {
		this.capacity += increasedCapacity;
	}
	
	//This method returns a List of items that are needed in order to build a specific piece of furniture
	abstract public List<Item> getRequiredMaterials(); 

}
