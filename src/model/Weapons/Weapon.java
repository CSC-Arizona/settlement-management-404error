package model.Weapons;

import model.Items.Craftable;

//Author: Maxwell Faridian
//This class defines the Weapon object
public abstract class Weapon extends Craftable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590825761249784692L;
	private String id;
	private int attackModifier;
	private double weight;

	//Weapons can not be eaten
	public Weapon(int attackModifier, int healthPts, double weight, String id) {
		super(false, attackModifier, healthPts, weight);
		this.attackModifier = attackModifier;
		this.weight = weight;
		this.id = id;
	}
	
	public String toString() {
		return "" + id + " (" + attackModifier + " attack mod, " + weight + " weight)";
	}

}
