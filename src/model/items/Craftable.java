package model.items;

import java.util.List;

import model.menus.PrintableItemsList;

//Author: Maxwell Faridian
//This class constructs Craftable items, which require a list of needed materials to craft said item
public abstract class Craftable extends Item {

	private static final long serialVersionUID = -6120651370931295074L;
	private PrintableItemsList ril;

	public Craftable(boolean edible, int attackModifier, int healthPts, double weight) {
		super(edible, attackModifier, healthPts, weight);
		// TODO Auto-generated constructor stub
	}
	
	public String reqMaterialsToString() {
		if (this.ril == null) {
			ril = new PrintableItemsList();
			for (Item i : this.getRequiredMaterials())
				ril.addItem(i);
		}
		return ril.toString();
	}
	
	//Returns a linked list of items needed to make a craftable item
	abstract public List<Item> getRequiredMaterials();

}
