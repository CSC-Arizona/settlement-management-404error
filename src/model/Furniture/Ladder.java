package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.Item;
import model.Items.WoodItem;

public class Ladder extends Furniture {

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
     
}
