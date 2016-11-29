package model.Furniture;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import model.Items.Item;

//Author: Maxwell Faridian
//This class is abstract and is extended by all pieces of furniture that are placed inside of rooms
public abstract class Furniture implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6642154062478447160L;
	private int capacity;
	private int weightCapacity;
	private String id;
	public int contentsWeight = 0;
	private List<Item> contentsList;

	public Furniture(int capacity, int weightCapacity, String id) {
		contentsList = new LinkedList<>();
		this.capacity = capacity;
		this.weightCapacity = weightCapacity;
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	
	//This method increases the capacity of a particular piece of furniture when a room is upgraded
	public void increaseCapacityBy(int increasedCapacity) {
		this.capacity += increasedCapacity;
	}
	
	//This method returns the capacity of the particular furniture item
	public int getCapacity() {
		return this.capacity;
	}
	
	public int getWeightCapacity() {
		return this.weightCapacity;
	}
	
	public int getRemainingWeightCapacity() {
		return this.weightCapacity - contentsWeight;
	}
	
	public boolean addItem(Item item) {
		if (item.getWeight() + contentsWeight <= weightCapacity) {
			contentsList.add(item);
			contentsWeight += item.getWeight();
			return true;
		}
		return false;
	}
	
	public boolean removeItem(Item item) {
		return contentsList.remove(item);
	}
	
	//This method returns a List of items that are needed in order to build a specific piece of furniture
	abstract public List<Item> getRequiredMaterials(); 
	
	abstract public String toString();
	

}
