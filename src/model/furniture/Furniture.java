package model.furniture;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.items.Item;
import model.menus.PrintableItemsList;

//Author: Maxwell Faridian
//This class is abstract and is extended by all pieces of furniture that are placed inside of rooms
public abstract class Furniture implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6642154062478447160L;
	private int capacity;
	private double weightCapacity;
	private String id;
	public double contentsWeight = 0;
	private List<Item> contentsList;
	private PrintableItemsList ril;
	private ImageEnum img;

	public Furniture(int capacity, double weightCapacity, String id, ImageEnum img) {
		contentsList = new LinkedList<>();
		this.capacity = capacity;
		this.weightCapacity = weightCapacity;
		this.id = id;
		this.img = img;
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
	
	public double getWeightCapacity() {
		return this.weightCapacity;
	}
	
	public double getRemainingWeightCapacity() {
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
	
	public String reqMaterialsToString() {
		if (this.ril == null) {
			ril = new PrintableItemsList();
			for (Item i : this.getRequiredMaterials())
				ril.addItem(i);
		}
		return ril.toString();
    }

	public boolean removeItem(Item item) {
		if(contentsWeight >= item.getWeight()) {
			contentsWeight-=item.getWeight();
		}
		return contentsList.remove(item);
		
	}
	
	public ImageEnum getImage(){
		return img;
	}
	
	//This method returns a List of items that are needed in order to build a specific piece of furniture
	abstract public List<Item> getRequiredMaterials(); 
	
	abstract public String toString();
	

}
