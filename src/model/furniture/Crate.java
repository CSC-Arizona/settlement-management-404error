package model.furniture;

import java.util.Iterator;
import java.util.LinkedList;

import images.ImageEnum;
import model.items.Item;
import model.menus.PrintableItemsList;

//Author: Maxwell Faridian
//This class constructs Crate objects, which can be used to store items in a storage room

public abstract class Crate extends Furniture {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2013070688745156833L;
	private LinkedList<Item> containedItems;
	private PrintableItemsList piList;
	private double weightCapacity;
	private double weightCarried;
	
	public Crate (int capacity, double weightCapacity) {
		super(capacity, (int) weightCapacity, "crate", ImageEnum.CRATE);
		this.weightCapacity = weightCapacity;
		containedItems = new LinkedList<>();
		piList = new PrintableItemsList();
		weightCarried = 0.0;
	}
	
	/*
	 * takes a single item as a parameter. If the item's weight won't make the
	 * inventory exceed its weight limit, the item is added and it returns true.
	 * Else, drop the item on the ground.
	 */
	public boolean addItem(Item it) {
		if (this.canAdd(it)) {
			containedItems.add(it);
			weightCarried += it.getWeight();
			piList.addItem(it);
			return true;
		}
		return false;
	}
	
	public double getCrateCapacity() {
		return weightCapacity;
	}
	
	/*
	 * returns the current amount of weight being carried by the inventory.
	 */
	public double getWeightCarried() {
		return weightCarried;
	}
	
	public boolean canAdd(Item it){
		return it.getWeight() + weightCarried <= weightCapacity;
	}
	
	@Override
	public String toString() {
		String result = "" + getWeightCarried() + "/" + weightCapacity + 
				" weight carried";
		if (!piList.toString().equals("")) {
			result = result + " (" + piList.toString() + ")";
		}
		return result;
	}
	
	/**
	 * @param required
	 * @return
	 */
	public boolean contains(Item required) {
		Iterator<Item> it = containedItems.iterator();
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
	public boolean removeItem(Item required) {
		Iterator<Item> it = containedItems.iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(item.getClass().equals(required.getClass())){
				this.weightCarried -= item.getWeight();
				it.remove();
				piList.removeItem(required);
				return true;
			}
		}
		return false;
	}
}
