package model.furniture;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;

//Author: Maxwell Faridian
//This class defines the Fireplace Furniture, which will be used in the kitchen to make food
//Fireplace: 4 stone, 2 iron
public class Fireplace extends Furniture {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8275631316064539965L;
	private static int capacity = 2; //2 dragons can cook at once
	private List<Item> fireplaceList;

	public Fireplace() {
		super(capacity, 0, "fireplace", null);
		fireplaceList = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			fireplaceList.add(new StoneItem());
		}
		fireplaceList.add(new IronItem());
		fireplaceList.add(new IronItem());
		
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return fireplaceList;
	}

	@Override
	public String toString() {
		return "Fireplace";
	}

}