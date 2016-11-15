package model;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;

/**
 * Inventory holds a collection of Items as a List. It implements a weight limit on the 
 * collection, so only a certain amount of items can be added to the inventory.
 * 
 * @author Katherine Walters
 */
public class Inventory {

	private final double MAXWEIGHT = 100.0;
	private double weight;
	/*
	 * I made this a list instead of a HashMap so that it can be displayed on the GUI as 
	 * a JList
	 */
	private List<Item> items;
	
	public Inventory() {
		this.weight = 0;
		items = new LinkedList<>();
	}
	
	/*
	 * takes a single item as a parameter. If the item's weight won't make the inventory
	 * exceed its weight limit, the item is added and it returns true. Else, it prints out
	 * an error message and returns false.
	 */
	public boolean addItem(Item it) {
		if (weight + it.getWeight() <= MAXWEIGHT) {
		    items.add(it);
		    weight += it.getWeight();
		    return true;
		} else {
			System.out.println("Could not add item due to inventory's weight capacity.");
			return false;
		}
	}
	
	/*
	 * takes an index as a parameter. If the index is within the limits of the inventory, it
	 * removes and returns the item at that index. Otherwise it returns null.
	 */
	public Item removeItem(int index) {
		if (index < items.size()) {
			weight -= items.get(index).getWeight();
		    return items.remove(index);
		} else
			return null;
	}
	
	/*
	 * returns the current amount of weight being carried by the inventory.
	 */
	public double getWeightCarried() {
		return weight;
	}
}
