package model;

import java.util.LinkedList;
import java.util.List;

public class Inventory {

	private final double MAXWEIGHT = 100.0;
	private double weight;
	private List<Item> items;
	
	public Inventory() {
		this.weight = 0;
		items = new LinkedList<>();
	}
	
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
	
	public Item removeItem(int index) {
		if (index < items.size()) {
			weight -= items.get(index).getWeight();
		    return items.remove(index);
		} else
			return null;
	}
	
	public double getWeightCarried() {
		return weight;
	}
}
