package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Actors.Actor;
import model.Actors.Position;
import model.Items.Item;

/**
 * Inventory holds a collection of Items as a List. It implements a weight limit
 * on the collection, so only a certain amount of items can be added to the
 * inventory.
 * 
 * @author Katherine Walters
 */
public class Inventory implements Iterable<Item> {

	public static final double MAXWEIGHT = 100.0;
	private double weight;
	/*
	 * I made this a list instead of a HashMap so that it can be displayed on
	 * the GUI as a JList
	 */
	private List<Item> items;

	public Inventory() {
		this.weight = 0;
		items = new LinkedList<>();
	}

	/*
	 * takes a single item as a parameter. If the item's weight won't make the
	 * inventory exceed its weight limit, the item is added and it returns true.
	 * Else, drop the item on the ground.
	 */
	public void addItem(Item it) {
			items.add(it);
			weight += it.getWeight();
	}

	/*
	 * takes an index as a parameter. If the index is within the limits of the
	 * inventory, it removes and returns the item at that index. Otherwise it
	 * returns null.
	 */
	public Item removeItem(int index) {
		if (index < items.size()) {
			weight -= items.get(index).getWeight();
			return items.remove(index);
		} else
			return null;
	}
	
	public Item getItem(int index) {
		if (index < items.size()) {
			return items.get(index);
		} else
			return null;	
	}

	/*
	 * returns the current amount of weight being carried by the inventory.
	 */
	public double getWeightCarried() {
		return weight;
	}
	
	public boolean canAdd(Item it){
		return it.getWeight() + weight <= MAXWEIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	public int size() {
		return items.size();
	}

	@Override
	public String toString() {
		String result = "";
		for (Item item : items) {
			result += item.toString() + " ";
		}
		return result.trim();
	}
	
	/**
	 * @param required
	 * @return
	 */
	public boolean contains(Item required) {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(item.getClass().equals(required.getClass())){
				return true;
			}
		}
		return false;
	}

	/**
	 * @param required
	 */
	public void removeItem(Item required) {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(item.getClass().equals(required.getClass())){
				this.weight -= item.getWeight();
				it.remove();
				return;
			}
		}
		
	}
}
