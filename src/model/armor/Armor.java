package model.armor;

import model.items.Craftable;

//Author: Maxwell Faridian
//This class constructs Armor objects, which are themselves extensions of the Item class
public abstract class Armor extends Craftable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2420900077518432098L;
	private int defenseModifier;
	private String id;
	
	public Armor (boolean edible, int attackModifier, int healthPts, double weight, int defenseMod, String id) {
		super(edible, attackModifier, healthPts, weight);
		this.defenseModifier = defenseMod;
		this.id = id;
	}
	
	//Returns the amount of health points that this piece of armor adds to an actor when worn by said actor
	public int getDefenseModifier() {
		return this.defenseModifier;
	}
	
	public String toString() {
		return "" + id + " (" + defenseModifier + " defense mod, " + this.getWeight() + " weight)";
	}
}
