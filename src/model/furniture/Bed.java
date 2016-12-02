package model.furniture;

import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.items.Item;
import model.items.StoneItem;
import model.items.WheatStemItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Bed Furniture, which actors can rest on to satisfy the fatigue need
//Bed: 4 wood, 2 stone, 4 wheat stems
public class Bed extends Furniture {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9071486516251898395L;
	private static int capacity = 2;
	private List<Item> bedList;

	public Bed() {
		super(capacity, 0, "bed", ImageEnum.BED);
		bedList = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			bedList.add(new WoodItem());
		}
		bedList.add(new StoneItem());
		bedList.add(new StoneItem());
		for (int i = 0; i < 4; i++) {
			bedList.add(new WheatStemItem());
		}

	}

	@Override
	public List<Item> getRequiredMaterials() {
		return bedList;
	}

	@Override
	public String toString() {
		return "Bed";
	}

}
