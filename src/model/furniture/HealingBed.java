package model.furniture;

import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WheatStemItem;
import model.items.WoodItem;

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
		super(capacity, 0, "healing bed", ImageEnum.MEDBED);
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

	@Override
	public String toString() {
		return "Healing Bed";
	}

}
