package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WheatStemItem;
import model.Items.WoodItem;

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
		super(capacity, 0, "bed");
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

}
