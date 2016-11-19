package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WheatStemItem;
import model.Items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Couch Furniture, which actors can sit on to satisfy their happiness need
//Couch: 4 wood, 1 stone, 4 wheat stems 
public class Couch extends Furniture {
	
	private static int capacity = 3;
	private List<Item> couchList;

	public Couch() {
		super(capacity);
		couchList = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			couchList.add(new WoodItem());
		}
		couchList.add(new StoneItem());
		for (int i = 0; i < 4; i++) {
			couchList.add(new WheatStemItem());
		}
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return couchList;
	}

}
