package model.furniture;

import java.util.LinkedList;
import java.util.List;

import model.items.Item;
import model.items.WoodItem;

public class Ladder extends Furniture {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2659920162119419368L;
	private List<Item> ladderMaterials;
	
	public Ladder() {
		super(10, 0, "ladder");
        ladderMaterials = new LinkedList<>();
        ladderMaterials.add(new WoodItem());
        ladderMaterials.add(new WoodItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return ladderMaterials;
	}

	@Override
	public String toString() {
		return "Ladder";
	}
     
}
