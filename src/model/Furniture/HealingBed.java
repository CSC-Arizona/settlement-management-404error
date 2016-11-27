package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WheatStemItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Healing Bed Furniture, which actors can rest on in the infirmary to regain health points
//Healing Bed: 4 wood, 2 stone, 5 wheat stems, 1 iron
public class HealingBed extends Furniture {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8011112006333334423L;
	private static int capacity = 2;
	private List<Item> healingBedList;

	public HealingBed() {
		super(capacity, 0, "bed");
		healingBedList = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			healingBedList.add(new WoodItem());
		}
		healingBedList.add(new StoneItem());
		healingBedList.add(new StoneItem());
		for (int i = 0; i < 5; i++) {
			healingBedList.add(new WheatStemItem());
		}
		healingBedList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return healingBedList;
	}

}
