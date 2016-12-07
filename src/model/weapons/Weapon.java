package model.weapons;

import images.ImageEnum;
import model.items.Item;

//Author: Maxwell Faridian
//This class defines the Weapon object
public abstract class Weapon extends Item { //extends Craftable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590825761249784692L;
	private String id;
	private int attackModifier;
	private double weight;

	//Weapons can not be eaten
	public Weapon(int attackModifier, int healthPts, double weight, String id, ImageEnum img) {
		super(false, attackModifier, healthPts, weight, img);
		this.attackModifier = attackModifier;
		this.weight = weight;
		this.id = id;
	}
	
	public String toString() {
		return "" + id + " (" + attackModifier + " attack mod, " + weight + " weight)";
	}

}
