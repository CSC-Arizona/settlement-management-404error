package model.furniture;

import java.util.LinkedList;
import java.util.List;

import images.ImageEnum;
import model.items.Item;
import model.items.StoneItem;
import model.items.WheatStemItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Couch Furniture, which actors can sit on to satisfy their happiness need
//Couch: 4 wood, 1 stone, 4 wheat stems 
public class Couch extends Furniture {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3889714829706540699L;
	private static int capacity = 3;
	private List<Item> couchList;

	public Couch() {
		super(capacity, 0, "couch", ImageEnum.COUCH);
		couchList = new LinkedList<>();
		for (int i = 0; i < 1; i++) {
			couchList.add(new WoodItem());
			couchList.add(new StoneItem());
			couchList.add(new WheatStemItem());
		}
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return couchList;
	}

	@Override
	public String toString() {
		return "Couch";
	}

}
