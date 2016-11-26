package model.Weapons;

import java.util.List;

import model.Items.Item;

//Author: Maxwell Faridian
//This class defines the Weapon object
public abstract class Weapon extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590825761249784692L;

	//Weapons can not be eaten
	public Weapon(int attackModifier, int healthPts, double weight) {
		super(false, attackModifier, healthPts, weight);
	}
	
	//Returns a linked list of items needed to make a given piece of weaponry
	abstract public List<Item> getRequiredMaterials();

}
