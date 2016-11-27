package model.Items;

import java.util.List;

//Author: Maxwell Faridian
//This class constructs Craftable items, which require a list of needed materials to craft said item
public abstract class Craftable extends Item {

	private static final long serialVersionUID = -6120651370931295074L;

	public Craftable(boolean edible, int attackModifier, int healthPts, double weight) {
		super(edible, attackModifier, healthPts, weight);
		// TODO Auto-generated constructor stub
	}
	
	//Returns a linked list of items needed to make a craftable item
	abstract public List<Item> getRequiredMaterials();

}
